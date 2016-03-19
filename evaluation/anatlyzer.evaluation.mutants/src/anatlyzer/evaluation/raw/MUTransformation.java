package anatlyzer.evaluation.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Represents a transformation analysed by the Mutant experiment.
 * 
 * @author jesus
 */
public class MUTransformation {

	@Attribute
	protected String name;

	@Attribute
	protected String path;

	@ElementList(name = "problems")
	protected List<MUProblem> problems;

	@Element(required = false)
	protected MUException exception;

	@Attribute(name = "isLibrary", required = false)
	private boolean isLibrary = false;

	public MUTransformation() {
		this.problems = new ArrayList<MUProblem>();
	}

	public MUTransformation(String name, String path) {
		this();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setIsLibrary(boolean lib) {
		this.isLibrary = lib;
	}

	public boolean isLibrary() {
		return isLibrary;
	}

	public void addProblem(MUProblem p) {
		this.problems.add(p);
	}

	public List<MUProblem> getProblems() {
		return problems;
	}

	public void analysisError(Exception e) {
		this.exception = new MUException(e, false);
	}

	
	//
	// Fields & methods specific to mutants (i.e., not copied from TEProblem)
	//
	
	@Element(name = "mutantError", required = false)
	private IMutantError mutantError;
	
	public void setMutantError(Diagnostic diagnostic, String modelName) {
		mutantError = new MutantInvalidValidationError(diagnostic, modelName);
	}

	public void setMutantError(String msg, String modelName) {
		mutantError = new MutantExecutionError(msg, modelName);
	}
	
	public static interface IMutantError {
		public String getMessage();
		public String getModelName();
	}
	
	public static class MutantInvalidValidationError implements IMutantError {

		@Element(name = "mainMessage")
		private String msg;
		@ElementList(name = "childrenMessages")
		private List<String> childrenMsg;
		@Attribute		
		private String modelName;

		public MutantInvalidValidationError(Diagnostic diagnostic, String modelName) {
			this.msg = diagnostic.getMessage();
			this.childrenMsg = diagnostic.getChildren().stream().map(d -> d.getMessage()).collect(Collectors.toList());
			this.modelName = modelName;
		}

		@Override
		public String getModelName() {
			return modelName;
		}
		
		@Override
		public String getMessage() {
			return msg;
		}
		
	}

	public static class MutantExecutionError implements IMutantError {
		@Element(name = "mainMessage")
		private String message;
		@Attribute		
		private String modelName;
		
		public MutantExecutionError(String msg, String modelName) {
			this.message = msg;
			this.modelName = modelName;
		}
		
		@Override
		public String getModelName() {
			return modelName;
		}
		
		@Override
		public String getMessage() {
			return message;
		}
		
	}

}
