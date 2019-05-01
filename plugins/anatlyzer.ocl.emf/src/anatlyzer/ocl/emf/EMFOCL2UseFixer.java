package anatlyzer.ocl.emf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.pivot.CollectionKind;
import witness.generator.USENameModifyier;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.RetypingToSet;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BagType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OrderedSetType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker.IOCLDialectTransformer;
import anatlyzer.atl.witness.UseReservedWords;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.OCL2.OCL2Factory;
import anatlyzer.atlext.OCL2.SelectByKind;
import anatlyzer.atlext.processing.AbstractVisitor;

public class EMFOCL2UseFixer {

	public static class Pre extends AbstractVisitor implements IOCLDialectTransformer { 
		
		@Override
		public void adapt(ATLModel m, IAnalyserResult result) {
			startVisiting(m.getRoot());
		}
	
		@Override
		public void inIteratorExp(IteratorExp self) {
			// In EMF/OCL collect flattens automatically
			if ( self.getName().equals("collect") ) {
				CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				flatten.setOperationName("flatten");
				EcoreUtil.replace(self, flatten);
				flatten.setSource(self);
			}
		}
		
		@Override
		public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
			if ( self.getOperationName().equals("selectByKind") || self.getOperationName().equals("selectByType") ) {
				SelectByKind s = OCL2Factory.eINSTANCE.createSelectByKind();
				s.setOperationName(self.getOperationName());
				s.setIsExact(self.getOperationName().equals("selectByType"));
				s.getArguments().addAll(self.getArguments());
				s.setSource(self.getSource());
				EcoreUtil.replace(self, s);
			}
		}
		
		// oclAsSet
		@Override
		public void inOperationCallExp(OperationCallExp self) {
			if ( self.getOperationName().equals("oclContainer")) {
				// Map to refImmediateComposite() because it is what we use internally
				self.setOperationName("refImmediateComposite");
			} else if ( self.getOperationName().equals("oclAsSet") ) {
				self.setOperationName("asSet");
			}
		}
		
		@Override
		public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
			if ( "oclContainer".equals(self.getName()) ) { 
				OperationCallExp call = OCLFactory.eINSTANCE.createOperationCallExp();
				call.setOperationName("refImmediateComposite");
				call.setSource(self.getSource());
				EcoreUtil.replace(self,  call);
			}
		}
		
	}

	public static class Post extends AbstractVisitor implements IOCLDialectTransformer { 
	
		private Map<EObject, CollectionKind> collectionAttr = new HashMap<>();
		
		private static Map<EClass, Set<String>> UnsupportedOperations = new HashMap<>();
		private static final Map<String, CollectionKind> COL_MAPPING = new HashMap<String, CollectionKind>();
		static {
			COL_MAPPING.put("collect", CollectionKind.BAG);
			
			UnsupportedOperations.put(TypesPackage.Literals.STRING_TYPE, new HashSet<>(Arrays.asList("matches")));
			UnsupportedOperations.put(TypesPackage.Literals.SET_TYPE, new HashSet<>(Arrays.asList("last"))); // this may happen when we use Set for Sequence			
			UnsupportedOperations.put(TypesPackage.Literals.SEQUENCE_TYPE, new HashSet<>(Arrays.asList("last", "indexOf"))); 
		}
		
		@Override
		public void adapt(ATLModel m, IAnalyserResult result) {
			startVisiting(m.getRoot());
		}
	
		@Override
		public void inAttribute(Attribute self) {
			CollectionKind kind = getColAttr(self.getInitExpression());
			if ( kind != null && self.getType() instanceof anatlyzer.atlext.OCL.CollectionType ) {
				OclType nested = ((anatlyzer.atlext.OCL.CollectionType) self.getType()).getElementType();
				self.setType(toATLCollectionType(kind, nested));
			}
		}
		
		@Override
		public void inOperation(Operation self) {			
			CollectionKind kind = getColAttr(self.getBody());
			if ( kind != null && self.getReturnType() instanceof anatlyzer.atlext.OCL.CollectionType ) {
				OclType nested = ((anatlyzer.atlext.OCL.CollectionType) self.getReturnType()).getElementType();
				self.setReturnType(toATLCollectionType(kind, nested));
			}
		}
		
		@Override
		public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
			if ( self.getInferredType() instanceof CollectionType ) {
				// TODO: Ask Martin about this, it seems that associations in USE are always SET
				// collectionAttr.put(self, toCollectionKind((CollectionType) self.getInferredType()));
				setColAttr(self, CollectionKind.SET);
			}
		}

		@Override
		public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
			handleCollectionKindMapping(self, self.getOperationName());

			Type src = self.getSource().getInferredType();
			if ( src != null && UnsupportedOperations.getOrDefault(src.eClass(), new HashSet<String>()).contains(self.getOperationName())) {
				throw new UseUnsupportedOperationException("USE doesn't support: " + TypeUtils.typeToString(src) + "." + self.getOperationName());
			}
		}
		
		@Override
		public void inIteratorExp(IteratorExp self) {
			handleCollectionKindMapping(self, self.getName());
			
			if ( self.isImplicitlyCasted() ) {
				OclExpression source = self;
				
				Type t = self.getInferredType();
				if ( t instanceof CollectionType ) {
					t = ((CollectionType) t).getContainedType();				
				}
	 			
				// Assume t is a class
				String className = null;
				String modelName = null;
				if ( t instanceof Metaclass ) {
					className = ((Metaclass) t).getName();
					modelName = ((ClassNamespace) t.getMetamodelRef()).getMetamodelName();	
				} else {
					throw new UnsupportedOperationException("Type not supported " + t);
				}
			
				CSPModel builder = new CSPModel();
				IteratorExp collect = builder.createIterator(null, "collect");
				VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
				varRef.setReferredVariable(collect.getIterators().get(0));

				OperationCallExp oclAsType = RetypingToSet.createOclAsType(className, modelName, (Metaclass) t, varRef);
				collect.setBody(oclAsType);

				
				// The new expression has 
				// collect.setInferredType(self.getInferredType());
				CSPModel.setInferredType(collect, self.getInferredType());
				
				EcoreUtil.replace(self, collect);
				collect.setSource(self);
				
				// This is needed for coherence with the conversion at the beginning of the method 
				// => any collect has to be turn into a set
				// convertToSet(collect);
			}
			
		}

		private void handleCollectionKindMapping(PropertyCallExp self, String name) {
			if ( COL_MAPPING.containsKey(name) ) {
				setColAttr(self, COL_MAPPING.get(name));
			} else {
				// by default we just propagate
				setColAttr(self, getColAttr(self.getSource()));
			}
		}
		

		// oclAsSet
		@Override
		public void inOperationCallExp(OperationCallExp self) {
			// oclAsSet is valid in a non-collection
			if ( self.getOperationName().equals("asSet") && ! TypeUtils.isCollection(self.getSource().getInferredType())) {
				SetExp set = OCLFactory.eINSTANCE.createSetExp();
				EcoreUtil.replace(self, set);			
				set.getElements().add(self.getSource());
				return;
			} else if ( self.getOperationName().equals("allInstances") ) {
				setColAttr(self, CollectionKind.SET);
				return;
			} else if ( self.getOperationName().equals("oclIsKindOf") || self.getOperationName().equals("oclIsTypeOf") ) {
				Type src = self.getSource().getInferredType();
				Type arg = self.getArguments().get(0).getInferredType();
				if ( ! TypingModel.isCompatible(arg, src) ) {
					// This is doable, but if there is an oclAsType it is going to fail anyway, so maybe it is better
					// to just signal de error
					throw new InvalidOclProgramException("Invalid oclIsKindOf " + TypeUtils.typeToString(src) + " is not supertype of " + TypeUtils.typeToString(arg));
					// EcoreUtil.replace(self, OCLFactory.eINSTANCE.createBooleanExp());
				}
				return;
			}
						
			Type src = self.getSource().getInferredType();
			if ( UnsupportedOperations.getOrDefault(src.eClass(), new HashSet<String>()).contains(self.getOperationName())) {
				throw new UseUnsupportedOperationException("USE doesn't support: " + TypeUtils.typeToString(src) + "." + self.getOperationName());
			} else if ( self.getOperationName().equals("oclType") ) {
				throw new UseUnsupportedOperationException("USE doesn't support: " + TypeUtils.typeToString(src) + "." + self.getOperationName());
			}
			
			if ( self.getOperationName().equals("oclBadOperation") || self.getOperationName().equals("oclInvalid")) {
				throw new UseUnsupportedOperationException("USE doesn't support: " + TypeUtils.typeToString(src) + "." + self.getOperationName());
			}

		}
		
		/**
		 * EMF OCL manual: 3.12. OclElement
		 * "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes.
		 *  Operations defined for OclElement"
		 */
		@Override
		public void inOclModelElement(OclModelElement self) {
			// This is not available in USE, but OclElement conforms to OclAny, so let's use OclAny
			if ( self.getName().equals("OclElement")) {
				EcoreUtil.replace(self, OCLFactory.eINSTANCE.createOclAnyType());
			}

			// This should be handled by AnATLyzer's normalisation??
			String replacement = UseReservedWords.getReplacement(self.getName());
			if ( replacement != null ) {
				self.setName(replacement);
				Type t = self.getInferredType();
				if ( t instanceof Metaclass ) {
					((Metaclass) t).setName(replacement);
					((Metaclass) t).getKlass().setName(replacement);
				}
			}
		}

		private CollectionKind toCollectionKind(CollectionType c) {
			if ( c instanceof SequenceType ) 
				return CollectionKind.SEQUENCE;
			else if ( c instanceof SetType ) 
				return CollectionKind.SET;
			else if ( c instanceof OrderedSetType ) 
				return CollectionKind.ORDERED_SET;
			else if ( c instanceof BagType ) 
				return CollectionKind.BAG;
			else
				throw new UnsupportedOperationException("Not supported " + c);
		}

		private OclType toATLCollectionType(CollectionKind kind, OclType nested) {
			anatlyzer.atlext.OCL.CollectionType t;
			switch (kind) {
			case SET: 
				t = OCLFactory.eINSTANCE.createSetType();
				break;
			case SEQUENCE: 
				t = OCLFactory.eINSTANCE.createSequenceType();
				break;
			case BAG: 
				t = OCLFactory.eINSTANCE.createBagType();
				break;
			case ORDERED_SET: 
				t = OCLFactory.eINSTANCE.createOrderedSetType();
				break;
			default:
				throw new IllegalStateException();
			}

			t.setElementType(nested);
			return t;
		}


		private CollectionKind getColAttr(EObject self) {
			return collectionAttr.get(self);
		}
		
		private void setColAttr(EObject self, CollectionKind kind) {
			collectionAttr.put(self, kind);
		}
	}

	
}
