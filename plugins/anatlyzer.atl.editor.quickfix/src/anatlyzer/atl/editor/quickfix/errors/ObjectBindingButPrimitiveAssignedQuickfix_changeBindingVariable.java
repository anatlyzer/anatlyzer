package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.EcoreTypeConverter;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.NumericExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PrimitiveExp;
import anatlyzer.atlext.OCL.StringExp;

/**
 * This quickfix changes the binding variable by a compatible one with primitive type
 * @author Usuario
 *
 */
public class ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable extends AbstractAtlQuickfix {

	public ObjectBindingButPrimitiveAssigned getProblem() {
		try {
			return (ObjectBindingButPrimitiveAssigned) super.getProblem();
		} catch (CoreException ce) {
			
		}
		return null;
	}
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ObjectBindingButPrimitiveAssigned.class);
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
	}
	
	private String getClosestVariableName(EClass mc, String old, OclExpression type) {	// We consider primitive types only
		if (! (type instanceof PrimitiveExp)) return old;
		List<String> compat = mc.getEAllAttributes().stream().
								filter ( a -> this.isCompatibleWith(a.getEAttributeType(), (PrimitiveExp)type )).
								map ( a -> a.getName()).
								collect(Collectors.toList());
		if (compat.size() == 0) return old;								// We should check that some is available in isApplicable;
		StringDistance sd = new StringDistance(new Levenshtein());
		return sd.closest(old, compat);
	}
	
	private EAttribute getClosestVariable(EClass mc, String old, OclExpression type) {
		String var = this.getClosestVariableName(mc, old, type);
		List<EAttribute> atts = mc.getEAllAttributes().stream().filter( a -> a.getName().equals(var)).collect(Collectors.toList());
		return (atts.size() == 0) ? null : atts.get(0); 
	}

	private boolean isCompatibleWith(EDataType eAttributeType, PrimitiveExp type) {		
		switch ( EcoreTypeConverter.normalizeToBasic(eAttributeType) ) {
			case BOOLEAN: return (type instanceof BooleanExp);
			case INTEGER: 
			case DECIMAL: return (type instanceof NumericExp);
			case STRING: return (type instanceof StringExp);		
		}
		return false;
	}
	
	private String getClosestVariableName() {
		Binding ob = (Binding)this.getProblematicElement();
		return this.getClosestVariableName(this.getClassContainer(ob), ob.getPropertyName(), ob.getValue());
	}
	
	private EAttribute getClosestVariable() {
		Binding ob = (Binding)this.getProblematicElement();
		EAttribute ea = this.getClosestVariable(this.getClassContainer(ob), ob.getPropertyName(), ob.getValue());
		return ea;
	}

	@Override
	public String getDisplayString() {
		return "Change binding variable by : "+this.getClosestVariableName();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Binding b = (Binding)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication();
		
		qfa.replace(b, (expr, trace) -> {

			trace.preserve(expr.getOutPatternElement());
			
			EAttribute closest = this.getClosestVariable();
			expr.setWrittenFeature(closest);
			expr.setLeftType(Conversions.convert(closest.getEAttributeType()));
			expr.setPropertyName(closest.getName());
			//StringExp lit = (StringExp)le.getArguments().get(1);
			//lit.setStringSymbol(getClosest());
			return expr;
		});
		return qfa;
	}

}
