package anatlyzer.atl.explanations.errors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import witness.visualizer.EMFModelPlantUMLSerializer;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.explanations.AbstractAtlExplanation;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessModel;

public class BindingPossiblyUnresolvedExplanation extends AbstractAtlExplanation {

	@Override
	public boolean isApplicable() {
		return checkProblemType(BindingPossiblyUnresolved.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		text.setText("There are some configurations of objects (in the right-hand side of the binding) which are not handled by any of the resolving rules");
		// Explain that you should document this or ignore...
	}

	@Override
	public void setAdditionalInfo(Composite composite) {
		Label l = new Label(composite, SWT.NONE);
		
		IWitnessModel witness = AnalyserUtils.getProblemWitnessModel(getProblem());

		// TODO: This is not very clean since here we depend directly from this plugin. There should be extension points for this kind of visualizations.
		try {
			// Do this in the proper place...
			ArrayList<EObject> toRemove = new ArrayList<EObject>();
			TreeIterator<EObject> it = witness.getModel().getAllContents();
			while ( it.hasNext() ) {
				EObject obj = it.next();
				if ( obj.eClass().getName().startsWith("AuxiliaryClass4USE") || obj.eClass().getName().equals("ThisModule") ) {
					toRemove.add(obj);					
				}
			}
			
			for (EObject eObject : toRemove) {
				EcoreUtil.delete(eObject);
			}
			
			EMFModelPlantUMLSerializer serializer = new EMFModelPlantUMLSerializer((EPackage) witness.getMetamodel().getContents().get(0), witness.getModel());
			File f = File.createTempFile("witness_model", "png");
			f.deleteOnExit();
			serializer.generatePNG(f.getAbsolutePath());

			Image img = new Image(null, f.getAbsolutePath());
			l.setImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
