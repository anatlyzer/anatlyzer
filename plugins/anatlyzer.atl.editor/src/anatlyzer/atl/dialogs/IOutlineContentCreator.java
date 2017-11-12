package anatlyzer.atl.dialogs;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public interface IOutlineContentCreator {

	ITreeContentProvider createOutlineContentProvider();

	Object getOutlineInput();

	ILabelProvider createOutlineLabelProvider();

	void goToElement(Object selectedElement);

}
