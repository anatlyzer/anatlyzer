package anatlyzer.atl.analyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.BagType;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.MapType;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OrderedSetType;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringType;
import anatlyzer.atlext.OCL.TupleType;
import anatlyzer.atlext.OCL.TupleTypeAttribute;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class ExplicitTypeTraversal extends AbstractAnalyserVisitor {
	

	public ExplicitTypeTraversal(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}
		
	public void perform(ComputedAttributes attr) {
		this.attr = attr.pushVisitor(this);
		startVisiting(root);
		attr.popVisitor(this);
	}

	// Variable declarations, setting the static type
	
	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		self.setStaticType( attr.typeOf( self.getType() ) );
	}
	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		self.setStaticType( attr.typeOf( self.getType() ) );
	}
	
	@Override
	public void inParameter(Parameter self) {
		self.setStaticType( attr.typeOf( self.getType() ) );
	}
	
	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		if ( self.getType() != null ) {
			self.setStaticType( attr.typeOf( self.getType() ) );			
		}
	}
	
	@Override
	public void inRuleVariableDeclaration(RuleVariableDeclaration self) {
		inVariableDeclaration(self);
	}
	
	
	//  
	// Variables and model access
	//
	
	@Override
	public void inOclModelElement(OclModelElement self) {
		OclModel metamodel = self.getModel();
		String mmName = metamodel.getName();
		MetamodelNamespace mmspace = mm.getNamespace(mmName);
		if ( mmspace == null ) {
			// TODO: As recovery action look up a model with the required
			//       type name
			errors().signalNoModel(mmName, self);
			
			attr.linkExprType(typ().newUnknownType());
			return;
		}
		
		ITypeNamespace tspace = mmspace.getClassifier(self.getName());
		if ( tspace == null ) {
			if ( "OclAny".equals(self.getName()) ) {
				attr.linkExprType(typ().newUnknownType());
				return;
			}

			// Recovery is done by generating a special error type
			Type errorType = errors().signalNoClass(self.getName(), mmspace, self);
			attr.linkExprType(errorType);
			return;
		}
		Type type = (Type) tspace.createType(true);
		// metaclass.setExplicitOcurrence(true);
		attr.linkExprType(type);

		checkReadingTargetModel(self);
		checkWritingSourceModel(self);
	}	

	protected Set<String> outputMetamodelNames;
	protected Set<String> inputMetamodelNames;
	protected Set<String> sameInputOutputMetamodelNames;
	
	protected Set<String> getOutMetamodelNames() {
		if ( outputMetamodelNames == null ) {
			outputMetamodelNames = ATLUtils.getModelInfo(model).stream().filter(m -> m.isOutput()).map(m -> m.getMetamodelName()).collect(Collectors.toSet());
		}
		return outputMetamodelNames;
	}

	protected Set<String> getInMetamodelNames() {
		if ( inputMetamodelNames == null ) {
			inputMetamodelNames = ATLUtils.getModelInfo(model).stream().filter(m -> m.isInput()).map(m -> m.getMetamodelName()).collect(Collectors.toSet());
		}
		return inputMetamodelNames;
	}

	protected Set<String> getSameInputOutputMetamodelNames() {
		if ( sameInputOutputMetamodelNames == null ) {
			sameInputOutputMetamodelNames = new HashSet<String>(getInMetamodelNames());
			sameInputOutputMetamodelNames.retainAll(getOutMetamodelNames());
		}
		return sameInputOutputMetamodelNames;		
	}

	protected void checkWritingSourceModel(OclModelElement self) {
		OclModel metamodel = self.getModel();
		if ( root instanceof Module && getSameInputOutputMetamodelNames().isEmpty() ) {	
			if ( getInMetamodelNames().contains(metamodel.getName()) && self.eContainer() instanceof OutPatternElement ) {
				errors().signalWritingSourceModel(self);			
			}
		}
	}
	
	/**
	 * This is likely to be moved to a separate pass, because it seems to be more complex than this
	 * @param self 
	 */
	protected void checkReadingTargetModel(OclModelElement self) {
//		if ( true ) {
//			return; // TODO: This is check is not accurate, so it is not performed for the moment
//		}
		
		
		OclModel metamodel = self.getModel();
		
		// TODO: Reading target model: Not sure if this may catch false cases
		if ( root instanceof Module && getSameInputOutputMetamodelNames().isEmpty() ) {

			if ( getOutMetamodelNames().contains(metamodel.getName()) && 
				 self.eContainer() instanceof OclExpression && ( ! (self.eContainer() instanceof OclType) )) {
				// The last check is to avoid problems with declarations of target types like in Set(ATargetType)
				
				// Check it is not in a do block, typically invoking a newInstance
				//  	&& ! (self.container_() instanceof CalledRule)
				EObject parent = self.eContainer();
				while ( !(parent instanceof ModuleElement) ) {
					// It is a do block?
					if ( parent instanceof Statement ) {
						return;
					}
					// Check it is in the parameters of a called rule
					if ( parent instanceof Parameter && parent.eContainer() instanceof CalledRule ) {
						return;
					}
				
					parent = parent.eContainer();
				}
				
				if ( parent instanceof Helper ) {
					if ( AnalyserUtils.isTargetInvariant((Helper) parent) || AnalyserUtils.isContract((Helper) parent)) 
						return;
				}
				
				errors().signalReadingTargetModel(self);
			}
			
//			if ( numTargets > 1 && metamodel.getModel().size() == 0 ) {
//				errors().signalAmbiguousTargetModelReference(self);
//			}
		}
		
		// I think there are ambiguous cases if the same meta-model
		// appears in source and target, but perhaps only for bindings, so this is
		// not the place to check		
	}

	// 
	// Primitive types
	//
	
	
	@Override
	public void inBooleanType(BooleanType self) {
		attr.linkExprType(typ().newBooleanType());
	}
	
	@Override
	public void inStringType(StringType self) {
		attr.linkExprType(typ().newStringType());
	}
	
	@Override
	public void inIntegerType(IntegerType self) {
		attr.linkExprType(typ().newIntegerType());
	}

	@Override
	public void inRealType(RealType self) {
		attr.linkExprType(typ().newFloatType());
	}
	
	@Override
	public void inOclType(OclType self) {
		attr.linkExprType(typ().newOclType());
	}
	
	//
	// Collection types
	//
	
	@Override
	public void inSequenceType(SequenceType self) {
		attr.linkExprType( typ().newSequenceType( attr.typeOf( self.getElementType() ) ) );
	}

	@Override
	public void inSetType(SetType self) {
		attr.linkExprType( typ().newSetType( attr.typeOf( self.getElementType() ) ) );
	}
	
	@Override
	public void inOrderedSetType(OrderedSetType self) {
		attr.linkExprType( typ().newOrderedSetType( attr.typeOf( self.getElementType() ) ) );
	}
	
	@Override
	public void inBagType(BagType self) {
		attr.linkExprType( typ().newBagType( attr.typeOf( self.getElementType() ) ) );
	}

	
	

	@Override
	public void inMapType(MapType self) {
		attr.linkExprType( typ().newMapType( 
				attr.typeOf( self.getKeyType() ), 
				attr.typeOf( self.getValueType() ) ));
	}
	
	@Override
	public void inTupleType(TupleType self) {
		Type[] attTypes   = new Type[self.getAttributes().size()];
		String[] attNames = new String[self.getAttributes().size()];
		
		int i = 0;
		for(TupleTypeAttribute a : self.getAttributes()) {
			attTypes[i] = attr.typeOf(a.getType());
			attNames[i] = a.getName();
			i++;
		}
		attr.linkExprType( typ().newTupleTuple(attNames, attTypes) );
	}
	
	@Override
	public void inOclAnyType(OclAnyType self) {
		attr.linkExprType(typ().newUnknownType());
	}
	

}
