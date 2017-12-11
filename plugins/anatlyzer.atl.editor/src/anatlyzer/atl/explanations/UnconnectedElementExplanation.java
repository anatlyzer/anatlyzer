package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.witness.IWitnessModel;

public class UnconnectedElementExplanation implements AtlProblemExplanation {

	private Cluster cluster;

	public UnconnectedElementExplanation(Cluster cluster) {
		this.cluster = cluster;
	}

	@Override
	public void setMarker(IMarker marker) {
		// No need
	}

	@Override
	public IWitnessModel getWitness() {
		return null;
	}

	@Override
	public boolean isApplicable() {
		return true;
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		for (Node node : cluster.getRootNodes()) {
			text.append("Nodes generated from: " + node.getOut().getOutPattern().getRule().getName() + "\n" );
			node.getGeneratedNodes().forEach(o -> text.append("  - " + o.getOut().getOutPattern().getRule().getName() + "\n"));
		}
	}

	@Override
	public void setAdditionalInfo(Composite scrolledComposite) {
		// Nothing for the moment
	}

}
