package anatlyzer.atl.analyser.umlprofiles;

import java.util.HashMap;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;

import anatlyzer.atl.analyser.AbstractAnalyserVisitor;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.StringExp;

public class ProfileUsageAnalysis extends AbstractAnalyserVisitor {

	private HashMap<String, Stereotype> stereotypes = new HashMap<String, Stereotype>();
	private UMLErrorModel umlErrors;
	
	/**
	 * 
	 * @param model
	 * @param mm
	 * @param root
	 * @param profiles Logical names of the meta-model that are actually profiles
	 */
	public ProfileUsageAnalysis(ATLModel model, GlobalNamespace mm, Unit root, HashMap<String, Profile> profiles) {
		super(model, mm, root);

		for (Profile p : profiles.values()) {			
			locateStereotypes(p);			
		}
		
		this.umlErrors = new UMLErrorModel(model.getErrors());
	}
	
	public void locateStereotypes(Profile p) {
		for(Element e : p.allOwnedElements()) {
			if ( e instanceof Stereotype ) {
				Stereotype s = (Stereotype) e;
				stereotypes.put(s.getQualifiedName(), s);
			}
		}
	}
			
	public void perform() {
		startVisiting(root);
	}

	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		// Look for the pattern
		// <stereotype-value>.qualifiedName = 'aQualifiedName'
		if ( self.getName().equals("qualifiedName") && isStereotype(self.getSource().getInferredType()) ) {
			 if ( self.eContainer()	instanceof OperatorCallExp ) {
				 OperatorCallExp op = (OperatorCallExp) self.eContainer();
				 if ( op.getOperationName().equals("=") ) {
					 StringExp str = null;
					 if ( op.getSource() instanceof StringExp ) {
						 str = (StringExp) op.getSource();
					 } else if ( op.getArguments().get(0) instanceof StringExp ) {
						 str = (StringExp) op.getArguments().get(0);
					 }
					 
					 if ( str != null ) {
						 String stereotypeName = str.getStringSymbol();
						 findStereotype(stereotypeName, str);
						 
						 // System.out.println("Want to look for " + );
					 }
				 }
			 }
		}
	}

	private void findStereotype(String stereotypeQName, LocatedElement node) {
		if ( ! stereotypes.containsKey(stereotypeQName) ) {
			umlErrors.signalStereotypeNotFound(stereotypeQName, node);
		} 
	}

	private boolean isStereotype(Type t) {
		if ( t instanceof Metaclass ) {
			return ((Metaclass) t).getName().equals("Stereotype");
		}
		return false;
	}
	
	
}
