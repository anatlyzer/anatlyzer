package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class IntegerTypeDef extends AtlTypeDef {
	
	@Override
	protected Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newIntegerType();
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
