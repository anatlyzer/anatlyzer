package anatlyzer.atl.analyser.generators;

import org.eclipse.emf.ecore.EObject;

public interface IObjectVisitor {
	public void perform(EObject root);
}
