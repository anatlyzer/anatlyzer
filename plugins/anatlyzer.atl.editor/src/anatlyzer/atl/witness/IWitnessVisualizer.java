package anatlyzer.atl.witness;

import org.eclipse.swt.widgets.Composite;

public interface IWitnessVisualizer {

	void setModel(IWitnessModel model);

	void render(Composite composite);

}
