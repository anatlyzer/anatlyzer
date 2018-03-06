package anatlyzer.atl.simplifier;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.IAnalyserResult;

public interface IOclSimplifier {

	EObject simplify(IAnalyserResult r, EObject obj);
	
}
