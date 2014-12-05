package anatlyzer.atl.analyser;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.processing.AbstractVisitor;

public abstract class AbstractAnalyserVisitor extends AbstractVisitor  {
	protected ComputedAttributes attr;
	
	protected Unit	root;
	protected GlobalNamespace	mm;
	protected ATLModel	model;
	
	public AbstractAnalyserVisitor(ATLModel model, GlobalNamespace mm, Unit root) {
		this.model = model;
		this.root = root;
		this.mm   = mm;
		// _debug = true;
	}
	
	
	protected void notImplementedYet() {
		throw new UnsupportedOperationException();
	}

	protected final ErrorModel errors() {
		return AnalyserContext.getErrorModel();
	}
	
	protected final TypingModel typ() {
		return AnalyserContext.getTypingModel();
	}

}
