package anatlyzer.atl.editor.quickassist.kinds;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.editor.quickassist.AbstractQuickAssistSet;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;

public class OutputPatternSet extends AbstractQuickAssistSet  {
		
	@Override
	public boolean isApplicable() {
		element = getProperOutPatternElement(element);
		if ( element == null ) {
			return false;
		}
		
		possibleAssists = computeQuickAssists((OutPatternElement) element);
		return possibleAssists.size() > 0;
	}

	@Override
	public AtlQuickAssist[] getPossibleAssists() {
		throw new UnsupportedOperationException();
	}

	
	private LocatedElement getProperOutPatternElement(LocatedElement element) {
		if ( element instanceof OutPatternElement ) return element;
		
		return ATLUtils.getContainer(element, OutPatternElement.class);
	}

	private List<AtlQuickAssist> computeQuickAssists(OutPatternElement ope) {
		ATLModel model = result.getATLModel();
		Metaclass m = (Metaclass) ope.getInferredType();
		return m.getKlass().getEAllStructuralFeatures().stream().
			filter(f -> ! f.isDerived() ).
			filter(f -> ope.getBindings().stream().noneMatch(b -> f == b.getWrittenFeature())).
			map(f -> createBinding(ope, f, model)).filter(f -> f != null).collect(Collectors.toList());
	}

	public CreateBinding createBinding(OutPatternElement ope, EStructuralFeature f, ATLModel model) {
		EStructuralFeature source = NoBindingForCompulsoryFeature_FindSimilarFeature.findSimilarAssignment(ope, f, model);
		return source == null ? null : new CreateBinding(ope, source, f);
	}
	
	public static class CreateBinding extends anatlyzer.atl.editor.quickassist.AbstractAtlQuickAssist {

		private OutPatternElement out;
		private EStructuralFeature source;
		private EStructuralFeature target;
		private RuleWithPattern rule;

		public CreateBinding(OutPatternElement out, EStructuralFeature source, EStructuralFeature target) {
			this.out = out;
			this.source = source;
			this.target = target;
			this.rule = ATLUtils.getContainer(out, RuleWithPattern.class);
		}
		
		@Override
		public boolean isApplicable() {
			return true;
		}

		@Override
		public String getDisplayString() {
			return "Create binding " + target.getName() + " <- " + source.getName();
		}

		@Override
		public void resetCache() { }

		@Override
		public QuickfixApplication getQuickfixApplication() throws CoreException {
			return NoBindingForCompulsoryFeature_FindSimilarFeature.createNewBinding(this, out, rule, source, target);
		}
		
	}

}
