package anatlyzer.visualizer.views;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atlext.ATL.OutPatternElement;

public class ElementConnectionsLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {

	
	@Override
	public void addListener(ILabelProviderListener listener) {
	
	}
	
	@Override
	public void dispose() {
	
	}
	
	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public Font getFont(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Color getBackground(Object element) {
		if ( element instanceof Cluster ) {
			Device device = Display.getCurrent ();
			return new Color(device, 90, 64, 128);			
		}
		else if ( element instanceof Node && ((Node) element).isRootNode() ) {
			Device device = Display.getCurrent ();
			return new Color(device, 64, 128, 90);
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getText(Object element) {
		if ( element instanceof Cluster ) {
			return ((Cluster) element).getId() + "";
		} if ( element instanceof Node ) {
			OutPatternElement out = ((Node) element).getOut();
			return out.getOutPattern().getRule().getName() + "\n" + out.getVarName() + " : " + TypeUtils.typeToString(out.getInferredType());
		}
		return null;
	}
	

}
