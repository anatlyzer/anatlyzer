package anatlyzer.atl.editor.witness;

import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinderFactory;
import anatlyzer.atl.witness.WitnessUtil;

public class EclipseWitnessFactory implements IWitnessFinderFactory {

	public static final EclipseWitnessFactory INSTANCE = new EclipseWitnessFactory();
	
	@Override
	public IWitnessFinder create() {
		return WitnessUtil.getFirstWitnessFinder();
	}

}
