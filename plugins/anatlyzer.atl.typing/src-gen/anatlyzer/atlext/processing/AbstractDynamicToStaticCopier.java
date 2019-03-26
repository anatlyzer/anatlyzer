package anatlyzer.atlext.processing;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

@SuppressWarnings("unchecked")
public class AbstractDynamicToStaticCopier {
	protected boolean _debug = false;
	protected HashMap<EObject, EObject> trace = new HashMap<EObject, EObject>();
	
	public void copyResource(Resource r, Resource target) {
		TreeIterator<EObject> it = r.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			EObject copied = copy(obj);
			if ( obj.eContainer() == null ) {
				target.getContents().add(copied);
			}
		}
		
		it = r.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			setCrossRefs(obj);
		}
		
	}
	
	public EObject getTarget(EObject src) {
		EObject tgt = trace.get(src);
		if ( tgt == null ) throw new IllegalStateException(src + " not found.");
		return tgt;
	}
	
	protected anatlyzer.atlext.OCL.OclContextDefinition createOclContextDefinition(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclContextDefinition();
	}
	protected anatlyzer.atlext.OCL.RealExp createRealExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createRealExp();
	}
	protected anatlyzer.atlext.ATL.LazyRule createLazyRule(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createLazyRule();
	}
	protected anatlyzer.atlext.ATL.RuleVariableDeclaration createRuleVariableDeclaration(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createRuleVariableDeclaration();
	}
	protected anatlyzer.atlext.OCL.BooleanExp createBooleanExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createBooleanExp();
	}
	protected anatlyzer.atlext.OCL.OclModelElement createOclModelElement(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclModelElement();
	}
	protected anatlyzer.atlext.ATL.ExpressionStat createExpressionStat(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createExpressionStat();
	}
	protected anatlyzer.atlext.ATL.ForEachOutPatternElement createForEachOutPatternElement(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createForEachOutPatternElement();
	}
	protected anatlyzer.atlext.OCL.IntegerType createIntegerType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIntegerType();
	}
	protected anatlyzer.atlext.ATL.Module createModule(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createModule();
	}
	protected anatlyzer.atlext.OCL.VariableExp createVariableExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createVariableExp();
	}
	protected anatlyzer.atlext.OCL.Attribute createAttribute(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createAttribute();
	}
	protected anatlyzer.atlext.OCL.Parameter createParameter(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createParameter();
	}
	protected anatlyzer.atlext.OCL.SuperExp createSuperExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createSuperExp();
	}
	protected anatlyzer.atlext.ATL.InPattern createInPattern(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createInPattern();
	}
	protected anatlyzer.atlext.OCL.OclType createOclType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclType();
	}
	protected anatlyzer.atlext.OCL.BagType createBagType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createBagType();
	}
	protected anatlyzer.atlext.ATL.ActionBlock createActionBlock(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createActionBlock();
	}
	protected anatlyzer.atlext.ATL.DropPattern createDropPattern(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createDropPattern();
	}
	protected anatlyzer.atlext.OCL.VariableDeclaration createVariableDeclaration(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createVariableDeclaration();
	}
	protected anatlyzer.atlext.OCL.OperatorCallExp createOperatorCallExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOperatorCallExp();
	}
	protected anatlyzer.atlext.ATL.BindingStat createBindingStat(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createBindingStat();
	}
	protected anatlyzer.atlext.ATL.IfStat createIfStat(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createIfStat();
	}
	protected anatlyzer.atlext.OCL.OrderedSetExp createOrderedSetExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOrderedSetExp();
	}
	protected anatlyzer.atlext.OCL.OclFeatureDefinition createOclFeatureDefinition(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclFeatureDefinition();
	}
	protected anatlyzer.atlext.ATL.SimpleOutPatternElement createSimpleOutPatternElement(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createSimpleOutPatternElement();
	}
	protected anatlyzer.atlext.OCL.OclModel createOclModel(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclModel();
	}
	protected anatlyzer.atlext.ATL.Library createLibrary(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createLibrary();
	}
	protected anatlyzer.atlext.OCL.EnumLiteralExp createEnumLiteralExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createEnumLiteralExp();
	}
	protected anatlyzer.atlext.OCL.BagExp createBagExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createBagExp();
	}
	protected anatlyzer.atlext.ATL.Binding createBinding(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createBinding();
	}
	protected anatlyzer.atlext.OCL.OperationCallExp createOperationCallExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOperationCallExp();
	}
	protected anatlyzer.atlext.ATL.Unit createUnit(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createUnit();
	}
	protected anatlyzer.atlext.OCL.Operation createOperation(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOperation();
	}
	protected anatlyzer.atlext.OCL.CollectionType createCollectionType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createCollectionType();
	}
	protected anatlyzer.atlext.OCL.OclAnyType createOclAnyType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclAnyType();
	}
	protected anatlyzer.atlext.OCL.StringType createStringType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createStringType();
	}
	protected anatlyzer.atlext.OCL.MapType createMapType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createMapType();
	}
	protected anatlyzer.atlext.OCL.BooleanType createBooleanType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createBooleanType();
	}
	protected anatlyzer.atlext.OCL.NavigationOrAttributeCallExp createNavigationOrAttributeCallExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
	}
	protected anatlyzer.atlext.OCL.SequenceType createSequenceType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createSequenceType();
	}
	protected anatlyzer.atlext.ATL.Helper createHelper(EObject original) { 
		throw new UnsupportedOperationException("Implmented by subclass");
	}
	protected anatlyzer.atlext.OCL.StringExp createStringExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createStringExp();
	}
	protected anatlyzer.atlext.OCL.SequenceExp createSequenceExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createSequenceExp();
	}
	protected anatlyzer.atlext.ATL.ForStat createForStat(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createForStat();
	}
	protected anatlyzer.atlext.ATL.LibraryRef createLibraryRef(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createLibraryRef();
	}
	protected anatlyzer.atlext.OCL.Iterator createIterator(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIterator();
	}
	protected anatlyzer.atlext.OCL.IterateExp createIterateExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIterateExp();
	}
	protected anatlyzer.atlext.OCL.IntegerExp createIntegerExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIntegerExp();
	}
	protected anatlyzer.atlext.OCL.SetType createSetType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createSetType();
	}
	protected anatlyzer.atlext.OCL.LetExp createLetExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createLetExp();
	}
	protected anatlyzer.atlext.ATL.Query createQuery(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createQuery();
	}
	protected anatlyzer.atlext.OCL.MapElement createMapElement(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createMapElement();
	}
	protected anatlyzer.atlext.ATL.MatchedRule createMatchedRule(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createMatchedRule();
	}
	protected anatlyzer.atlext.ATL.CalledRule createCalledRule(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createCalledRule();
	}
	protected anatlyzer.atlext.OCL.CollectionOperationCallExp createCollectionOperationCallExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createCollectionOperationCallExp();
	}
	protected anatlyzer.atlext.OCL.SetExp createSetExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createSetExp();
	}
	protected anatlyzer.atlext.OCL.OrderedSetType createOrderedSetType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOrderedSetType();
	}
	protected anatlyzer.atlext.ATL.OutPattern createOutPattern(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createOutPattern();
	}
	protected anatlyzer.atlext.OCL.TupleExp createTupleExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createTupleExp();
	}
	protected anatlyzer.atlext.OCL.IfExp createIfExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIfExp();
	}
	protected anatlyzer.atlext.ATL.SimpleInPatternElement createSimpleInPatternElement(EObject original) { 
		return anatlyzer.atlext.ATL.ATLFactory.eINSTANCE.createSimpleInPatternElement();
	}
	protected anatlyzer.atlext.OCL.RealType createRealType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createRealType();
	}
	protected anatlyzer.atlext.OCL.TupleType createTupleType(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createTupleType();
	}
	protected anatlyzer.atlext.OCL.OclUndefinedExp createOclUndefinedExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createOclUndefinedExp();
	}
	protected anatlyzer.atlext.OCL.TuplePart createTuplePart(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createTuplePart();
	}
	protected anatlyzer.atlext.OCL.IteratorExp createIteratorExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createIteratorExp();
	}
	protected anatlyzer.atlext.OCL.MapExp createMapExp(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createMapExp();
	}
	protected anatlyzer.atlext.OCL.TupleTypeAttribute createTupleTypeAttribute(EObject original) { 
		return anatlyzer.atlext.OCL.OCLFactory.eINSTANCE.createTupleTypeAttribute();
	}
	
	protected anatlyzer.atlext.OCL.OclContextDefinition copyOclContextDefinition(EObject object) { 
		anatlyzer.atlext.OCL.OclContextDefinition newObj = createOclContextDefinition(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.RealExp copyRealExp(EObject object) { 
		anatlyzer.atlext.OCL.RealExp newObj = createRealExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("realSymbol");
			v = object.eGet(f);
	    if ( v != null ) newObj.setRealSymbol( (Double) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.LazyRule copyLazyMatchedRule(EObject object) { 
		anatlyzer.atlext.ATL.LazyRule newObj = createLazyRule(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isAbstract");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsAbstract( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isRefining");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsRefining( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isNoDefault");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsNoDefault( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isUnique");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsUnique( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.RuleVariableDeclaration copyRuleVariableDeclaration(EObject object) { 
		anatlyzer.atlext.ATL.RuleVariableDeclaration newObj = createRuleVariableDeclaration(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BooleanExp copyBooleanExp(EObject object) { 
		anatlyzer.atlext.OCL.BooleanExp newObj = createBooleanExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("booleanSymbol");
			v = object.eGet(f);
	    if ( v != null ) newObj.setBooleanSymbol( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclModelElement copyOclModelElement(EObject object) { 
		anatlyzer.atlext.OCL.OclModelElement newObj = createOclModelElement(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ExpressionStat copyExpressionStat(EObject object) { 
		anatlyzer.atlext.ATL.ExpressionStat newObj = createExpressionStat(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ForEachOutPatternElement copyForEachOutPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.ForEachOutPatternElement newObj = createForEachOutPatternElement(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IntegerType copyIntegerType(EObject object) { 
		anatlyzer.atlext.OCL.IntegerType newObj = createIntegerType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Module copyModule(EObject object) { 
		anatlyzer.atlext.ATL.Module newObj = createModule(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isRefining");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsRefining( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.VariableExp copyVariableExp(EObject object) { 
		anatlyzer.atlext.OCL.VariableExp newObj = createVariableExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Attribute copyAttribute(EObject object) { 
		anatlyzer.atlext.OCL.Attribute newObj = createAttribute(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Parameter copyParameter(EObject object) { 
		anatlyzer.atlext.OCL.Parameter newObj = createParameter(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SuperExp copySuperExp(EObject object) { 
		anatlyzer.atlext.OCL.SuperExp newObj = createSuperExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.InPattern copyInPattern(EObject object) { 
		anatlyzer.atlext.ATL.InPattern newObj = createInPattern(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclType copyOclType(EObject object) { 
		anatlyzer.atlext.OCL.OclType newObj = createOclType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BagType copyBagType(EObject object) { 
		anatlyzer.atlext.OCL.BagType newObj = createBagType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ActionBlock copyActionBlock(EObject object) { 
		anatlyzer.atlext.ATL.ActionBlock newObj = createActionBlock(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.DropPattern copyDropPattern(EObject object) { 
		anatlyzer.atlext.ATL.DropPattern newObj = createDropPattern(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.VariableDeclaration copyVariableDeclaration(EObject object) { 
		anatlyzer.atlext.OCL.VariableDeclaration newObj = createVariableDeclaration(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OperatorCallExp copyOperatorCallExp(EObject object) { 
		anatlyzer.atlext.OCL.OperatorCallExp newObj = createOperatorCallExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("operationName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setOperationName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.BindingStat copyBindingStat(EObject object) { 
		anatlyzer.atlext.ATL.BindingStat newObj = createBindingStat(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("propertyName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setPropertyName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isAssignment");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsAssignment( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.IfStat copyIfStat(EObject object) { 
		anatlyzer.atlext.ATL.IfStat newObj = createIfStat(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OrderedSetExp copyOrderedSetExp(EObject object) { 
		anatlyzer.atlext.OCL.OrderedSetExp newObj = createOrderedSetExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclFeatureDefinition copyOclFeatureDefinition(EObject object) { 
		anatlyzer.atlext.OCL.OclFeatureDefinition newObj = createOclFeatureDefinition(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.SimpleOutPatternElement copySimpleOutPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.SimpleOutPatternElement newObj = createSimpleOutPatternElement(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclModel copyOclModel(EObject object) { 
		anatlyzer.atlext.OCL.OclModel newObj = createOclModel(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Library copyLibrary(EObject object) { 
		anatlyzer.atlext.ATL.Library newObj = createLibrary(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.EnumLiteralExp copyEnumLiteralExp(EObject object) { 
		anatlyzer.atlext.OCL.EnumLiteralExp newObj = createEnumLiteralExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BagExp copyBagExp(EObject object) { 
		anatlyzer.atlext.OCL.BagExp newObj = createBagExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Binding copyBinding(EObject object) { 
		anatlyzer.atlext.ATL.Binding newObj = createBinding(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("propertyName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setPropertyName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isAssignment");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsAssignment( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OperationCallExp copyOperationCallExp(EObject object) { 
		anatlyzer.atlext.OCL.OperationCallExp newObj = createOperationCallExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("operationName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setOperationName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Unit copyUnit(EObject object) { 
		anatlyzer.atlext.ATL.Unit newObj = createUnit(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Operation copyOperation(EObject object) { 
		anatlyzer.atlext.OCL.Operation newObj = createOperation(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.CollectionType copyCollectionType(EObject object) { 
		anatlyzer.atlext.OCL.CollectionType newObj = createCollectionType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclAnyType copyOclAnyType(EObject object) { 
		anatlyzer.atlext.OCL.OclAnyType newObj = createOclAnyType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.StringType copyStringType(EObject object) { 
		anatlyzer.atlext.OCL.StringType newObj = createStringType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapType copyMapType(EObject object) { 
		anatlyzer.atlext.OCL.MapType newObj = createMapType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BooleanType copyBooleanType(EObject object) { 
		anatlyzer.atlext.OCL.BooleanType newObj = createBooleanType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.NavigationOrAttributeCallExp copyNavigationOrAttributeCallExp(EObject object) { 
		anatlyzer.atlext.OCL.NavigationOrAttributeCallExp newObj = createNavigationOrAttributeCallExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SequenceType copySequenceType(EObject object) { 
		anatlyzer.atlext.OCL.SequenceType newObj = createSequenceType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Helper copyHelper(EObject object) { 
		anatlyzer.atlext.ATL.Helper newObj = createHelper(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.StringExp copyStringExp(EObject object) { 
		anatlyzer.atlext.OCL.StringExp newObj = createStringExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("stringSymbol");
			v = object.eGet(f);
	    if ( v != null ) newObj.setStringSymbol( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SequenceExp copySequenceExp(EObject object) { 
		anatlyzer.atlext.OCL.SequenceExp newObj = createSequenceExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ForStat copyForStat(EObject object) { 
		anatlyzer.atlext.ATL.ForStat newObj = createForStat(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.LibraryRef copyLibraryRef(EObject object) { 
		anatlyzer.atlext.ATL.LibraryRef newObj = createLibraryRef(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Iterator copyIterator(EObject object) { 
		anatlyzer.atlext.OCL.Iterator newObj = createIterator(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IterateExp copyIterateExp(EObject object) { 
		anatlyzer.atlext.OCL.IterateExp newObj = createIterateExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IntegerExp copyIntegerExp(EObject object) { 
		anatlyzer.atlext.OCL.IntegerExp newObj = createIntegerExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("integerSymbol");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIntegerSymbol( (Integer) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SetType copySetType(EObject object) { 
		anatlyzer.atlext.OCL.SetType newObj = createSetType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.LetExp copyLetExp(EObject object) { 
		anatlyzer.atlext.OCL.LetExp newObj = createLetExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Query copyQuery(EObject object) { 
		anatlyzer.atlext.ATL.Query newObj = createQuery(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapElement copyMapElement(EObject object) { 
		anatlyzer.atlext.OCL.MapElement newObj = createMapElement(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.MatchedRule copyMatchedRule(EObject object) { 
		anatlyzer.atlext.ATL.MatchedRule newObj = createMatchedRule(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isAbstract");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsAbstract( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isRefining");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsRefining( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isNoDefault");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsNoDefault( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.CalledRule copyCalledRule(EObject object) { 
		anatlyzer.atlext.ATL.CalledRule newObj = createCalledRule(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("isEntrypoint");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsEntrypoint( (Boolean) v );
						f = object.eClass().getEStructuralFeature("isEndpoint");
			v = object.eGet(f);
	    if ( v != null ) newObj.setIsEndpoint( (Boolean) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.CollectionOperationCallExp copyCollectionOperationCallExp(EObject object) { 
		anatlyzer.atlext.OCL.CollectionOperationCallExp newObj = createCollectionOperationCallExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("operationName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setOperationName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SetExp copySetExp(EObject object) { 
		anatlyzer.atlext.OCL.SetExp newObj = createSetExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OrderedSetType copyOrderedSetType(EObject object) { 
		anatlyzer.atlext.OCL.OrderedSetType newObj = createOrderedSetType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.OutPattern copyOutPattern(EObject object) { 
		anatlyzer.atlext.ATL.OutPattern newObj = createOutPattern(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleExp copyTupleExp(EObject object) { 
		anatlyzer.atlext.OCL.TupleExp newObj = createTupleExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IfExp copyIfExp(EObject object) { 
		anatlyzer.atlext.OCL.IfExp newObj = createIfExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.SimpleInPatternElement copySimpleInPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.SimpleInPatternElement newObj = createSimpleInPatternElement(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.RealType copyRealType(EObject object) { 
		anatlyzer.atlext.OCL.RealType newObj = createRealType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleType copyTupleType(EObject object) { 
		anatlyzer.atlext.OCL.TupleType newObj = createTupleType(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclUndefinedExp copyOclUndefinedExp(EObject object) { 
		anatlyzer.atlext.OCL.OclUndefinedExp newObj = createOclUndefinedExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TuplePart copyTuplePart(EObject object) { 
		anatlyzer.atlext.OCL.TuplePart newObj = createTuplePart(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("id");
			v = object.eGet(f);
	    if ( v != null ) newObj.setId( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("varName");
			v = object.eGet(f);
	    if ( v != null ) newObj.setVarName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IteratorExp copyIteratorExp(EObject object) { 
		anatlyzer.atlext.OCL.IteratorExp newObj = createIteratorExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapExp copyMapExp(EObject object) { 
		anatlyzer.atlext.OCL.MapExp newObj = createMapExp(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
				
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleTypeAttribute copyTupleTypeAttribute(EObject object) { 
		anatlyzer.atlext.OCL.TupleTypeAttribute newObj = createTupleTypeAttribute(object);
		EStructuralFeature f = null;
		Object v = null;
			f = object.eClass().getEStructuralFeature("location");
			v = object.eGet(f);
	    if ( v != null ) newObj.setLocation( (java.lang.String) v );
						f = object.eClass().getEStructuralFeature("commentsBefore");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsBefore().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("commentsAfter");
		for(Object child : ((List<EObject>) object.eGet(f))) {
		    newObj.getCommentsAfter().add( (java.lang.String) child);
		}
							f = object.eClass().getEStructuralFeature("name");
			v = object.eGet(f);
	    if ( v != null ) newObj.setName( (java.lang.String) v );
			
		customCopy(object, newObj);
	
		trace.put(object, newObj);
		return newObj;
	} 
	
    /** 
      * Can be implemented by subclasses to add specific copy behaviours
      */
    protected void customCopy(EObject source, EObject target) { }

	protected anatlyzer.atlext.OCL.OclContextDefinition setCrossRefsOclContextDefinition(EObject object) { 
		anatlyzer.atlext.OCL.OclContextDefinition newObj = (anatlyzer.atlext.OCL.OclContextDefinition) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("definition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinition( (anatlyzer.atlext.OCL.OclFeatureDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("context_");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setContext_( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.RealExp setCrossRefsRealExp(EObject object) { 
		anatlyzer.atlext.OCL.RealExp newObj = (anatlyzer.atlext.OCL.RealExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.LazyRule setCrossRefsLazyMatchedRule(EObject object) { 
		anatlyzer.atlext.ATL.LazyRule newObj = (anatlyzer.atlext.ATL.LazyRule) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("actionBlock");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setActionBlock( (anatlyzer.atlext.ATL.ActionBlock) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variables");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariables().add( (anatlyzer.atlext.ATL.RuleVariableDeclaration) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("inPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInPattern( (anatlyzer.atlext.ATL.InPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("children");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getChildren().add( (anatlyzer.atlext.ATL.RuleWithPattern) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("superRule");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSuperRule( (anatlyzer.atlext.ATL.RuleWithPattern) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.RuleVariableDeclaration setCrossRefsRuleVariableDeclaration(EObject object) { 
		anatlyzer.atlext.ATL.RuleVariableDeclaration newObj = (anatlyzer.atlext.ATL.RuleVariableDeclaration) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("rule");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setRule( (anatlyzer.atlext.ATL.Rule) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BooleanExp setCrossRefsBooleanExp(EObject object) { 
		anatlyzer.atlext.OCL.BooleanExp newObj = (anatlyzer.atlext.OCL.BooleanExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclModelElement setCrossRefsOclModelElement(EObject object) { 
		anatlyzer.atlext.OCL.OclModelElement newObj = (anatlyzer.atlext.OCL.OclModelElement) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("model");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setModel( (anatlyzer.atlext.OCL.OclModel) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ExpressionStat setCrossRefsExpressionStat(EObject object) { 
		anatlyzer.atlext.ATL.ExpressionStat newObj = (anatlyzer.atlext.ATL.ExpressionStat) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("expression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ForEachOutPatternElement setCrossRefsForEachOutPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.ForEachOutPatternElement newObj = (anatlyzer.atlext.ATL.ForEachOutPatternElement) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("sourceElement");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSourceElement( (anatlyzer.atlext.ATL.InPatternElement) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("bindings");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getBindings().add( (anatlyzer.atlext.ATL.Binding) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("model");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setModel( (anatlyzer.atlext.OCL.OclModel) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("iterator");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIterator( (anatlyzer.atlext.OCL.Iterator) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IntegerType setCrossRefsIntegerType(EObject object) { 
		anatlyzer.atlext.OCL.IntegerType newObj = (anatlyzer.atlext.OCL.IntegerType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Module setCrossRefsModule(EObject object) { 
		anatlyzer.atlext.ATL.Module newObj = (anatlyzer.atlext.ATL.Module) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("libraries");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getLibraries().add( (anatlyzer.atlext.ATL.LibraryRef) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("inModels");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getInModels().add( (anatlyzer.atlext.OCL.OclModel) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("outModels");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getOutModels().add( (anatlyzer.atlext.OCL.OclModel) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.ATL.ModuleElement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.VariableExp setCrossRefsVariableExp(EObject object) { 
		anatlyzer.atlext.OCL.VariableExp newObj = (anatlyzer.atlext.OCL.VariableExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("referredVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setReferredVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Attribute setCrossRefsAttribute(EObject object) { 
		anatlyzer.atlext.OCL.Attribute newObj = (anatlyzer.atlext.OCL.Attribute) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("definition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinition( (anatlyzer.atlext.OCL.OclFeatureDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Parameter setCrossRefsParameter(EObject object) { 
		anatlyzer.atlext.OCL.Parameter newObj = (anatlyzer.atlext.OCL.Parameter) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SuperExp setCrossRefsSuperExp(EObject object) { 
		anatlyzer.atlext.OCL.SuperExp newObj = (anatlyzer.atlext.OCL.SuperExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.InPattern setCrossRefsInPattern(EObject object) { 
		anatlyzer.atlext.ATL.InPattern newObj = (anatlyzer.atlext.ATL.InPattern) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.ATL.InPatternElement) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("filter");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setFilter( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclType setCrossRefsOclType(EObject object) { 
		anatlyzer.atlext.OCL.OclType newObj = (anatlyzer.atlext.OCL.OclType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BagType setCrossRefsBagType(EObject object) { 
		anatlyzer.atlext.OCL.BagType newObj = (anatlyzer.atlext.OCL.BagType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elementType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElementType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ActionBlock setCrossRefsActionBlock(EObject object) { 
		anatlyzer.atlext.ATL.ActionBlock newObj = (anatlyzer.atlext.ATL.ActionBlock) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("rule");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setRule( (anatlyzer.atlext.ATL.Rule) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("statements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getStatements().add( (anatlyzer.atlext.ATL.Statement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.DropPattern setCrossRefsDropPattern(EObject object) { 
		anatlyzer.atlext.ATL.DropPattern newObj = (anatlyzer.atlext.ATL.DropPattern) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.VariableDeclaration setCrossRefsVariableDeclaration(EObject object) { 
		anatlyzer.atlext.OCL.VariableDeclaration newObj = (anatlyzer.atlext.OCL.VariableDeclaration) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OperatorCallExp setCrossRefsOperatorCallExp(EObject object) { 
		anatlyzer.atlext.OCL.OperatorCallExp newObj = (anatlyzer.atlext.OCL.OperatorCallExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("arguments");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getArguments().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.BindingStat setCrossRefsBindingStat(EObject object) { 
		anatlyzer.atlext.ATL.BindingStat newObj = (anatlyzer.atlext.ATL.BindingStat) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("value");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setValue( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.IfStat setCrossRefsIfStat(EObject object) { 
		anatlyzer.atlext.ATL.IfStat newObj = (anatlyzer.atlext.ATL.IfStat) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("condition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCondition( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("thenStatements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getThenStatements().add( (anatlyzer.atlext.ATL.Statement) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("elseStatements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElseStatements().add( (anatlyzer.atlext.ATL.Statement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OrderedSetExp setCrossRefsOrderedSetExp(EObject object) { 
		anatlyzer.atlext.OCL.OrderedSetExp newObj = (anatlyzer.atlext.OCL.OrderedSetExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclFeatureDefinition setCrossRefsOclFeatureDefinition(EObject object) { 
		anatlyzer.atlext.OCL.OclFeatureDefinition newObj = (anatlyzer.atlext.OCL.OclFeatureDefinition) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("feature");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setFeature( (anatlyzer.atlext.OCL.OclFeature) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("context_");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setContext_( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.SimpleOutPatternElement setCrossRefsSimpleOutPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.SimpleOutPatternElement newObj = (anatlyzer.atlext.ATL.SimpleOutPatternElement) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("sourceElement");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSourceElement( (anatlyzer.atlext.ATL.InPatternElement) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("bindings");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getBindings().add( (anatlyzer.atlext.ATL.Binding) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("model");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setModel( (anatlyzer.atlext.OCL.OclModel) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("reverseBindings");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getReverseBindings().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclModel setCrossRefsOclModel(EObject object) { 
		anatlyzer.atlext.OCL.OclModel newObj = (anatlyzer.atlext.OCL.OclModel) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("metamodel");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMetamodel( (anatlyzer.atlext.OCL.OclModel) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.OclModelElement) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("model");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getModel().add( (anatlyzer.atlext.OCL.OclModel) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Library setCrossRefsLibrary(EObject object) { 
		anatlyzer.atlext.ATL.Library newObj = (anatlyzer.atlext.ATL.Library) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("libraries");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getLibraries().add( (anatlyzer.atlext.ATL.LibraryRef) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("helpers");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getHelpers().add( (anatlyzer.atlext.ATL.Helper) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.EnumLiteralExp setCrossRefsEnumLiteralExp(EObject object) { 
		anatlyzer.atlext.OCL.EnumLiteralExp newObj = (anatlyzer.atlext.OCL.EnumLiteralExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BagExp setCrossRefsBagExp(EObject object) { 
		anatlyzer.atlext.OCL.BagExp newObj = (anatlyzer.atlext.OCL.BagExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Binding setCrossRefsBinding(EObject object) { 
		anatlyzer.atlext.ATL.Binding newObj = (anatlyzer.atlext.ATL.Binding) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("value");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setValue( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("outPatternElement");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPatternElement( (anatlyzer.atlext.ATL.OutPatternElement) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OperationCallExp setCrossRefsOperationCallExp(EObject object) { 
		anatlyzer.atlext.OCL.OperationCallExp newObj = (anatlyzer.atlext.OCL.OperationCallExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("arguments");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getArguments().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Unit setCrossRefsUnit(EObject object) { 
		anatlyzer.atlext.ATL.Unit newObj = (anatlyzer.atlext.ATL.Unit) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("libraries");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getLibraries().add( (anatlyzer.atlext.ATL.LibraryRef) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Operation setCrossRefsOperation(EObject object) { 
		anatlyzer.atlext.OCL.Operation newObj = (anatlyzer.atlext.OCL.Operation) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("definition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinition( (anatlyzer.atlext.OCL.OclFeatureDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parameters");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getParameters().add( (anatlyzer.atlext.OCL.Parameter) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("returnType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setReturnType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("body");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBody( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.CollectionType setCrossRefsCollectionType(EObject object) { 
		anatlyzer.atlext.OCL.CollectionType newObj = (anatlyzer.atlext.OCL.CollectionType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elementType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElementType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclAnyType setCrossRefsOclAnyType(EObject object) { 
		anatlyzer.atlext.OCL.OclAnyType newObj = (anatlyzer.atlext.OCL.OclAnyType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.StringType setCrossRefsStringType(EObject object) { 
		anatlyzer.atlext.OCL.StringType newObj = (anatlyzer.atlext.OCL.StringType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapType setCrossRefsMapType(EObject object) { 
		anatlyzer.atlext.OCL.MapType newObj = (anatlyzer.atlext.OCL.MapType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("valueType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setValueType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("keyType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setKeyType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.BooleanType setCrossRefsBooleanType(EObject object) { 
		anatlyzer.atlext.OCL.BooleanType newObj = (anatlyzer.atlext.OCL.BooleanType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.NavigationOrAttributeCallExp setCrossRefsNavigationOrAttributeCallExp(EObject object) { 
		anatlyzer.atlext.OCL.NavigationOrAttributeCallExp newObj = (anatlyzer.atlext.OCL.NavigationOrAttributeCallExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SequenceType setCrossRefsSequenceType(EObject object) { 
		anatlyzer.atlext.OCL.SequenceType newObj = (anatlyzer.atlext.OCL.SequenceType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elementType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElementType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Helper setCrossRefsHelper(EObject object) { 
		anatlyzer.atlext.ATL.Helper newObj = (anatlyzer.atlext.ATL.Helper) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("query");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setQuery( (anatlyzer.atlext.ATL.Query) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("library");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLibrary( (anatlyzer.atlext.ATL.Library) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinition( (anatlyzer.atlext.OCL.OclFeatureDefinition) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.StringExp setCrossRefsStringExp(EObject object) { 
		anatlyzer.atlext.OCL.StringExp newObj = (anatlyzer.atlext.OCL.StringExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SequenceExp setCrossRefsSequenceExp(EObject object) { 
		anatlyzer.atlext.OCL.SequenceExp newObj = (anatlyzer.atlext.OCL.SequenceExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.ForStat setCrossRefsForStat(EObject object) { 
		anatlyzer.atlext.ATL.ForStat newObj = (anatlyzer.atlext.ATL.ForStat) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("iterator");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIterator( (anatlyzer.atlext.OCL.Iterator) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("statements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getStatements().add( (anatlyzer.atlext.ATL.Statement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.LibraryRef setCrossRefsLibraryRef(EObject object) { 
		anatlyzer.atlext.ATL.LibraryRef newObj = (anatlyzer.atlext.ATL.LibraryRef) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("unit");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setUnit( (anatlyzer.atlext.ATL.Unit) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.Iterator setCrossRefsIterator(EObject object) { 
		anatlyzer.atlext.OCL.Iterator newObj = (anatlyzer.atlext.OCL.Iterator) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("loopExpr");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExpr( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IterateExp setCrossRefsIterateExp(EObject object) { 
		anatlyzer.atlext.OCL.IterateExp newObj = (anatlyzer.atlext.OCL.IterateExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("body");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBody( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("iterators");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getIterators().add( (anatlyzer.atlext.OCL.Iterator) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("result");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setResult( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IntegerExp setCrossRefsIntegerExp(EObject object) { 
		anatlyzer.atlext.OCL.IntegerExp newObj = (anatlyzer.atlext.OCL.IntegerExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SetType setCrossRefsSetType(EObject object) { 
		anatlyzer.atlext.OCL.SetType newObj = (anatlyzer.atlext.OCL.SetType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elementType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElementType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.LetExp setCrossRefsLetExp(EObject object) { 
		anatlyzer.atlext.OCL.LetExp newObj = (anatlyzer.atlext.OCL.LetExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("in_");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIn_( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.Query setCrossRefsQuery(EObject object) { 
		anatlyzer.atlext.ATL.Query newObj = (anatlyzer.atlext.ATL.Query) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("libraries");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getLibraries().add( (anatlyzer.atlext.ATL.LibraryRef) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("body");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBody( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("helpers");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getHelpers().add( (anatlyzer.atlext.ATL.Helper) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapElement setCrossRefsMapElement(EObject object) { 
		anatlyzer.atlext.OCL.MapElement newObj = (anatlyzer.atlext.OCL.MapElement) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("map");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMap( (anatlyzer.atlext.OCL.MapExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("key");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setKey( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("value");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setValue( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.MatchedRule setCrossRefsMatchedRule(EObject object) { 
		anatlyzer.atlext.ATL.MatchedRule newObj = (anatlyzer.atlext.ATL.MatchedRule) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("actionBlock");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setActionBlock( (anatlyzer.atlext.ATL.ActionBlock) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variables");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariables().add( (anatlyzer.atlext.ATL.RuleVariableDeclaration) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("inPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInPattern( (anatlyzer.atlext.ATL.InPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("children");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getChildren().add( (anatlyzer.atlext.ATL.RuleWithPattern) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("superRule");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSuperRule( (anatlyzer.atlext.ATL.RuleWithPattern) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.CalledRule setCrossRefsCalledRule(EObject object) { 
		anatlyzer.atlext.ATL.CalledRule newObj = (anatlyzer.atlext.ATL.CalledRule) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("outPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOutPattern( (anatlyzer.atlext.ATL.OutPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("actionBlock");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setActionBlock( (anatlyzer.atlext.ATL.ActionBlock) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variables");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariables().add( (anatlyzer.atlext.ATL.RuleVariableDeclaration) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("parameters");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getParameters().add( (anatlyzer.atlext.OCL.Parameter) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.CollectionOperationCallExp setCrossRefsCollectionOperationCallExp(EObject object) { 
		anatlyzer.atlext.OCL.CollectionOperationCallExp newObj = (anatlyzer.atlext.OCL.CollectionOperationCallExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("arguments");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getArguments().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.SetExp setCrossRefsSetExp(EObject object) { 
		anatlyzer.atlext.OCL.SetExp newObj = (anatlyzer.atlext.OCL.SetExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.OclExpression) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OrderedSetType setCrossRefsOrderedSetType(EObject object) { 
		anatlyzer.atlext.OCL.OrderedSetType newObj = (anatlyzer.atlext.OCL.OrderedSetType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elementType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElementType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.OutPattern setCrossRefsOutPattern(EObject object) { 
		anatlyzer.atlext.ATL.OutPattern newObj = (anatlyzer.atlext.ATL.OutPattern) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("rule");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setRule( (anatlyzer.atlext.ATL.Rule) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("dropPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDropPattern( (anatlyzer.atlext.ATL.DropPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.ATL.OutPatternElement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleExp setCrossRefsTupleExp(EObject object) { 
		anatlyzer.atlext.OCL.TupleExp newObj = (anatlyzer.atlext.OCL.TupleExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tuplePart");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getTuplePart().add( (anatlyzer.atlext.OCL.TuplePart) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IfExp setCrossRefsIfExp(EObject object) { 
		anatlyzer.atlext.OCL.IfExp newObj = (anatlyzer.atlext.OCL.IfExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("thenExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setThenExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("condition");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCondition( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elseExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setElseExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.ATL.SimpleInPatternElement setCrossRefsSimpleInPatternElement(EObject object) { 
		anatlyzer.atlext.ATL.SimpleInPatternElement newObj = (anatlyzer.atlext.ATL.SimpleInPatternElement) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("mapsTo");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapsTo( (anatlyzer.atlext.ATL.OutPatternElement) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("inPattern");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInPattern( (anatlyzer.atlext.ATL.InPattern) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("models");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getModels().add( (anatlyzer.atlext.OCL.OclModel) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.RealType setCrossRefsRealType(EObject object) { 
		anatlyzer.atlext.OCL.RealType newObj = (anatlyzer.atlext.OCL.RealType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleType setCrossRefsTupleType(EObject object) { 
		anatlyzer.atlext.OCL.TupleType newObj = (anatlyzer.atlext.OCL.TupleType) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("definitions");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setDefinitions( (anatlyzer.atlext.OCL.OclContextDefinition) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("oclExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOclExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("operation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType2( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("mapType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setMapType( (anatlyzer.atlext.OCL.MapType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collectionTypes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollectionTypes( (anatlyzer.atlext.OCL.CollectionType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleTypeAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleTypeAttribute( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableDeclaration");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setVariableDeclaration( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("attributes");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getAttributes().add( (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.OclUndefinedExp setCrossRefsOclUndefinedExp(EObject object) { 
		anatlyzer.atlext.OCL.OclUndefinedExp newObj = (anatlyzer.atlext.OCL.OclUndefinedExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TuplePart setCrossRefsTuplePart(EObject object) { 
		anatlyzer.atlext.OCL.TuplePart newObj = (anatlyzer.atlext.OCL.TuplePart) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initExpression");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitExpression( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("baseExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBaseExp( (anatlyzer.atlext.OCL.IterateExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("variableExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getVariableExp().add( (anatlyzer.atlext.OCL.VariableExp) getTarget((EObject) child));
		}
						}
			f = object.eClass().getEStructuralFeature("tuple");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTuple( (anatlyzer.atlext.OCL.TupleExp) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.IteratorExp setCrossRefsIteratorExp(EObject object) { 
		anatlyzer.atlext.OCL.IteratorExp newObj = (anatlyzer.atlext.OCL.IteratorExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("source");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setSource( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("body");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setBody( (anatlyzer.atlext.OCL.OclExpression) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("iterators");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getIterators().add( (anatlyzer.atlext.OCL.Iterator) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.MapExp setCrossRefsMapExp(EObject object) { 
		anatlyzer.atlext.OCL.MapExp newObj = (anatlyzer.atlext.OCL.MapExp) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp3");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp3( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("appliedProperty");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setAppliedProperty( (anatlyzer.atlext.OCL.PropertyCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("collection");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setCollection( (anatlyzer.atlext.OCL.CollectionExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("letExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLetExp( (anatlyzer.atlext.OCL.LetExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("loopExp");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setLoopExp( (anatlyzer.atlext.OCL.LoopExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("parentOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setParentOperation( (anatlyzer.atlext.OCL.OperationCallExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("initializedVariable");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setInitializedVariable( (anatlyzer.atlext.OCL.VariableDeclaration) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp2");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp2( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningOperation");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningOperation( (anatlyzer.atlext.OCL.Operation) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("ifExp1");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setIfExp1( (anatlyzer.atlext.OCL.IfExp) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("owningAttribute");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setOwningAttribute( (anatlyzer.atlext.OCL.Attribute) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("elements");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		for(EObject child : ((List<EObject>) object.eGet(f))) {
		    newObj.getElements().add( (anatlyzer.atlext.OCL.MapElement) getTarget((EObject) child));
		}
						}
	
		trace.put(object, newObj);
		return newObj;
	} 
	protected anatlyzer.atlext.OCL.TupleTypeAttribute setCrossRefsTupleTypeAttribute(EObject object) { 
		anatlyzer.atlext.OCL.TupleTypeAttribute newObj = (anatlyzer.atlext.OCL.TupleTypeAttribute) getTarget(object);
		EStructuralFeature f = null;
		EObject v = null;
			f = object.eClass().getEStructuralFeature("type");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setType( (anatlyzer.atlext.OCL.OclType) getTarget( v ) );
					}
			f = object.eClass().getEStructuralFeature("tupleType");
		// This check is to allow versions of the meta-models to be copied
		// but should be parameterized not to check in all cases
		if ( f != null ) {
		   		v = (EObject) object.eGet(f);
		if ( v != null ) newObj.setTupleType( (anatlyzer.atlext.OCL.TupleType) getTarget( v ) );
					}
	
		trace.put(object, newObj);
		return newObj;
	} 
	
	private EObject copy(EObject child) {	
	if ( child.eClass().getName().equals("OclContextDefinition") ) return copyOclContextDefinition(child);
	if ( child.eClass().getName().equals("RealExp") ) return copyRealExp(child);
	if ( child.eClass().getName().equals("LazyMatchedRule") ) return copyLazyMatchedRule(child);
	if ( child.eClass().getName().equals("RuleVariableDeclaration") ) return copyRuleVariableDeclaration(child);
	if ( child.eClass().getName().equals("BooleanExp") ) return copyBooleanExp(child);
	if ( child.eClass().getName().equals("OclModelElement") ) return copyOclModelElement(child);
	if ( child.eClass().getName().equals("ExpressionStat") ) return copyExpressionStat(child);
	if ( child.eClass().getName().equals("ForEachOutPatternElement") ) return copyForEachOutPatternElement(child);
	if ( child.eClass().getName().equals("IntegerType") ) return copyIntegerType(child);
	if ( child.eClass().getName().equals("Module") ) return copyModule(child);
	if ( child.eClass().getName().equals("VariableExp") ) return copyVariableExp(child);
	if ( child.eClass().getName().equals("Attribute") ) return copyAttribute(child);
	if ( child.eClass().getName().equals("Parameter") ) return copyParameter(child);
	if ( child.eClass().getName().equals("SuperExp") ) return copySuperExp(child);
	if ( child.eClass().getName().equals("InPattern") ) return copyInPattern(child);
	if ( child.eClass().getName().equals("OclType") ) return copyOclType(child);
	if ( child.eClass().getName().equals("BagType") ) return copyBagType(child);
	if ( child.eClass().getName().equals("ActionBlock") ) return copyActionBlock(child);
	if ( child.eClass().getName().equals("DropPattern") ) return copyDropPattern(child);
	if ( child.eClass().getName().equals("VariableDeclaration") ) return copyVariableDeclaration(child);
	if ( child.eClass().getName().equals("OperatorCallExp") ) return copyOperatorCallExp(child);
	if ( child.eClass().getName().equals("BindingStat") ) return copyBindingStat(child);
	if ( child.eClass().getName().equals("IfStat") ) return copyIfStat(child);
	if ( child.eClass().getName().equals("OrderedSetExp") ) return copyOrderedSetExp(child);
	if ( child.eClass().getName().equals("OclFeatureDefinition") ) return copyOclFeatureDefinition(child);
	if ( child.eClass().getName().equals("SimpleOutPatternElement") ) return copySimpleOutPatternElement(child);
	if ( child.eClass().getName().equals("OclModel") ) return copyOclModel(child);
	if ( child.eClass().getName().equals("Library") ) return copyLibrary(child);
	if ( child.eClass().getName().equals("EnumLiteralExp") ) return copyEnumLiteralExp(child);
	if ( child.eClass().getName().equals("BagExp") ) return copyBagExp(child);
	if ( child.eClass().getName().equals("Binding") ) return copyBinding(child);
	if ( child.eClass().getName().equals("OperationCallExp") ) return copyOperationCallExp(child);
	if ( child.eClass().getName().equals("Unit") ) return copyUnit(child);
	if ( child.eClass().getName().equals("Operation") ) return copyOperation(child);
	if ( child.eClass().getName().equals("CollectionType") ) return copyCollectionType(child);
	if ( child.eClass().getName().equals("OclAnyType") ) return copyOclAnyType(child);
	if ( child.eClass().getName().equals("StringType") ) return copyStringType(child);
	if ( child.eClass().getName().equals("MapType") ) return copyMapType(child);
	if ( child.eClass().getName().equals("BooleanType") ) return copyBooleanType(child);
	if ( child.eClass().getName().equals("NavigationOrAttributeCallExp") ) return copyNavigationOrAttributeCallExp(child);
	if ( child.eClass().getName().equals("SequenceType") ) return copySequenceType(child);
	if ( child.eClass().getName().equals("Helper") ) return copyHelper(child);
	if ( child.eClass().getName().equals("StringExp") ) return copyStringExp(child);
	if ( child.eClass().getName().equals("SequenceExp") ) return copySequenceExp(child);
	if ( child.eClass().getName().equals("ForStat") ) return copyForStat(child);
	if ( child.eClass().getName().equals("LibraryRef") ) return copyLibraryRef(child);
	if ( child.eClass().getName().equals("Iterator") ) return copyIterator(child);
	if ( child.eClass().getName().equals("IterateExp") ) return copyIterateExp(child);
	if ( child.eClass().getName().equals("IntegerExp") ) return copyIntegerExp(child);
	if ( child.eClass().getName().equals("SetType") ) return copySetType(child);
	if ( child.eClass().getName().equals("LetExp") ) return copyLetExp(child);
	if ( child.eClass().getName().equals("Query") ) return copyQuery(child);
	if ( child.eClass().getName().equals("MapElement") ) return copyMapElement(child);
	if ( child.eClass().getName().equals("MatchedRule") ) return copyMatchedRule(child);
	if ( child.eClass().getName().equals("CalledRule") ) return copyCalledRule(child);
	if ( child.eClass().getName().equals("CollectionOperationCallExp") ) return copyCollectionOperationCallExp(child);
	if ( child.eClass().getName().equals("SetExp") ) return copySetExp(child);
	if ( child.eClass().getName().equals("OrderedSetType") ) return copyOrderedSetType(child);
	if ( child.eClass().getName().equals("OutPattern") ) return copyOutPattern(child);
	if ( child.eClass().getName().equals("TupleExp") ) return copyTupleExp(child);
	if ( child.eClass().getName().equals("IfExp") ) return copyIfExp(child);
	if ( child.eClass().getName().equals("SimpleInPatternElement") ) return copySimpleInPatternElement(child);
	if ( child.eClass().getName().equals("RealType") ) return copyRealType(child);
	if ( child.eClass().getName().equals("TupleType") ) return copyTupleType(child);
	if ( child.eClass().getName().equals("OclUndefinedExp") ) return copyOclUndefinedExp(child);
	if ( child.eClass().getName().equals("TuplePart") ) return copyTuplePart(child);
	if ( child.eClass().getName().equals("IteratorExp") ) return copyIteratorExp(child);
	if ( child.eClass().getName().equals("MapExp") ) return copyMapExp(child);
	if ( child.eClass().getName().equals("TupleTypeAttribute") ) return copyTupleTypeAttribute(child);
		throw new IllegalStateException("Cannot handle class: " + child.eClass().getName());
	}
	
	private void setCrossRefs(EObject child) {	
	if ( child.eClass().getName().equals("OclContextDefinition") ) { setCrossRefsOclContextDefinition(child); return; }
	if ( child.eClass().getName().equals("RealExp") ) { setCrossRefsRealExp(child); return; }
	if ( child.eClass().getName().equals("LazyMatchedRule") ) { setCrossRefsLazyMatchedRule(child); return; }
	if ( child.eClass().getName().equals("RuleVariableDeclaration") ) { setCrossRefsRuleVariableDeclaration(child); return; }
	if ( child.eClass().getName().equals("BooleanExp") ) { setCrossRefsBooleanExp(child); return; }
	if ( child.eClass().getName().equals("OclModelElement") ) { setCrossRefsOclModelElement(child); return; }
	if ( child.eClass().getName().equals("ExpressionStat") ) { setCrossRefsExpressionStat(child); return; }
	if ( child.eClass().getName().equals("ForEachOutPatternElement") ) { setCrossRefsForEachOutPatternElement(child); return; }
	if ( child.eClass().getName().equals("IntegerType") ) { setCrossRefsIntegerType(child); return; }
	if ( child.eClass().getName().equals("Module") ) { setCrossRefsModule(child); return; }
	if ( child.eClass().getName().equals("VariableExp") ) { setCrossRefsVariableExp(child); return; }
	if ( child.eClass().getName().equals("Attribute") ) { setCrossRefsAttribute(child); return; }
	if ( child.eClass().getName().equals("Parameter") ) { setCrossRefsParameter(child); return; }
	if ( child.eClass().getName().equals("SuperExp") ) { setCrossRefsSuperExp(child); return; }
	if ( child.eClass().getName().equals("InPattern") ) { setCrossRefsInPattern(child); return; }
	if ( child.eClass().getName().equals("OclType") ) { setCrossRefsOclType(child); return; }
	if ( child.eClass().getName().equals("BagType") ) { setCrossRefsBagType(child); return; }
	if ( child.eClass().getName().equals("ActionBlock") ) { setCrossRefsActionBlock(child); return; }
	if ( child.eClass().getName().equals("DropPattern") ) { setCrossRefsDropPattern(child); return; }
	if ( child.eClass().getName().equals("VariableDeclaration") ) { setCrossRefsVariableDeclaration(child); return; }
	if ( child.eClass().getName().equals("OperatorCallExp") ) { setCrossRefsOperatorCallExp(child); return; }
	if ( child.eClass().getName().equals("BindingStat") ) { setCrossRefsBindingStat(child); return; }
	if ( child.eClass().getName().equals("IfStat") ) { setCrossRefsIfStat(child); return; }
	if ( child.eClass().getName().equals("OrderedSetExp") ) { setCrossRefsOrderedSetExp(child); return; }
	if ( child.eClass().getName().equals("OclFeatureDefinition") ) { setCrossRefsOclFeatureDefinition(child); return; }
	if ( child.eClass().getName().equals("SimpleOutPatternElement") ) { setCrossRefsSimpleOutPatternElement(child); return; }
	if ( child.eClass().getName().equals("OclModel") ) { setCrossRefsOclModel(child); return; }
	if ( child.eClass().getName().equals("Library") ) { setCrossRefsLibrary(child); return; }
	if ( child.eClass().getName().equals("EnumLiteralExp") ) { setCrossRefsEnumLiteralExp(child); return; }
	if ( child.eClass().getName().equals("BagExp") ) { setCrossRefsBagExp(child); return; }
	if ( child.eClass().getName().equals("Binding") ) { setCrossRefsBinding(child); return; }
	if ( child.eClass().getName().equals("OperationCallExp") ) { setCrossRefsOperationCallExp(child); return; }
	if ( child.eClass().getName().equals("Unit") ) { setCrossRefsUnit(child); return; }
	if ( child.eClass().getName().equals("Operation") ) { setCrossRefsOperation(child); return; }
	if ( child.eClass().getName().equals("CollectionType") ) { setCrossRefsCollectionType(child); return; }
	if ( child.eClass().getName().equals("OclAnyType") ) { setCrossRefsOclAnyType(child); return; }
	if ( child.eClass().getName().equals("StringType") ) { setCrossRefsStringType(child); return; }
	if ( child.eClass().getName().equals("MapType") ) { setCrossRefsMapType(child); return; }
	if ( child.eClass().getName().equals("BooleanType") ) { setCrossRefsBooleanType(child); return; }
	if ( child.eClass().getName().equals("NavigationOrAttributeCallExp") ) { setCrossRefsNavigationOrAttributeCallExp(child); return; }
	if ( child.eClass().getName().equals("SequenceType") ) { setCrossRefsSequenceType(child); return; }
	if ( child.eClass().getName().equals("Helper") ) { setCrossRefsHelper(child); return; }
	if ( child.eClass().getName().equals("StringExp") ) { setCrossRefsStringExp(child); return; }
	if ( child.eClass().getName().equals("SequenceExp") ) { setCrossRefsSequenceExp(child); return; }
	if ( child.eClass().getName().equals("ForStat") ) { setCrossRefsForStat(child); return; }
	if ( child.eClass().getName().equals("LibraryRef") ) { setCrossRefsLibraryRef(child); return; }
	if ( child.eClass().getName().equals("Iterator") ) { setCrossRefsIterator(child); return; }
	if ( child.eClass().getName().equals("IterateExp") ) { setCrossRefsIterateExp(child); return; }
	if ( child.eClass().getName().equals("IntegerExp") ) { setCrossRefsIntegerExp(child); return; }
	if ( child.eClass().getName().equals("SetType") ) { setCrossRefsSetType(child); return; }
	if ( child.eClass().getName().equals("LetExp") ) { setCrossRefsLetExp(child); return; }
	if ( child.eClass().getName().equals("Query") ) { setCrossRefsQuery(child); return; }
	if ( child.eClass().getName().equals("MapElement") ) { setCrossRefsMapElement(child); return; }
	if ( child.eClass().getName().equals("MatchedRule") ) { setCrossRefsMatchedRule(child); return; }
	if ( child.eClass().getName().equals("CalledRule") ) { setCrossRefsCalledRule(child); return; }
	if ( child.eClass().getName().equals("CollectionOperationCallExp") ) { setCrossRefsCollectionOperationCallExp(child); return; }
	if ( child.eClass().getName().equals("SetExp") ) { setCrossRefsSetExp(child); return; }
	if ( child.eClass().getName().equals("OrderedSetType") ) { setCrossRefsOrderedSetType(child); return; }
	if ( child.eClass().getName().equals("OutPattern") ) { setCrossRefsOutPattern(child); return; }
	if ( child.eClass().getName().equals("TupleExp") ) { setCrossRefsTupleExp(child); return; }
	if ( child.eClass().getName().equals("IfExp") ) { setCrossRefsIfExp(child); return; }
	if ( child.eClass().getName().equals("SimpleInPatternElement") ) { setCrossRefsSimpleInPatternElement(child); return; }
	if ( child.eClass().getName().equals("RealType") ) { setCrossRefsRealType(child); return; }
	if ( child.eClass().getName().equals("TupleType") ) { setCrossRefsTupleType(child); return; }
	if ( child.eClass().getName().equals("OclUndefinedExp") ) { setCrossRefsOclUndefinedExp(child); return; }
	if ( child.eClass().getName().equals("TuplePart") ) { setCrossRefsTuplePart(child); return; }
	if ( child.eClass().getName().equals("IteratorExp") ) { setCrossRefsIteratorExp(child); return; }
	if ( child.eClass().getName().equals("MapExp") ) { setCrossRefsMapExp(child); return; }
	if ( child.eClass().getName().equals("TupleTypeAttribute") ) { setCrossRefsTupleTypeAttribute(child); return; }
		throw new IllegalStateException("Cannot handle class: " + child.eClass().getName());
	}
}
