package anatlyzer.atl.editor.views;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
/**
 * From: http://davymeers.net/2010/07/04/jface-using-columnviewers-combined-with-decoration-and-custom-tooltips/
 * @author jesus
 *
 */
public class TooltipSupport {
	public static class ViewDecoratingStyledCellLabelProvider extends
			DecoratingStyledCellLabelProvider {
		private final IStyledLabelProvider labelProvider;

		public ViewDecoratingStyledCellLabelProvider(
				IStyledLabelProvider labelProvider, ILabelDecorator decorator,
				IDecorationContext decorationContext) {
			super(labelProvider, decorator, decorationContext);
			this.labelProvider = labelProvider;
		}

		@Override
		public Color getToolTipBackgroundColor(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipBackgroundColor(object);
			}
			return super.getToolTipBackgroundColor(object);
		}

		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipDisplayDelayTime(object);
			}
			return super.getToolTipDisplayDelayTime(object);
		}

		@Override
		public Font getToolTipFont(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipFont(object);
			}
			return super.getToolTipFont(object);
		}

		@Override
		public Color getToolTipForegroundColor(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipForegroundColor(object);
			}
			return super.getToolTipForegroundColor(object);
		}

		@Override
		public Image getToolTipImage(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipImage(object);
			}
			return super.getToolTipImage(object);
		}

		@Override
		public Point getToolTipShift(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipShift(object);
			}
			return super.getToolTipShift(object);
		}

		@Override
		public int getToolTipStyle(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipStyle(object);
			}
			return super.getToolTipStyle(object);
		}

		@Override
		public String getToolTipText(Object element) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipText(element);
			}
			return super.getToolTipText(element);
		}

		@Override
		public int getToolTipTimeDisplayed(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.getToolTipTimeDisplayed(object);
			}
			return super.getToolTipTimeDisplayed(object);
		}

		@Override
		public boolean useNativeToolTip(Object object) {
			if (labelProvider instanceof CellLabelProvider) {
				return ((CellLabelProvider) labelProvider)
						.useNativeToolTip(object);
			}
			return super.useNativeToolTip(object);
		}
	}

	public static class ViewColumnViewerToolTipSupport extends
			ColumnViewerToolTipSupport {

		protected ViewColumnViewerToolTipSupport(ColumnViewer viewer,
				int style, boolean manualActivation) {
			super(viewer, style, manualActivation);
		}

		/*
		@Override
		protected Composite createViewerToolTipContentArea(Event event,
				ViewerCell cell, Composite parent) {
			final Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new RowLayout(SWT.VERTICAL));
			Text text = new Text(composite, SWT.SINGLE);
			text.setText(getText(event));
			text.setSize(100, 60);
			DateTime calendar = new DateTime(composite, SWT.CALENDAR);
			calendar.setEnabled(false);
			calendar.setSize(100, 100);
			composite.pack();
			return composite;
		}
		*/

		public static final void enableFor(final ColumnViewer viewer) {
			new ViewColumnViewerToolTipSupport(viewer, ToolTip.NO_RECREATE,
					false);
		}
		
		
	}

}
