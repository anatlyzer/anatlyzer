package anatlyzer.visualizer.views;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

public class RuleRelationshipsLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Font getFont(Object element) {
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if ( element instanceof Binding ) {
			Device device = Display.getCurrent ();
			return new Color(device, 150, 150, 150);			
		}
		else if ( element instanceof RuleResolutionInfo ) {
			Device device = Display.getCurrent ();
			return new Color(device, 64, 160, 64);
		}

		return null;
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	private static final String INDENT = "  ";
	
	@Override
	public String getText(Object element) {
		
		if ( element instanceof MatchedRule ) {
			MatchedRule r = (MatchedRule) element;
			String header = r.getName();
			if ( r instanceof MatchedRule ) {
				header = ruleToString((MatchedRule) r);
			}

			return r.getName();
		} else if ( element instanceof RuleResolutionInfo ) {			
			RuleResolutionInfo rri = (RuleResolutionInfo) element;
			Binding b = (Binding) rri.eContainer();
			
			String s = "";
			try { 
				s = ATLSerializer.serialize(b.getValue());
				s = s.length() < 10 ? s : s.substring(0, 10);				
			} catch (Exception e) { s = "..."; }

			return b.getPropertyName() + " <- " + s; 
		}
		return null;
	}

	private String ruleToString(MatchedRule r) {
		String s = "rule " + r.getName() + "\n";
		s = s + INDENT + "from " + r.getInPattern().getElements().stream().
				map(e -> e.getVarName() + " : " + TypeUtils.typeToString(e.getInferredType())).
				collect(Collectors.joining(", "));
		if ( r.getInPattern().getFilter() != null ) {
			String f = ATLSerializer.serialize(r.getInPattern().getFilter());
			s += " ( " + f + " )";
		}
		
		List<OutPatternElement> outs = ATLUtils.getAllOutputPatternElement(r);
		s = s + "\n" + INDENT + "to " + TypeUtils.typeToString(outs.get(0).getInferredType());
		for(int i = 1; i < outs.size(); i++) {
			s += "\n" + INDENT + "to " + TypeUtils.typeToString(outs.get(i).getInferredType());
		}
		
		
		return s;
	}

}
