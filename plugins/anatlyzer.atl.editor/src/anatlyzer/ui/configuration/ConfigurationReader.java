package anatlyzer.ui.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;

public class ConfigurationReader {

	private TransformationConfiguration configuration;
	
	private ConfigurationReader(InputStream stream, TransformationConfiguration configuration) throws IOException {
		this.configuration = configuration;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		while( (line = reader.readLine()) != null ) {
			line = line.trim();
			
			if ( line.startsWith("#"))
				continue;
			
			if ( line.startsWith("debug-witness-finder") ) {
				checkDebug(line);				
			} else if ( line.startsWith("enforce-containment-semantics") ) {
				checkEnforceContainmentSemantics(line);				
			} else if ( line.startsWith("witness-finder") ) {
				checkContinuous(line);
			} else if ( line.startsWith("show-marker") ) {
				checkShowMarker(line);
			} else if ( line.startsWith("check-discard-cause") ) {
				checkDiscardCause(line);
			} else if ( line.startsWith("witness-generation-mode") ) {
				checkWitnessGenerationMode(line);
			} else if ( line.startsWith("witness-recursion-unfolding") ) {
				checkRecursionUnfolding(line);
			} else if ( line.startsWith("witness-generation-graphics") ) {
				checkWitnessGenerationGraphics(line);				
			} else if ( line.startsWith("witness-timeout") ) {
				checkWitnessTimeOut(line);				
			} else if ( line.startsWith("continuous-problems") ) {
				checkProblemsSet(line.substring("continuous-problems".length()), configuration.getAvailableProblems()::moveToContinous);
			}  else if ( line.startsWith("batch-problems") ) {
				checkProblemsSet(line.substring("batch-problems".length()), configuration.getAvailableProblems()::moveToBatch);
			}  else if ( line.startsWith("ignored-problems") ) {
				checkProblemsSet(line.substring("ignored-problems".length()), configuration.getAvailableProblems()::moveToIgnored);
			}
		}
	}

	
	private void checkProblemsSet(String line, Consumer<EClass> consumer) throws IOException {
		String[] classNames = line.split(",");
		for (String className : classNames) {
			EClass c = (EClass) AtlErrorPackage.eINSTANCE.getEClassifier(className.trim());
			if ( c == null )
				throw new IOException("Configuration error. Class " + className + " not found");
			consumer.accept(c);
		}
	}


	/**
	 * <pre>
	 *   show-marker discarded
	 *   show-marker unknown
	 *   show-marker witness-required
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkShowMarker(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("discarded") ) configuration.addMarkerFor(ProblemStatus.ERROR_DISCARDED, ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL);
			if ( parts[1].equals("unknown") ) configuration.addMarkerFor(ProblemStatus.USE_INTERNAL_ERROR, ProblemStatus.NOT_SUPPORTED_BY_USE, ProblemStatus.IMPL_INTERNAL_ERROR);
			if ( parts[1].equals("witness-required") ) configuration.addMarkerFor(ProblemStatus.WITNESS_REQUIRED);
		}		
	}

	/**
	 * <pre>
	 *   witness-finder continuous
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkContinuous(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("continuous") ) configuration.setContinousWitnessFinder(true);
			if ( parts[1].equals("batch") )      configuration.setContinousWitnessFinder(false);
		}
	}

	/**
	 * <pre>
	 *   debug-witness-finder on
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkDebug(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("on") ) configuration.setWitnessFinderDebugMode(true);
			else if ( parts[1].equals("off") ) configuration.setWitnessFinderDebugMode(false);
		}
	}

	/**
	 * <pre>
	 *   enforce-containment-semantics on
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkEnforceContainmentSemantics(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("on") ) configuration.setEnforceContainmentSemantics(true);
			else if ( parts[1].equals("off") ) configuration.setEnforceContainmentSemantics(false);
		}
	}

	
	
	/**
	 * <pre>
	 *   witness-recursion-unfolding on
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkRecursionUnfolding(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("on") ) configuration.setDoRecursionUnfolding(true);
			else if ( parts[1].equals("off") ) configuration.setDoRecursionUnfolding(false);
		}
	}

	/**
	 * <pre>
	 *   witness-generation-mode error-path | mandatory-effective | mandatory-full | full
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkWitnessGenerationMode(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if      ( parts[1].equals("error-path") )			configuration.setWitnessMode(WitnessGenerationMode.ERROR_PATH);
			else if ( parts[1].equals("mandatory-effective") ) 	configuration.setWitnessMode(WitnessGenerationMode.MANDATORY_EFFECTIVE_METAMODEL);
			else if ( parts[1].equals("mandatory-full") ) 		configuration.setWitnessMode(WitnessGenerationMode.MANDATORY_FULL_METAMODEL);
			else if ( parts[1].equals("full") ) 				configuration.setWitnessMode(WitnessGenerationMode.FULL_METAMODEL);			
		}
	}

	/**
	 * <pre>
	 *   check-discard-cause on
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkDiscardCause(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("on") ) configuration.setCheckDiscardCause(true);
			else if ( parts[1].equals("off") ) configuration.setCheckDiscardCause(false);
		}
	}
	
	/**
	 * <pre>
	 *   witness-generation-graphics plantuml
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkWitnessGenerationGraphics(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("plantuml") ) configuration.setWitnessGenerationGraphics("plantuml");
			else if ( parts[1].equals("off") ) configuration.setWitnessGenerationGraphics(null);
		}
	}

	/**
	 * <pre>
	 *   witness-timeout off|1000 millis
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkWitnessTimeOut(String line) {
		String[] parts = line.split("\\s+");
		long timeOut = -1;
		if ( parts.length >= 2 ) {			
			if ( parts[1].equals("off") ) 
				timeOut = -1;
			else {
				timeOut = Long.parseLong(parts[1]);
				if ( parts.length >= 3 ) {
					String unit = parts[2];
					if ( "millis".equals(unit) ) {
						// as is
					} else if ( "seconds".equals(unit) ) {
						timeOut = timeOut * 1000;
					} else if ( "minutes".equals(unit) ) {
						timeOut = timeOut * (1000 * 60);
					}
				}
			}			
			configuration.setTimeOut(timeOut);
		}
	}

	public static TransformationConfiguration read(InputStream stream) throws IOException {
		TransformationConfiguration conf = new TransformationConfiguration();
		new ConfigurationReader(stream, conf);
		return conf;
	}
	
}
