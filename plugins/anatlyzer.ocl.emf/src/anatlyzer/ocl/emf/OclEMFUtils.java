package anatlyzer.ocl.emf;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;

public class OclEMFUtils {
	
	public static List<ConstraintCS> getInvariants(CompleteOCLDocumentCS doc) {
		return doc.getOwnedPackages().stream().
			flatMap(p -> p.getOwnedContexts().stream()).
			filter(c -> c instanceof ClassifierContextDeclCS).
			map(c -> (ClassifierContextDeclCS) c).			
			flatMap(c -> c.getOwnedInvariants().stream() ).
			collect(Collectors.toList());
	}
	
	public static List<DefCS> getOperations(CompleteOCLDocumentCS doc) {
		return doc.getOwnedPackages().stream().
			flatMap(p -> p.getOwnedContexts().stream()).
			filter(c -> c instanceof ClassifierContextDeclCS).
			map(c -> (ClassifierContextDeclCS) c).			
			flatMap(c -> c.getOwnedDefinitions().stream() ).
			collect(Collectors.toList());
	}	
}
