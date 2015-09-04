package anatlyzer.evaluation.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Report {
	private Map<String,Result> report =  new HashMap<String,Result>();
	
	/** analysis of a new transformation */
	private Result getResult (String transformation) {
		if (!report.containsKey(transformation)) 
			report.put(transformation, new Result());
		return report.get(transformation);
	}

	/** analysis of a new transformation */
	public boolean addResult (String transformation) {
		this.getResult(getFileName(transformation));
		return false;
	}

	/** the transformation did not execute correctly */
	public boolean setExecutionError (String transformation, String error, String witness) {
		Result r = this.getResult(getFileName(transformation));
		r.setExecutionRaisesException();
		r.setExecutionError(error);
		r.setExecutionWitness(witness);
		return true;
	}
	
	/** the transformation produces a model not conforming to the output metamodel */
	public boolean setOutputError (String transformation, String error, String witness) {
		Result r = this.getResult(getFileName(transformation));
		r.setExecutionYieldsIllTarget();
		r.setExecutionError("The transformation produces ill-typed target models.");
		r.setExecutionWitness(witness);
		return true;
	}
	
	/** the anatlyzer reported an error in the transformation */
	public boolean setAnatlyserError (String transformation, String error) {
		Result r = this.getResult(getFileName(transformation));
		r.setAnatlyserNotifiesError();
		r.setAnatlyserError(error);
		return true;
	}
	
	/** the anatlyser finished with an exception */
	public boolean setAnatlyserException (String transformation, String error) {
		Result r = this.getResult(getFileName(transformation));
		r.setAnatlyserDoesNotFinish();
		r.setAnatlyserError(error);
		return true;
	}	
	
	/** print report */
	public void print () {
		ReportConsole console = new ReportConsole();
		console.clear();
		long total          = 0;
		long falsePositives = 0;
		long falseNegatives = 0;
		long anatlyserExceptions = 0;
		
		console.println("==============================================================");
		console.println("RESULTS");
		console.println("==============================================================");
		
		// print result of each analysis
		for (String transformation : new TreeSet<String>(report.keySet())) {
			Result  r           = report.get(transformation);
			
			boolean discrepancy = (r.getExecutionRaisesException()||r.getExecutionYieldsIllTarget()) ^ r.getAnatlyserNotifiesError();			
			if (discrepancy) {
				if (r.getAnatlyserNotifiesError()) 
					 falsePositives++;
				else falseNegatives++;
				console.println( " ** discrepancy in " + transformation + " --" + 
								 " ANATLYZER: " + (r.getAnatlyserNotifiesError()? "error "+r.getAnatlyserError()+" --":"ok --") +
	                             " EXECUTION: " + ((r.getExecutionRaisesException()||r.getExecutionYieldsIllTarget())? ("error "+r.getExecutionError()+"; witness "+r.getExecutionWitness()):"ok") );
			}			
			else console.println( r.getAnatlyserNotifiesError()? 
						 		  " ok: " +  transformation + " is incorrect [ANATLYSER =>" + r.getAnatlyserError() + "; EXECUTION => " + r.getExecutionError() + "]" :
								  " ok: " +  transformation + " is correct" );	
			
			if (r.getAnatlyserDoesNotFinish()) {
				anatlyserExceptions++;
				console.println("    ---> WARNING: anATLyser raised the exception " + r.getAnatlyserError() );
			}
			
			total++;
		}
		
		// print summary
		console.println("==============================================================");
		console.println("SUMARY");
		console.println("==============================================================");
		console.println(" Evaluated transformations = " + total);
		if (total>0) {
			console.println(" Correctly anatlysed ..... = " + (total-falseNegatives-falsePositives) + " (" + (100*(total-falseNegatives-falsePositives)/total) + "%)");
			console.println(" False positives ......... = " + falsePositives + " (" + (100*falsePositives/total) + "%)");
			console.println(" False negatives ......... = " + falseNegatives + " (" + (100*falseNegatives/total) + "%)");
			if (anatlyserExceptions>0) console.println(" [anATLyser exceptions ... = " + anatlyserExceptions + "]");
		}
		console.display();
	}
	
	/** it returns the name of a file, given its full or relative path**/
	private String getFileName(String path) {
		String name = new File(path).getName();
		return name!=null? name : path;
	}
	
	/** result for a transformation */
	public class Result {
		private boolean anatlyserNotifiesError   = false;
		private boolean anatlyserDoesNotFinish   = false; // the anatlysis raises an exception
		// otherwise, it means the anatlysis reports no error
		
		private boolean executionRaisesException = false;
		private boolean executionYieldsIllTarget = false;
		// otherwise, it means the execution always produces correct target models
		
		private String executionError, anatlyserError;
		private String executionWitness;
		
		void setAnatlyserNotifiesError()       { anatlyserNotifiesError   = true; }
		void setAnatlyserDoesNotFinish()       { anatlyserDoesNotFinish   = true; }
		void setExecutionRaisesException()     { executionRaisesException = true; }
		void setExecutionYieldsIllTarget()     { executionYieldsIllTarget = true; }
		void setAnatlyserError(String error)   { anatlyserError   = error;        }
		void setExecutionError(String error)   { executionError   = error;        }
		void setExecutionWitness(String error) { executionWitness = error;        }
		
		boolean getAnatlyserNotifiesError()   { return anatlyserNotifiesError;   }
		boolean getAnatlyserDoesNotFinish()   { return anatlyserDoesNotFinish;   }
		boolean getExecutionRaisesException() { return executionRaisesException; }
		boolean getExecutionYieldsIllTarget() { return executionYieldsIllTarget; }		
		String  getAnatlyserError()           { return anatlyserError;           }
		String  getExecutionError()           { return executionError;           }
		String  getExecutionWitness()         { return executionWitness;         }
	}
}
