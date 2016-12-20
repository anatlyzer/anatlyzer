package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import anatlyzer.atl.analyser.batch.PossibleInvariantViolationNode;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.IWitnessVisualizer;
import anatlyzer.atl.witness.WitnessUtil;

public class InvariantExplanationDialog extends SimpleExplanationDialog {

	public InvariantExplanationDialog(Shell parentShell, PossibleInvariantViolationNode node) {
		super(parentShell, new InvariantExplanation(node));
	}

	public static class InvariantExplanation implements AtlProblemExplanation {
		private PossibleInvariantViolationNode node;

		public InvariantExplanation(PossibleInvariantViolationNode node) {
			this.node = node;
		}

		@Override
		public void setMarker(IMarker marker) {
			throw new IllegalStateException();
		}

		@Override
		public boolean isApplicable() {
			return true;
		}

		@Override
		public void setDetailedProblemDescription(StyledText text) {
			text.setText("This is the generated pre-condition: \n\n" + ATLSerializer.serialize(node.getPrecondition()));
		}

		@Override
		public void setAdditionalInfo(Composite composite) {
			
			IWitnessModel witness = node.getWitness();
			if (witness == null ) {
				Label l = new Label(composite, SWT.NONE);
				l.setText("No witness is available.");
				return;
			}
			
			IWitnessVisualizer visualizer = WitnessUtil.getWitnessVisualizer(witness);
			if (visualizer == null ) {
				Label l = new Label(composite, SWT.NONE);
				l.setText("No visualizer is available.");
				return;
			}
			
			visualizer.render(composite);
		}
	}
}
