package anatlyzer.atl.analyser;

import java.util.HashSet;

import anatlyzer.atl.analyser.libtypes.IOclStandardLibrary;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.OclAnyInheritedNamespace;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;

public class AnalyserContext {
	private static ThreadLocal<TypingModel> typingModelTL = new ThreadLocal<TypingModel>();
	private static ThreadLocal<ErrorModel>  errorModelTL  = new ThreadLocal<ErrorModel>();
	private static ThreadLocal<EcoreTypeConverter> converterTL  = new ThreadLocal<EcoreTypeConverter>();
	private static ThreadLocal<GlobalNamespace> mmTL  = new ThreadLocal<GlobalNamespace>();
	private static ThreadLocal<IOclStandardLibrary> stdLibTL  = new ThreadLocal<IOclStandardLibrary>();
	private static ThreadLocal<OclAnyInheritedNamespace> oclAnyInheritedNamespace  = new ThreadLocal<OclAnyInheritedNamespace>();

	private static boolean	isVarDclInferencePreferred = true;
	private static boolean	isOclStrict = true;
	
	public static void setTypingModel(TypingModel value) {
		typingModelTL.set(value);
		converterTL.set(new EcoreTypeConverter(value));
		oclAnyInheritedNamespace.set(new OclAnyInheritedNamespace());
	}
	
	public static IOclStandardLibrary getStdLib() {
		return stdLibTL.get();
	}
	
	public static TypingModel getTypingModel() {
		return typingModelTL.get();
	}
	
	public static void setErrorModel(ErrorModel value) {
		errorModelTL.set(value);
	}


	public static void setGlobalNamespace(GlobalNamespace mm) {
		mmTL.set(mm);
	}
	
	public static void setStdLib(IOclStandardLibrary std) {
		stdLibTL.set(std);
	}
	
	public static GlobalNamespace getGlobalNamespace() {
		return mmTL.get();
	}
	
	public static ErrorModel getErrorModel() {
		return errorModelTL.get();
	}
	
	public static EcoreTypeConverter getConverter() {
		return converterTL.get();
	}

	public static OclAnyInheritedNamespace getOclAnyInheritedNamespace() {
		return oclAnyInheritedNamespace.get();
	}
	
	public static boolean isVarDclInferencePreferred() {
		return isVarDclInferencePreferred ;
	}

	public static boolean isOclStrict() {
		return isOclStrict ;
	}

	public static boolean debugMode() {
		return true;
	}

	
	public static HashSet<Class<?>> ignoreInAnalysisPass = new HashSet<>();
	static {
		ignoreInAnalysisPass.add(anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny.class);
	}
	public static boolean check(Class<?> class1) {
		return ! ignoreInAnalysisPass.contains(class1);
	}

	

}
