package witness.visualizer.utils;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;


public class PredicateUtils {
	public static final IPredicate<EClassifier> FILTER_CLASS = new IPredicate<EClassifier>() {
		@Override public boolean applies(EClassifier e) {
			return (e instanceof EClass);
		}		
	};
	
	public static final IPredicate<EClassifier> FILTER_CONCRETECLASS = new IPredicate<EClassifier>() {
		@Override public boolean applies(EClassifier e) {
			return (e instanceof EClass && !((EClass)e).isAbstract());
		}		
	};
}
