package anatlyzer.useocl.ui;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.witness.IWitnessModel;

public class WitnessProvider implements IStructuredContentProvider, ILabelProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if ( inputElement instanceof WitnessModelList ) {
			return ((WitnessModelList) inputElement).models.toArray();
		}
		return new Object[0];
	}

	public static class WitnessModelList {
		private ArrayList<WitnessModel> models = new ArrayList<>();
		
		public WitnessModel createModel(IWitnessModel m) {
			WitnessModel wm = new WitnessModel("Result " + (models.size() + 1), m);			
			models.add(wm);
			return wm;
		}
	}
	
	public static class WitnessModel {
		private String name;
		private IWitnessModel model;

		public WitnessModel(String name, IWitnessModel m) {
			this.name  = name;
			this.model = m;
		}
		
		public IWitnessModel getModel() {
			return model;
		}
		
	}

	//
	// ILabelProvider
	//
	
	@Override
	public void addListener(ILabelProviderListener listener) { }

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) { }

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if ( element instanceof WitnessModel ) {
			return ((WitnessModel) element).name;
		}
		return null;
	}

}
