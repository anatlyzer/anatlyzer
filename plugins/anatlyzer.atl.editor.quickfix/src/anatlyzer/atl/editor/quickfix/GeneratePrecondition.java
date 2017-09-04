package anatlyzer.atl.editor.quickfix;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.Operation;

public abstract class GeneratePrecondition extends AbstractAtlQuickfix {

	private Class<?> applicableClass;
	private ProblemPath path;

	public GeneratePrecondition(Class<?> applicableClass) {
		this.applicableClass = applicableClass;
	}

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		boolean typeOk = checkProblemType(marker, applicableClass);
		if ( typeOk ) {
			setErrorMarker(marker);
			this.path = getProblemPath();
			return ! this.path.getExecutionNodes().isEmpty();
		}
		return false;
	}

	public ProblemPath getProblemPath() throws CoreException {
		if ( path != null )
			return path;
		
		LocalProblem p = (LocalProblem) getProblem();		
		AnalysisResult result = getAnalysisResult();
		
		ErrorPathGenerator pathgen = new ErrorPathGenerator(result.getAnalyser());		
		ProblemPath path = pathgen.generatePath(p);
		
		return path;
	}
	
	@Override public void resetCache() { 
		this.path = null;		
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		OclExpression expr = getProblemPath().getWeakestPrecondition();
		
		if ( expr == null ) {
			MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
			new QuickfixApplication(this); // does nothing
		}
		
		boolean insertAsComment = false;
		
		if ( insertAsComment ) {
			QuickfixApplication qfa = new QuickfixApplication(this);
			qfa.addCommentBefore(getATLModel().getRoot(), () -> {
				String pre = ATLSerializer.serialize(expr);
				pre = pre.replace("\n", "\n-- ");			
				return "-- @pre " + pre + "\n";
			});
			
			return qfa;
		} else {
			ModuleElement anchor = null;
			List<Helper> helpers = ATLUtils.getAllHelpers(getATLModel());
			for (Helper helper : helpers) {
				if ( AnalyserUtils.isPrecondition(helper) ) {
					anchor = helper;
				}
			}
			
			if ( anchor == null && getATLModel().getRoot() instanceof Module ) {
				anchor = getATLModel().getModule().getElements().stream().filter(e -> e instanceof Rule).findFirst().orElse(null);
			}
			
			
			String preName = "genPrecondition";
			StaticHelper helper = ASTUtils.buildNewThisModuleOperation(preName, anatlyzer.atl.types.TypesFactory.eINSTANCE.createBooleanType(), java.util.Collections.emptyList());
			Operation op = (Operation) helper.getDefinition().getFeature();
			helper.getCommentsBefore().add("@precondition");
			
			op.setBody(expr);
			
			// ModuleElement anchor = ATLUtils.getContainer(res, ModuleElement.class);
		
			QuickfixApplication qfa = new QuickfixApplication(this);
			// qfa.putIn(getATLModel().getRoot(), ATLPackage.Literals.MODULE__ELEMENTS, () -> helper);			
			qfa.insertAfter(anchor, () -> helper);
			return qfa;
		}
		
		// ASTUtils.buildNewThisModuleOperation(name, returnType, arguments)
		
		// This code was used to generate the pre-condition in USE format
		/*
		ErrorSlice slice = path.getErrorSlice(result.getAnalyser());
		String info = slice.toOneLineString();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.addCommentBefore(getATLModel().getRoot(), () -> {
			USEConstraint useConstraint = USESerializer.retypeAndGenerate(expr, path);	
			String pre = useConstraint.asString();
			pre = pre.replace("\n", "\n-- ");			
			return "-- @pre " + pre + "\n" + "-- @footprint " + info;
		});
		*/
		
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


	@Override public Image getImage() {
		return QuickfixImages.precondition.createImage();
	}
}
