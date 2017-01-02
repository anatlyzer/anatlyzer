package anatlyzer.experiments.export;

import java.util.ArrayList;
import java.util.List;

public class SimpleArtefact implements IClassifiedArtefact {

	private String name;
	private ArrayList<IHint> hints = new ArrayList<IHint>();
	
	public SimpleArtefact(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void addHing(SimpleHint hint) {
		this.hints.add(hint);
	}
	
	@Override
	public List<IHint> getHints() {
		return hints;
	}

	@Override
	public String getId() {
		return getName();
	}

}
