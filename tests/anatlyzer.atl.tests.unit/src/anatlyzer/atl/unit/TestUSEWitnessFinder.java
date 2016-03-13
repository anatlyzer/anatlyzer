package anatlyzer.atl.unit;

import java.io.File;
import java.io.IOException;

import anatlyzer.atl.witness.UseWitnessFinder;

public class TestUSEWitnessFinder extends UseWitnessFinder {

	@Override
	protected void onUSEInternalError(Exception e) {
		e.printStackTrace();
	}

	@Override
	protected String getTempDirectory() {
		return new File(".").getAbsolutePath().replace("/.", "");
	}

}
