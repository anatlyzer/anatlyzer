package anatlyzer.evaluation.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {
	
	private Map<String,SortedSet<Record>> report =  new HashMap<String,SortedSet<Record>>();

	/** clear report **/
	public void clear () {
		report.clear();
	}
	
	/** analysis of a new transformation */
	public SortedSet<Record> getResult (String transformation) {
		String transformation2 = getFileName(transformation);
		if (!report.containsKey(transformation2)) 
			report.put(transformation2, new TreeSet<Record>());
		return report.get(transformation2);
	}

	/** analysis of a new transformation */
	public boolean addResult (String transformation) {
		this.getResult(transformation);
		return false;
	}

	/** when executed with the given input model, the transformation crashes*/
	public boolean setExecutionError (String transformation, String error, String witness) {
		Record record = new Record(witness, error, ERROR_KIND.EXECUTION_RAISES_EXCEPTION); 
		this.getResult(transformation).add(record);
		return true;
	}
	
	/** when executed with the given input model, the transformation produces a malformed target model*/
	public boolean setOutputError (String transformation, String error, String witness) {
		Record record = new Record(witness, "The transformation produces ill-typed target models.", ERROR_KIND.EXECUTION_YIELDS_ILL_TARGET); 
		this.getResult(transformation).add(record);
		return true;
	}
	
	/** anatlyser reported an error in the transformation */
	public boolean setAnatlyserError (String transformation, String error) {
		Record record = new Record(null/*for any model*/, error, ERROR_KIND.ANATLYZER_DETECTED_ERROR); 
		this.getResult(transformation).add(record);
		return true;
	}
	
	/** anatlyser finished with an exception */
	public boolean setAnatlyserException (String transformation, String error) {
		Record record = new Record(null/*for any model*/, error, ERROR_KIND.ANATLYZER_DOES_NOT_FINISH); 
		this.getResult(transformation).add(record);
		return true;
	}	
	
	/** results of evaluation */
	public boolean getExecutionRaisesException (String transformation) { return getResult(transformation).stream().anyMatch( record -> record.executionRaisesException() ); }
	public boolean getExecutionYieldsIllTarget (String transformation) { return getResult(transformation).stream().anyMatch( record -> record.executionYieldsIllTarget() ); }
	public boolean getAnatlyserNotifiesError   (String transformation) { return getResult(transformation).stream().anyMatch( record -> record.anatlyserNotifiesError() ); }
	public boolean getAnatlyserDoesNotFinish   (String transformation) { return getResult(transformation).stream().anyMatch( record -> record.anatlyserDoesNotFinish() ); }
	public String  getExecutionError (String transformation) {
		if (getExecutionRaisesException(transformation) || getExecutionYieldsIllTarget(transformation)) {
			SortedSet<Record> records = getResult(transformation);
			Stream<Record>   errors   = records.stream().filter( record -> record.executionRaisesException() || record.executionYieldsIllTarget() );
			return errors.map(Record::getError).collect(Collectors.joining(" -- "));
		}
		return "";
	}
	public String  getAnatlyserError (String transformation) {
		if (getAnatlyserDoesNotFinish(transformation) || getAnatlyserNotifiesError(transformation)) {
			SortedSet<Record> records = getResult(transformation);
			Stream<Record>   errors   = records.stream().filter( record -> record.anatlyserDoesNotFinish() || record.anatlyserNotifiesError() );
			return errors.map(Record::getError).collect(Collectors.joining(" -- "));
		}
		return "";
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
			boolean executionRaisesException = getExecutionRaisesException(transformation);
			boolean executionYieldsIllTarget = getExecutionYieldsIllTarget(transformation);
			boolean anatlyserNotifiesError   = getAnatlyserNotifiesError  (transformation);
			boolean anatlyserDoesNotFinish   = getAnatlyserDoesNotFinish  (transformation);
			String  anatlyserError           = getAnatlyserError(transformation);
			String  executionError           = getExecutionError(transformation);
			
			boolean discrepancy = (executionRaisesException||executionYieldsIllTarget) ^ anatlyserNotifiesError;			
			if (discrepancy) {
				if (anatlyserNotifiesError) 
					 falsePositives++;
				else falseNegatives++;
				console.println( " ** discrepancy in " + transformation + " --" + 
								 " Anatlyser: " + (anatlyserNotifiesError? "error "+anatlyserError+" --":"ok --") +
	                             " EXECUTION: " + ((executionRaisesException||executionYieldsIllTarget)? "error "+executionError:"ok") );
			}			
			else console.println( anatlyserNotifiesError? 
						 		  " ok: " +  transformation + " is incorrect [ANATLYSER =>" + anatlyserError + "; EXECUTION => " + executionError + "]" :
								  " ok: " +  transformation + " is correct" );	
			
			if (anatlyserDoesNotFinish) {
				anatlyserExceptions++;
				console.println("    ---> WARNING: anATLyser raised the exception " + anatlyserError );
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
			xls.println(transformation + "\t" + 
		            convert(getAnatlyserNotifiesError(transformation)) + "\t" + 
				    convert(getExecutionRaisesException(transformation)||getExecutionYieldsIllTarget(transformation)) + "\t" +
		            "=SI(Y(B" + (numrecords+1) + "=\"error\";C" + (numrecords+1) + "=\"error\"); \"true positive\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"correct\";C" + (numrecords+1) + "=\"correct\"); \"true negative\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"error\";C" + (numrecords+1) + "=\"correct\"); \"false positive\";" +
		            " SI(Y(B" + (numrecords+1) + "=\"correct\";C" + (numrecords+1) + "=\"error\"); \"false negative\"; \"unknown\"))))" + "\t" +
		            (getAnatlyserNotifiesError(transformation)? convert(getAnatlyserError(transformation)) : "") + "\t" +
		            (getExecutionError(transformation)  !=null? convert(getExecutionError(transformation)) : "") +
		            (getAnatlyserDoesNotFinish(transformation)? "\t ***WARNING*** anATLyser raised the exception " + convert(getAnatlyserError(transformation)) : ""));
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
	
	// --------------------------------------------------------------------------------------------------
	// INNER CLASSES AND ENUMERATES
	// --------------------------------------------------------------------------------------------------
	
	enum ERROR_KIND { 
		// execution errors
		EXECUTION_RAISES_EXCEPTION, EXECUTION_YIELDS_ILL_TARGET,
		// anatlyser errors
		ANATLYZER_DETECTED_ERROR, ANATLYZER_DOES_NOT_FINISH
	};
	
	public class Record implements Comparable<Record> {
		
		/** attributes */
		private String     model        = null;
		private String     errorMessage = "";
		private ERROR_KIND errorKind    = null;
		
		public Record (String model) { 
			this.model = model; 
		}
		
		public Record (String model, String error, ERROR_KIND kind) { 
			this(model);
			this.errorMessage = error;
			this.errorKind    = kind;
		}
		
		public String  getModel()                  { return model; }
		public String  getError()                  { return (model!=null? "[witness model "+model+"]: " : "") + errorMessage; }
		public boolean executionRaisesException () { return errorKind==ERROR_KIND.EXECUTION_RAISES_EXCEPTION;  }
		public boolean executionYieldsIllTarget () { return errorKind==ERROR_KIND.EXECUTION_YIELDS_ILL_TARGET; }
		public boolean anatlyserNotifiesError ()   { return errorKind==ERROR_KIND.ANATLYZER_DETECTED_ERROR;    }
		public boolean anatlyserDoesNotFinish ()   { return errorKind==ERROR_KIND.ANATLYZER_DOES_NOT_FINISH;   }
		
		@Override
		public int compareTo(Record other) {
			if (model==null && other.model==null) return 0;
			if (model==null)       return 1;
			if (other.model==null) return -1;
			return model.compareTo(other.model);
		}
	}	
}
