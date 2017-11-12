package anatlyzer.visualizer.views;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.EntityConnectionData;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;
import org.eclipse.zest.core.widgets.ZestStyles;

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
import anatlyzer.atlext.ATL.RuleResolutionStatus;

public class ResolveBindingLabelProvider implements ILabelProvider, IColorProvider, IFontProvider, IConnectionStyleProvider {

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
		
		if ( element instanceof Binding ) {
			Binding b = (Binding) element;
			Rule r = ATLUtils.getRule(b);
			String header = r.getName();
			if ( r instanceof MatchedRule ) {
				header = ruleToString((MatchedRule) r);
			}
			
			String t1 = TypeUtils.typeToString(b.getLeftType());
			String t2 = TypeUtils.typeToString(b.getValue().getInferredType());
			return header + "\n\n" + 
					INDENT + INDENT + t1 + " <- " + t2 + "\n" +		
					INDENT + INDENT + b.getPropertyName() + " <- " + ATLSerializer.serialize(b.getValue());
		} else if ( element instanceof RuleResolutionInfo ) {
			RuleResolutionInfo rri = (RuleResolutionInfo) element;
			MatchedRule r = (MatchedRule) rri.getRule();
			return ruleToString(r);
			//if ( r.getInPattern().getFilter() != null ) {
				// String s = ATLSerializer.serialize(r.getInPattern().getFilter());
			//	ri.getRuleName() + "\n" + "(" + s + ")";
			// }
			// TODO: Print filters!
			// return ri.getRuleName();
		} else if ( element instanceof RuleResolutionInfo ) {
			RuleResolutionInfo rri = (RuleResolutionInfo) element;
			return ruleToString(rri.getRule());
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

	// IConnectionStyleProvider
	
	
	@Override
	public int getConnectionStyle(Object rel) {
		if ( rel instanceof EntityConnectionData ) {
			EntityConnectionData d = (EntityConnectionData) rel;
			RuleResolutionInfo rri = (RuleResolutionInfo) d.dest;

			switch ( rri.getStatus() ) {
			case RESOLUTION_DISCARDED: return ZestStyles.CONNECTIONS_DASH_DOT;
			case RESOLUTION_UNKNOWN: return ZestStyles.CONNECTIONS_DOT;
			case RESOLUTION_CONFIRMED: return ZestStyles.CONNECTIONS_SOLID;			
			}			
		}

		return 0;
	}

	@Override
	public Color getColor(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getHighlightColor(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLineWidth(Object rel) {
		if ( rel instanceof EntityConnectionData ) {
			EntityConnectionData d = (EntityConnectionData) rel;
			RuleResolutionInfo rri = (RuleResolutionInfo) d.dest;

			switch ( rri.getStatus() ) {
			case RESOLUTION_DISCARDED: return 0;
			case RESOLUTION_UNKNOWN: return 0;
			case RESOLUTION_CONFIRMED: return 1;			
			}			
		}

		return 0;
	}

	@Override
	public IFigure getTooltip(Object entity) {
		return null;
	}

}
