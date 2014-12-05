package anatlyzer.footprint;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A call site indicates the inferred computed type
 * of the receptor object of a call.
 * 
 * Example:
 * 
 * <pre>
 * class.features->select( f | f.oclIsKindOf(Reference) )->select( r | r.type)
 * </pre>
 * 
 * The call site of r.type is <Reference, TypedElt.type> 
 *
 * @author jesus
 *
 */
public class CallSite {
	private EStructuralFeature feature;
	private EClass receptor;

	public CallSite(EClass receptor, EStructuralFeature feature) {
		this.receptor = receptor;
		this.feature = feature;
	}

	public EClass getReceptor() {
		return receptor;
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result
				+ ((receptor == null) ? 0 : receptor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallSite other = (CallSite) obj;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "<" + receptor.getName() + ", " + feature.getEContainingClass().getName() + "." + feature.getName() +">";
	}
}
