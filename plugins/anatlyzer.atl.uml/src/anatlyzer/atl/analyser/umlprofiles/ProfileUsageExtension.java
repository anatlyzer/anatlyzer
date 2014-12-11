package anatlyzer.atl.analyser.umlprofiles;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPlugin;

import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Unit;

public class ProfileUsageExtension implements AnalyserExtension {


	public void perform(ATLModel model, GlobalNamespace mm, Unit root) {
		HashMap<String, Profile> profiles = loadProfiles(model, mm);
		new ProfileUsageAnalysis(model, mm, root, profiles).perform();
	}


	private HashMap<String, Profile> loadProfiles(ATLModel atlModel, GlobalNamespace mm) {
		ResourceSet rs = new ResourceSetImpl();
		List<String> profileTags = ATLUtils.findCommaTags(atlModel.getModule(), "profile");		
		HashMap<String, Profile> profiles = new HashMap<String, Profile>();
		for (String tagValue : profileTags) {	
			String[] elems  = tagValue.split("=");
			if ( elems.length == 0 || elems.length > 2)
				continue;
			
			Profile p = null;
			String name = elems[0].trim();				
			MetamodelNamespace ns = mm.getNamespace(name);
			if ( elems.length == 1 ) {
				p = loadInMemoryProfile(rs, ns);
			} else if ( elems.length == 2 ) {
				p = loadFileProfile(rs, elems[1].trim());
			}				
			profiles.put(name, p);
		}
	
		return profiles;
	}


	private Profile loadInMemoryProfile(ResourceSet rs, MetamodelNamespace m) {
		EPackage pkg = (EPackage) m.getResource().getContents().get(0);
		URI profileURI = UMLPlugin.getEPackageNsURIToProfileLocationMap().get(pkg.getNsURI());
		@SuppressWarnings("unused")
		Resource profileResource = rs.getResource(profileURI, true);			
		Profile profile = (Profile) rs.getEObject(profileURI, true);
		return profile;
	}
	
	private Profile loadFileProfile(ResourceSet rs, String uri) {
		URI profileURI = URI.createPlatformResourceURI(uri, true);
		Resource profileResource = rs.getResource(profileURI, true);			
		return (Profile) profileResource.getContents().get(0);
	}
}
