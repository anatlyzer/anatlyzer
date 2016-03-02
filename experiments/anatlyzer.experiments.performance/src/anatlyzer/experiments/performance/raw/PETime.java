package anatlyzer.experiments.performance.raw;

import java.util.Calendar;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="time")
public class PETime {
	@Attribute
	private long millis;
	
	public PETime() { }

	public PETime(long timeMillis) {
		this.millis = timeMillis;
	}

	public PETime(long totalTime, long nExecutions) {
		this.millis = totalTime / nExecutions;
	}
	
	public PETime(double pathTime) {
		this.millis = (long) pathTime; // truncating...
	}

	public long getTime() {
		return millis;
	}

	public double toSeconds() {
		return millis / 1000.0;
	}
}
