package anatlyzer.experiments.typing;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.m2m.atl.core.ATLCoreException;

import transML.exceptions.transException;
import anatlyzer.evaluation.tester.Tester;


public class QuickfixEvaluationByMutation extends QuickfixEvaluationAbstract {
	
	public QuickfixEvaluationByMutation() {
	}

	@Override
	public void perform(IResource resource) {
		String trafoName = "mutants_" + resource.getLocation().removeFileExtension().lastSegment();
		IFolder folder = null;
		int i = 0;
		while ( i < 100 ) {
			i++;
			folder = resource.getProject().getFolder(trafoName + i);
			if ( ! folder.exists() ) {
				try {
					folder.create(true, true, null);
				} catch (CoreException e) {
					return;
				}
				break;
			}			
		}
		
		try {
			anatlyzer.evaluation.tester.Tester tester = new Tester(
						resource.getLocation().toPortableString(),
						folder.getLocation().toPortableString()
					);
			tester.generateMutants();
		} catch (ATLCoreException | transException e) {
			e.printStackTrace();
		}
		
		
		//anatlyzer.evaluation.tester.Tester tester = new Tester(path-transformacion, path-salida-mutantes);
		//tester.generateMutants(); 
		
		
	}
}
