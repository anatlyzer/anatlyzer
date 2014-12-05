package anatlyzer.atl.analyser;

public class AnalyserInternalError extends RuntimeException {

	private static final long serialVersionUID = -113718838144425301L;
	private int stage;

	public AnalyserInternalError(Throwable e, int stage) {
		super(e);
		this.stage = stage;
	}
	
	public int getStage() {
		return stage;
	}

}
