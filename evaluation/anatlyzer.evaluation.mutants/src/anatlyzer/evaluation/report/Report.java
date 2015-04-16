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
		r.setExecutionError();
		r.setError(error);
		r.setWitness(witness);
		return true;
	}
	
	/** the transformation produces a model not conforming to the output metamodel */
	public boolean setOutputError (String transformation, String error, String witness) {
		Result r = this.getResult(getFileName(transformation));
		r.setOutputError();
		r.setError(error);
		r.setWitness(witness);
		return true;
	}
	
	/** the anatlyzer reported an error in the transformation */
	public boolean setAnatlyserError (String transformation, String error) {
		Result r = this.getResult(getFileName(transformation));
		r.setAnatlyzerError();
		r.setError(error);
		return true;
	}
	
	/** print report */
	public void print () {
		ReportConsole console = new ReportConsole();
		console.clear();
		long total          = 0;
		long falsePositives = 0;
		long falseNegatives = 0;
		
		console.println("==============================================================");
		console.println("RESULTS");
		console.println("==============================================================");
		
		// print result of each analysis
		for (String transformation : new TreeSet<String>(report.keySet())) {
			Result  r = report.get(transformation);
			boolean discrepancy = (r.isExecutionError()||r.isOutputError()) ^ r.isAnatlyzerError();
			if (discrepancy) {
				if (r.isAnatlyzerError()) 
					 falsePositives++;
				else falseNegatives++;
				console.println( " ** discrepancy in " + transformation + " --" + 
								 " ANATLYZER: " + (r.isAnatlyzerError()?                       "error "+r.getError()+" --":"ok --") +
	                             " EXECUTION: " + ((r.isExecutionError()||r.isOutputError())? ("error "+r.getError()+"; witness "+r.getWitness()):"ok") );
			}
			else console.println( r.isAnatlyzerError()? 
						 		  " ok: " +  transformation + " is incorrect (" + r.getError() + ")" :
								  " ok: " +  transformation + " is correct" );
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
		}
		console.display();
	}
	
	/** it returns the name of a file, given its full or relative path**/
	private String getFileName(String path) {
		String name = new File(path).getName();
		return name!=null? name : path;
	}
	
	/** result for a transformation */
	class Result {
		private boolean executionError = false;
		private boolean outputError    = false;
		private boolean anatlyzerError = false;
		private String  error;
		private String  witness;
		
		void setExecutionError()        { executionError = true;  }
		void setOutputError()           { outputError    = true;  }
		void setAnatlyzerError()        { anatlyzerError = true;  }
		void setError  (String error)   { this.error   = error;   }
		void setWitness(String witness) { this.witness = witness; }
		
		boolean isExecutionError() { return executionError; }
		boolean isOutputError()    { return outputError;    }
		boolean isAnatlyzerError() { return anatlyzerError; }
		String  getError()         { return error;          }
		String  getWitness()       { return witness;        }
	}
}
