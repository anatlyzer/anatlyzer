package anatlyzer.atl.quickfixast;

import java.util.function.Function;

import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.LocatedElement;

public class QuickfixScope<T extends LocatedElement> {
	private T root;
	private T originalRoot;

	@SuppressWarnings("unchecked")
	public QuickfixScope(T root) {
		this.originalRoot = root;
		this.root         = (T) ATLCopier.copySingleElement(root);
	}

	public void replace(Function<T, T> replacer) {
		T r = replacer.apply(root);
		EcoreUtil.replace(root, r);
		// now r is the new root...
	}
	
	
}
