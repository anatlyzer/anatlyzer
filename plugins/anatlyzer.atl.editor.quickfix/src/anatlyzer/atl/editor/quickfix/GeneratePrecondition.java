package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.USESerializer.USEConstraint;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.OclExpression;

public class GeneratePrecondition extends AbstractAtlQuickfix {

	private Class<?> applicableClass;

	public GeneratePrecondition(Class<?> applicableClass) {
		this.applicableClass = applicableClass;
	}

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, applicableClass);
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		LocalProblem p = getProblem();		
		AnalysisResult result = getAnalysisResult();
		
		ErrorPathGenerator pathgen = new ErrorPathGenerator(result.getAnalyser());		
		ProblemPath path = pathgen.generatePath(p);
		OclExpression expr = path.getWeakestPrecondition();
		
		ErrorSlice slice = path.getErrorSlice(result.getAnalyser());
		String info = slice.toOneLineString();
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.addCommentBefore(getATLModel().getRoot(), () -> {
			USEConstraint useConstraint = USESerializer.retypeAndGenerate(expr, path);	
			String pre = useConstraint.asString();
			pre = pre.replace("\n", "\n-- ");			
			return "-- @pre " + pre + "\n" + "-- @footprint " + info;
		});
		
		
		return qfa;
	}
	
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public String getDisplayString() {
		return "Generate precondition";
	}


}
