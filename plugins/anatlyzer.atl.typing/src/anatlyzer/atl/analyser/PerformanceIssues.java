package anatlyzer.atl.analyser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.TransformationNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * This traversal checks for performance issues.
 * 
 * 
 * 
 * @author jesus
 *
 */
public class PerformanceIssues extends AbstractAnalyserVisitor {

	public PerformanceIssues(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}
	
	public void perform(ComputedAttributes attr) {
//		this.attr = attr.pushVisitor(this);
//		startVisiting(root);
//		attr.popVisitor(this);
	}

	// Unneeded asSequence for binding
	// chapters <- e.children->select(c | c.oclIsKindOf(XML!Element))->asSequence()
	
}
