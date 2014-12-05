package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class BooleanTypeDef extends AtlTypeDef {
	
	@Override
	protected Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newBooleanType();
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
