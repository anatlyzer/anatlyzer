package anatlyzer.ocl.emf;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.ocl.examples.xtext.base.basecs.ConstraintCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.CompleteOCLDocumentCS;

public class OclEMFUtils {
	
	
	public static List<ConstraintCS> getInvariants(CompleteOCLDocumentCS doc) {
		return doc.getPackages().stream().
			flatMap(p -> p.getContexts().stream()).
			filter(c -> c instanceof ClassifierContextDeclCS).
			map(c -> (ClassifierContextDeclCS) c).			
			flatMap(c -> c.getInvariants().stream() ).
			collect(Collectors.toList());
	}
}
