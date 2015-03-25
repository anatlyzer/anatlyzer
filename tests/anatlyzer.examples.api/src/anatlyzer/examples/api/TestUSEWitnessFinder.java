package anatlyzer.examples.api;

import anatlyzer.atl.witness.UseWitnessFinder;

public class TestUSEWitnessFinder extends UseWitnessFinder {

	@Override
	protected void onUSEInternalError(Exception e) {
		e.printStackTrace();
	}

	@Override
	protected String getTempDirectory() {
		return ".";
	}

}
