package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import anatlyzer.atl.analyser.batch.PreconditionAnalysis.PreconditionIssue;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.IWitnessVisualizer;
import anatlyzer.atl.witness.WitnessUtil;

public class PreconditionExplanation implements AtlProblemExplanation {
		private PreconditionIssue node;

		public PreconditionExplanation(PreconditionIssue node) {
			this.node = node;
		}
		
		@Override
		public IWitnessModel getWitness() {
			return node.getWitness();
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
			// text.setText("This is the generated pre-condition: \n\n" + ATLSerializer.serialize(node.getPrecondition()));
			if ( node.getAnalysisError() != null ) {
//				
//				node.getAnalysisError().printStackTrace();
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				
//				node.getAnalysisError().printStackTrace(new PrintStream(os));
//				
//				text.setText( "There was an internal error processing the invariant: \n" + new String(os.toByteArray()) );
			} else {			
//				System.out.println("Original pre-condition:");
//				System.out.println(ATLSerializer.serialize(node.getPreconditionNorm()));
//				text.setText("This is the generated pre-condition (in ATL): \n\n" + ATLSerializer.serialize(node.getPreconditionATL()));
			}
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
