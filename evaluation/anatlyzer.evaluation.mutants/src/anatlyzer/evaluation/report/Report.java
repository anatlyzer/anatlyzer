package anatlyzer.evaluation.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Report {
	private Map<String,Result> report =  new HashMap<String,Result>();

	/** clear report **/
	public void clear () {
		report.clear();
	}
	
	/** analysis of a new transformation */
	public Result getResult (String transformation) {
		String transformation2 = getFileName(transformation);
		if (!report.containsKey(transformation2)) 
			report.put(transformation2, new Result());
		return report.get(transformation2);
	}

	/** analysis of a new transformation */
	public boolean addResult (String transformation) {
		this.getResult(transformation);
		return false;
	}

	/** the transformation did not execute correctly */
	public boolean setExecutionError (String transformation, String error, String witness) {
		Result r = this.getResult(transformation);
		r.setExecutionRaisesException();
		r.setExecutionError(error);
		r.setExecutionWitness(witness);
		return true;
	}
	
	/** the transformation produces a model not conforming to the output metamodel */
	public boolean setOutputError (String transformation, String error, String witness) {
		Result r = this.getResult(transformation);
		r.setExecutionYieldsIllTarget();
		r.setExecutionError("The transformation produces ill-typed target models.");
		r.setExecutionWitness(witness);
		return true;
	}
	
	/** the anatlyzer reported an error in the transformation */
	public boolean setAnatlyserError (String transformation, String error) {
		Result r = this.getResult(transformation);
		r.setAnatlyserNotifiesError();
		r.setAnatlyserError(error);
		return true;
	}
	
	/** the anatlyser finished with an exception */
	public boolean setAnatlyserException (String transformation, String error) {
		Result r = this.getResult(transformation);
		r.setAnatlyserDoesNotFinish();
		r.setAnatlyserError(error);
		return true;
	}	
	
	/** print report to console */
	public void print () {
		ReportConsole console = new ReportConsole();
		console.clear();
		long total          = 0;
		long falsePositives = 0;
		long falseNegatives = 0;
		long anatlyserExceptions = 0;
		
		// print header
		console.println("==============================================================");
		console.println("RESULTS");
		console.println("==============================================================");
		
		// print result of each transformation
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
	
	/** print report to file **/
	public void print (String folder) {
		if (folder==null || !new File(folder).exists()) return;
		
		PrintWriter xls = null;
		try { xls = new PrintWriter(folder + File.separator + "evaluation.xls", "UTF-8"); } 
	    catch (FileNotFoundException|UnsupportedEncodingException e) {}
		if (xls == null) return;

		Integer numrecords = 0;

		// printer header
		xls.println("MUTANT\t" +
			    "ANATLYSER\t" +
			    "EXECUTION\t" +
			    "RESULT\t" +
			    "AN. OUTPUT\t" +
			    "EX. OUTPUT\t" +
			    "AN. EXCEPTION");
		
		// print result of each transformation
		for (String transformation : new TreeSet<String>(report.keySet())) {
			numrecords++;
			Result r = report.get(transformation);
			xls.println(transformation + "\t" + 
		            convert(r.getAnatlyserNotifiesError()) + "\t" + 
				    convert(r.getExecutionRaisesException()||r.getExecutionYieldsIllTarget()) + "\t" +
		            "=SI(Y(B" + (numrecords+1) + "=\"error\";C" + (numrecords+1) + "=\"error\"); \"true positive\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"correct\";C" + (numrecords+1) + "=\"correct\"); \"true negative\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"error\";C" + (numrecords+1) + "=\"correct\"); \"false positive\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"correct\";C" + (numrecords+1) + "=\"error\"); \"false negative\"; \"unknown\"))))" + "\t" +
		            (r.getAnatlyserNotifiesError()? convert(r.getAnatlyserError()) : "") + "\t" +
		            (r.getExecutionError()  !=null? convert(r.getExecutionError()) : "") +
		            (r.getAnatlyserDoesNotFinish()? "\t ***WARNING*** anATLyser raised the exception " + convert(r.getAnatlyserError()) : ""));
		}
		
		// print summary
		xls.println();
		xls.println();
		xls.println("\tEvaluated transformations\t"+numrecords);
		xls.println("\tTrue positives\t=CONTAR.SI(D2:D"+(numrecords+1)+";\"true positive\")\t=CONCATENAR(REDONDEAR(C"+(numrecords+5)+"/C"+(numrecords+4)+"%;2);\"%\")");
		xls.println("\tTrue negatives\t=CONTAR.SI(D2:D"+(numrecords+1)+";\"true negative\")\t=CONCATENAR(REDONDEAR(C"+(numrecords+6)+"/C"+(numrecords+4)+"%;2);\"%\")");
		xls.println("\tFalse positives\t=CONTAR.SI(D2:D"+(numrecords+1)+";\"false positive\")\t=CONCATENAR(REDONDEAR(C"+(numrecords+7)+"/C"+(numrecords+4)+"%;2);\"%\")");
		xls.println("\tFalse negatives\t=CONTAR.SI(D2:D"+(numrecords+1)+";\"false negative\")\t=CONCATENAR(REDONDEAR(C"+(numrecords+8)+"/C"+(numrecords+4)+"%;2);\"%\")");
		xls.println("\tPrecision\t=REDONDEAR(C"+(numrecords+5)+"/(C"+(numrecords+5)+"+C"+(numrecords+7)+");2)");
		xls.println("\tRecall\t=REDONDEAR(C"+(numrecords+5)+"/(C"+(numrecords+5)+"+C"+(numrecords+8)+");2)");
		xls.println("\tAccuracy\t=REDONDEAR((C"+(numrecords+5)+"+C"+(numrecords+6)+")/(C"+(numrecords+5)+"+C"+(numrecords+6)+"+C"+(numrecords+7)+"+C"+(numrecords+8)+");2)");
		xls.println("\tF-score\t=REDONDEAR((2*C"+(numrecords+9)+"*C"+(numrecords+10)+")/(C"+(numrecords+9)+"+C"+(numrecords+10)+");2)");
		xls.println("\tAnatlyser exceptions\t=C"+(numrecords+4)+"-CONTAR.BLANCO(G2:G"+(numrecords+1)+")");
		
		xls.close();
		
		ReportConsole console = new ReportConsole();
		console.println("\n>> The results of the analysis were saved in file evaluation.xls");
		console.display();
	}
	
	public void printError (String error) {
		ReportConsole console = new ReportConsole();
		console.println(error);
		console.display();
	}
	
	private String convert (boolean value) { return value? "error" : "correct"; }
	private String convert (String  value) { String value2 = value.replaceAll("\\s", " "); return value2.length()<100? value2 : value2.substring(0, 100); }
	
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
		
		public boolean getAnatlyserNotifiesError()   { return anatlyserNotifiesError;   }
		public boolean getAnatlyserDoesNotFinish()   { return anatlyserDoesNotFinish;   }
		public boolean getExecutionRaisesException() { return executionRaisesException; }
		public boolean getExecutionYieldsIllTarget() { return executionYieldsIllTarget; }		
		public String  getAnatlyserError()           { return anatlyserError;           }
		public String  getExecutionError()           { return executionError;           }
		public String  getExecutionWitness()         { return executionWitness;         }
	}
}
