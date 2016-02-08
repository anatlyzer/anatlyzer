package anatlyzer.experiments.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRecorder {
	private long current = -1;
	private List<SingleExecution> executions = new ArrayList<SingleExecution>();
	private boolean enabled = false;
	
	public void reset() {
		current      =  -1;
		executions   = new ArrayList<SingleExecution>();
		enabled      = false;
	}
	
	public void enable() {
		this.enabled = true;
	}
	
	public void start(String id) {
		if ( ! enabled ) {
			return;
		}
	
		System.out.println("====> Started " + id);
		
		if ( this.current != -1 ) {
			throw new IllegalStateException();
		}
		
		this.current = System.currentTimeMillis();
	}
	

	public void discard() {
		if ( ! enabled ) {
			return;
		}
		this.current = -1;
	}
	
	public void stop() {
		if ( ! enabled ) {
			return;
		}

		if ( this.current == -1 ) {
			throw new IllegalStateException();
		}

		long elapsed = System.currentTimeMillis() - this.current;
		SingleExecution exec = new SingleExecution(elapsed);
		executions.add(exec);
		System.out.println("Time: " + exec.millis);
		
		this.current = -1;
	}
	
	public double avgTime() {
		return executions.stream().collect(Collectors.averagingLong(SingleExecution::getMillis));
	}
	
	public long totalTime() {
		return executions.stream().collect(Collectors.summingLong(SingleExecution::getMillis));
	}

	public int numRecorded() {
		return executions.size();
	}
	
	public static class SingleExecution {
		private long millis;
		
		public SingleExecution(long millis) {
			this.millis = millis;
		}
		
		public long getMillis() {
			return millis;
		}
	}
	
}
