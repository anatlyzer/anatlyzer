package anatlyzer.atl.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OclModelElement;

/**
 * This contains utility functions to select relevant classes
 * for a problem.
 * 
 * @author jesus
 *
 */
public class ClassPicker {

	/**
	 * This strategy searches for oclIsKindOf occurrences and picks a sibling class to enable the
	 * alternative path occurring when the "oclIsKindOf" for this type is not valid.
	 * This works for oclIsType as well, although for oclIsTypeOf it would be possible to use
	 * a subtype instead of a sibling, it this is not available (not implemented yet) 
	 * 
	 * In the following example, binding "activities <- a.node" can be incorrectly resolved by ActivityNode2Vertex,
	 * so we need to select at least one class that provokes the binding problem. We normally select
	 * OpaqueAction since it is part of the slice, but we also need to select something that is not
	 * "OpaqueAction" to enable the "not" in practice. 
	 *
	 * <pre>
	 * rule ActivityPartition2Pool {
	 *		from a : UML!ActivityPartition
 	 * 	to  l : Intalio!Lane (
     *		activities <- a.node
     *	)
     *  }
     * 
     *	rule ActivityNode2Vertex {
	 *			from a : UML!ActivityNode( not a.oclIsKindOf(UML!OpaqueAction)  )
 	 *		to v : Intalio!Vertex
	 *	}
	 *	</pre>
	 * @return 
     */
	public static Set<EClass> treatOclIsKindOf_pickSubclassSibling(BindingResolution problem, GlobalNamespace namespace) {
		
		// Find class uses in the filters. If found, go to the superclass and pick a sibling.
		// TODO: Extend this idea to possibly invoked helpers
		HashSet<Metaclass> usages = new HashSet<Metaclass>();
		for(ResolvedRuleInfo rinfo : problem.getRules()) {
			for(EObject rule : rinfo.getAllInvolvedRules()) {
				MatchedRule mr = (MatchedRule) rule;
				if ( mr.getInPattern().getFilter() != null ) {
					mr.getInPattern().getFilter().eAllContents().forEachRemaining(e -> {
						if ( e instanceof OclModelElement && ((OclModelElement) e).getInferredType() instanceof Metaclass ) {
							usages.add((Metaclass) ((OclModelElement) e).getInferredType()); 
						}
					});				
				}
			}
		}
		
		Set<EClass> result = pickSubclasses(namespace, usages);	
		
		return result;
	}

	public static Set<EClass> pickSubclasses(GlobalNamespace namespace, Set<Metaclass> usages) {
		Set<EClass> result = new HashSet<EClass>();
		Set<EClass> usageEclasses = usages.stream().map(m -> m.getKlass()).collect(Collectors.toSet());
		GlobalNamespace global = namespace;
		for (Metaclass metaclass : usages) {
			IClassNamespace ns = (IClassNamespace) metaclass.getMetamodelRef();
			boolean picked = false;
			
			for (ClassNamespace sup : ns.getAllSuperClasses()) {
				Collection<ClassNamespace> subs = sup.getDirectSubclasses(global);
				for (ClassNamespace sub : subs) {
					if ( ! usageEclasses.contains( sub.getKlass() ) ) {
						System.out.println("picked! " + sub.getKlass());
						// slice.addMetaclassNeededInError(sub.getKlass());
						result.add(sub.getKlass());
						picked = true;
						break;
					}
				}
				
				if ( picked ) 
					break;
			}			
		}
		return result;
	}

	
}
