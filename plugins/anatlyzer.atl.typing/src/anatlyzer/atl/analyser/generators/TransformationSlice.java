package anatlyzer.atl.analyser.generators;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Rule;

/**
 * 
 * TODO: It would be interesting to generate the elements in the same
 *       order as they appear in the original transformation.
 * 
 * @author jesus
 *
 */
public class TransformationSlice {

	private ATLModel atlModel;
	private HashSet<Rule> rules = new HashSet<Rule>();
	
	public TransformationSlice(ATLModel originalModel) {
		this.atlModel = originalModel;
	}

	public void addRule(Rule r) {
		rules.add(r);
	}

	public ATLModel doSlice() {
		System.out.println("TransformationSlice.doSlice()");
		/*
		Module oriModule = atlModel.allObjectsOf(Module).get(0);
		Module newModule = emptyModel.create(Module.class);
		
		newModule.setName(oriModule.getName());
		for(String s : oriModule.getCommentsBefore()) {
			if ( s.contains("@") ) 
				newModule.addCommentsBefore(s);
		}
		*/
		
		HashSet<EObject> toBeCopied = new HashSet<EObject>();
		
		HashSet<Object> contents = new HashSet<Object>();
		contents.addAll(rules);
		
		List<? extends LocatedElement> elements = atlModel.allObjectsOf(LocatedElement.class);
		for(LocatedElement e : elements) {
			if ( e.eContainer() == null ) {
				toBeCopied.add(e); // ocl model elements that are there...
			}
		}

		/*
		for(LocatedElement e : elements) {
			if ( contents.contains(e) ) {
				toBeCopied.add(e.original_());
			} else if ( e.container_() == null && ! (e instanceof Unit) ) {
				toBeCopied.add(e.original_()); // ocl model elements that are there...
			} else if ( e instanceof OclModel ) {
				// Just to make sure that OclModel elements of Unit elements (meta-model descriptions) are included
				toBeCopied.add(e.original_());
			}
			
		}
		*/

		ATLModel newModel = new ATLModel();
		Resource rs = newModel.getResource();

		// Resource rs = new ResourceImpl();

		// Copy
		Copier copier = new Copier();
	    Collection<EObject> result = copier.copyAll(toBeCopied);
	    copier.copyReferences();	    
		rs.getContents().addAll(result);
		
		// Remove unused things
		for(ModuleElement e : atlModel.allObjectsOf(ModuleElement.class)) {
			if ( ! contents.contains(e) ) {
				EcoreUtil.delete(copier.get(e), true);
			}
		}

		
		/*
		Module newModule = newModel.create(Module.class);
		Module oriModule = atlModel.allObjectsOf(Module.class).get(0);

		newModule.setName(oriModule.getName());
		for(String s : oriModule.getCommentsBefore()) {
			if ( s.contains("@") ) 
				newModule.addCommentsBefore(s);
		}
		
		for(ModuleElement e : newModel.allObjectsOf(ModuleElement.class)) {
			newModule.addElements(e);
		}
		*/
		return newModel;
	}
	
	
}
