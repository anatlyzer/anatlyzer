package witness;

public class WitnessException extends Exception {

	public WitnessException(String msg) {
		super(msg);
	}

	public WitnessException(Exception e) {
		super(e);
	}

	public WitnessException(String msg, Exception e) {
		super(msg, e);
	}
}
