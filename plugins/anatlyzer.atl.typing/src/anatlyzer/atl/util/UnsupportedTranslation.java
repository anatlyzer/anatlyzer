package anatlyzer.atl.util;

import org.eclipse.emf.ecore.EObject;


public class UnsupportedTranslation extends RuntimeException {
	private static final long serialVersionUID = -2469725461655919931L;
	private EObject context;

	public UnsupportedTranslation(String msg) {
		super(msg);
	}
	
	public UnsupportedTranslation(String msg, EObject ctx) {
		super(msg);
		this.context = ctx;
	}
	
	public EObject getContext() {
		return context;
	}
}
