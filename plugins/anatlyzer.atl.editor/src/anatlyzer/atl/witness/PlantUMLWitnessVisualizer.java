package anatlyzer.atl.witness;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import witness.visualizer.EMFModelPlantUMLSerializer;

public class PlantUMLWitnessVisualizer implements IWitnessVisualizer {

	private IWitnessModel witness;

	@Override
	public void setModel(IWitnessModel model) {
		this.witness = model;
	}
	
	@Override
	public void render(Composite composite) {
		Label l = new Label(composite, SWT.NONE);
		
		EMFModelPlantUMLSerializer serializer = null;
		// Try one approach, if not, the other...
		try {
			Resource r = witness.getModelAsOriginal();
			serializer = new EMFModelPlantUMLSerializer(new ArrayList<>(witness.getOriginalMetamodel()), r);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		
		// TODO: This is not very clean since here we depend directly from this plugin. There should be extension points for this kind of visualizations.
		try {
			if ( serializer == null ) {
				serializer = adaptRewrittenModel();			
			}
			File f = File.createTempFile("witness_model", ".png");
			f.deleteOnExit();
			serializer.generatePNG(f.getAbsolutePath());

			l.setAlignment(SWT.CENTER);
			Image img = new Image(null, f.getAbsolutePath());
			l.setImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}

	private EMFModelPlantUMLSerializer adaptRewrittenModel() throws IOException {
		EMFModelPlantUMLSerializer serializer;
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
		
		serializer = new EMFModelPlantUMLSerializer((EPackage) witness.getMetamodel().getContents().get(0), witness.getModel());
		return serializer;
	}
	

}
