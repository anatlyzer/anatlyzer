/**
 * An abstract superclass is extracted in the hierarchy, and a set of common properties is pulled on. 
 */
package testing.metamodel.mutators.breaking;

import java.util.*;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class ExtractAbstractSuperclass extends AbstractMutator {	
	private MMResource mm;
	private boolean DEBUG = false;

	@Override
	public void mutate(MMResource metamodel) {
		this.mm = metamodel;
		// obtain candidate classes
		Map<Set<EStructuralFeature>, Set<EClass>> groups = new HashMap<>();
		
		for (EClass c: metamodel.getEClasses()) {
			Map<Set<EStructuralFeature>, Set<EClass>> similars = this.groupClassesByFeaturesOf(c, c.getEStructuralFeatures());
			groups.putAll(similars);
		}
		
		for (Set<EStructuralFeature> features : groups.keySet()) {
			Set<String> fnames = features.stream().map(f -> f.getName()).collect(Collectors.toSet());
			Set<String> classNames = groups.get(features).stream().map(f -> f.getName()).collect(Collectors.toSet());
			if (DEBUG)
				if (classNames.size()>1) {
					System.out.println("Classes: "+classNames+" have features "+fnames);
					System.out.println("Features-2: "+features);
				}
		}
		
		Set<Set<EClass>> distinct = groups.values().stream().filter( set -> set.size()>1 ).collect(Collectors.toSet());
		
		EcoreFactory factory  = EcoreFactory.eINSTANCE;
		EClass       newClass = factory.createEClass();
		newClass.setAbstract( isAbstract() );
		
		Map<EClass, List<EStructuralFeature>> deleted = new HashMap<>();
		
		for (Set<EClass> classes : distinct) {
			deleted.clear();
			Set<EStructuralFeature> featus = this.getFeatures(classes, groups);
			this.addAllFeatures(newClass, featus);
			// generate each possible mutant
			boolean init = true;
			EPackage pack = null;
			for (EClass clazz : classes) {
				if (init) {
					pack = (EPackage)clazz.eContainer(); 					
					pack.getEClassifiers().add(newClass);
					init = false;
					newClass.setName(clazz.getName());
				}
				else newClass.setName(newClass.getName()+clazz.getName());
				clazz.getESuperTypes().add(newClass);				
				deleted.put(clazz, new ArrayList<EStructuralFeature>());
				for (EStructuralFeature sf : featus) {
					EStructuralFeature ownedFeature = this.get(clazz, sf.getName());
					//EStructuralFeature ownedFeature = clazz.getEStructuralFeature(sf.getName()); // THIS DOES NOT WORK
					if (DEBUG) System.out.println("feature : "+ownedFeature);
					deleted.get(clazz).add(ownedFeature);
					clazz.getEStructuralFeatures().remove(ownedFeature);
					
					if (ownedFeature instanceof EReference && ((EReference)ownedFeature).getEOpposite()!=null) { // bidirectional case
						EReference opposite          = ((EReference)ownedFeature).getEOpposite();
						EClass     containerOpposite = opposite.getEContainingClass();
						containerOpposite.getEStructuralFeatures().remove(opposite);
						if (!deleted.containsKey(containerOpposite))
							deleted.put(containerOpposite, new ArrayList<EStructuralFeature>());
						deleted.get(containerOpposite).add(opposite);
					}
				}
			}
			// register mutation
			Collection<ENamedElement> elements = new ArrayList<ENamedElement>();
			elements.add(newClass);
			elements.addAll(newClass.getEStructuralFeatures());
			EAnnotation annotation = registerMutation(elements.toArray(new ENamedElement[0]), null, null);
			// TODO: register deleted classes
						
			
			EPackage fPack = pack;
			registerUndo(metamodel, () -> {

				// unregister mutation
				unregisterMutation(annotation);
				
				// undo
				fPack.getEClassifiers().remove(newClass);
				for (EClass clazz : classes) {
					clazz.getESuperTypes().remove(newClass);
					for (EStructuralFeature sf : deleted.get(clazz)) {
						clazz.getEStructuralFeatures().add(sf);
						if (sf instanceof EReference && ((EReference)sf).getEOpposite()!=null ) { // bidirectional case
							((EReference)sf).getEOpposite().setEType(clazz);
							((EClass)sf.getEType()).getEStructuralFeatures().add(((EReference)sf).getEOpposite());
						}
					}
				}
				for (EReference f : newClass.getEReferences()) {
					newClass.getEStructuralFeatures().remove(f);
					if (f.getEOpposite() != null)
						f.getEOpposite().getEContainingClass().getEStructuralFeatures().remove(f.getEOpposite());
				}
				newClass.getEStructuralFeatures().clear();
			
			});
		}
	}
	
	protected boolean isAbstract () {
		return true; // the extracted superclass should be abstract
	}
	
	private EStructuralFeature get (EClass c, String f) {
		for (EStructuralFeature feat : c.getEStructuralFeatures()) {
			if (feat.getName().equals(f)) return feat;
		}
		return null;
	}

	private void addAllFeatures(EClass newClass, Set<EStructuralFeature> featus) {
		for (EStructuralFeature f : featus) {
			if (f instanceof EReference)
				 clone((EReference)f, newClass, null);
			else clone((EAttribute)f, newClass, null);			
		}
	}

	private Set<EStructuralFeature> getFeatures(Set<EClass> classes, Map<Set<EStructuralFeature>, Set<EClass>> groups) {
		List<Set<EStructuralFeature>> candidates = new ArrayList<>();
		
		for (Set<EStructuralFeature> feats : groups.keySet()) {
			if (groups.get(feats).equals(classes)) candidates.add(feats);
		}
		// Now return the biggest
		int max = 1;
		Set<EStructuralFeature> selected = null;
		for (Set<EStructuralFeature> feats : candidates) {
			if (feats.size() >= max) selected = feats;
		}
		return selected;
	}
	
	private void print(List<List<EStructuralFeature>> fs) {
		for (List<EStructuralFeature> f : fs) {
			System.out.print("[");
			for (EStructuralFeature sf : f) {
				System.out.print(sf.getName()+" ");
			}
			System.out.println("]");
		}
	}
	
	private Map<Set<EStructuralFeature>, Set<EClass>> groupClassesByFeaturesOf(EClass c, EList<EStructuralFeature> feats) {
		Map<Set<EStructuralFeature>, Set<EClass>> result = new HashMap<>();
		List<List<EStructuralFeature>> allCombs = this.allCombinations(feats);
		
		if (DEBUG) this.print(allCombs);
		for (List<EStructuralFeature> comb : this.allCombinations(feats)) {
			HashSet<EClass> set = new HashSet<>();
			set.add(c);
			result.put(new HashSet<EStructuralFeature>(comb), set);
		}
		for (EClass cl : mm.getEClasses()) {
			if (cl.equals(c)) continue;
			if (cl.getEAllSuperTypes().contains(c)) continue;
			if (c.getEAllSuperTypes().contains(cl)) continue;
			for (Set<EStructuralFeature> features : result.keySet()) {
				if (this.hasAll(features, cl)) {
					result.get(features).add(cl);					
				}
			}
		}

		return result;
	}
	
	private boolean hasAll(Set<EStructuralFeature> features, EClass cl) {		
		for (EStructuralFeature f : features) {
			if (! this.hasFeature(f, cl)) return false;
		}
		return true;
	}
	
	private boolean hasFeature(EStructuralFeature sf, EClass cl) {
		for (EStructuralFeature f : cl.getEStructuralFeatures()) {
			if (f.getName().equals(sf.getName()) && f.getLowerBound()==sf.getLowerBound() && f.getUpperBound()==sf.getUpperBound()) {
				if ( (f instanceof EAttribute) && (sf instanceof EAttribute) ) {
					EAttribute fa = (EAttribute)f;
					EAttribute fsf = (EAttribute)sf;
					if (fa.getEAttributeType().equals(fsf.getEAttributeType())) return true;
				}
				else if ( (f instanceof EReference) && (sf instanceof EReference) ) {
					EReference fa = (EReference)f;
					EReference fsf = (EReference)sf;
					if (fa.getEReferenceType().equals(fsf.getEReferenceType())) return true;
				}					
			}
		}
		return false;
	}
	
	private List<List<EStructuralFeature>> allCombinations(List<EStructuralFeature> feats) {
		List<List<EStructuralFeature>> combs = new ArrayList<>();
		for (int i = 1; i <= feats.size(); i++) {
			combs.addAll(this.allCombinationsOf(feats, i));			
		}
		return combs;
	}
	
	private List<List<EStructuralFeature>> allCombinationsOf(List<EStructuralFeature> feats, int size) {
		List<List<EStructuralFeature>> combs = new ArrayList<>();
		int index[] = new int[size];
		for (int i = 0; i < size; i++) {
			index[i] = i;
		}
		boolean finished = false;
		while (! finished) {
			List<EStructuralFeature> common = new ArrayList<EStructuralFeature>();
			for (int i = 0; i < size; i ++ ) {
				common.add(feats.get(index[i]));
				if (DEBUG) System.out.print(feats.get(index[i]).getName()+" ");
			}
			if (DEBUG) System.out.println();
			combs.add(common);
			finished = this.moveIndex(index, size, feats.size()-1);
		}
		return combs;
	}
	
	private boolean moveIndex(int[] index, int size, int max) {
		int candidate = size-1;
		int maximum = max;
		
		while (true) {
			while (index[candidate] == maximum) {
				candidate--;
				if (candidate < 0) return true;
				// now reset all upper candidates...
				maximum--;
				if (index[candidate]<maximum) {
					index[candidate]++;
					for (int i = candidate + 1; i < size; i++ ) {
						index[i] = index[i-1]+1;
					}
					return false;
				}
			}
			index[candidate]++;
			return false;
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}
}
