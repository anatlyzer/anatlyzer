package witness.visualizer.eclectic.modeling.emf;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Util {
	public static URI cwdURI(String path) {
		return URI.createURI("file:" + path);	
	}
	
	public static URI createURI(String path) {		
		return createURI(path, null);
	}
	
	public static URI createURI(String path, String cwd) {
		String uriString = path;
		if ( uriString.startsWith("platform:/resource/") ) {
			//this option depends on org.eclipse.resources
			//return URI.createPlatformResourceURI(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uriString)), true);
			return URI.createPlatformResourceURI(extract(uriString, "platform:/resource/"), true);
		}
		else if ( uriString.startsWith("platform:/plugin/") ) {
			return URI.createPlatformPluginURI(extract(uriString, "platform:/plugin/"), true);
		}
		else if ( uriString.startsWith("http:/") ) { // to allow loading http:/www.eclipse.org/emf/2002/Ecore, but this is not the general case
			return URI.createURI(path);
		}
	
		if ( cwd == null ) {
			return URI.createURI(path);
		} else {
			URI uri = URI.createFileURI(new File(path).getAbsolutePath());
			return uri;
		}
		//return URI.createURI(path).resolve(cwdURI(cwd));
	}	
	
	public static List<EPackage> castToEPackage(EList<EObject> objs) {
		LinkedList<EPackage> pkgs = new LinkedList<EPackage>();
		for (EObject object : objs) {
			pkgs.add((EPackage) object);
		}
		return pkgs;
	}	

	
	private static String extract(String s, String extract) {
		return s.replaceFirst("^" + extract, "");
	}	
	
	public static void registerResourceFactory() {
		if ( Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().size() == 0 ) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put
		       ("*", new XMIResourceFactoryImpl());
		}		
	}
	
	public static boolean isFeatureEnum(EStructuralFeature feature) {
		return EcorePackage.Literals.EENUM.isInstance(feature.getEType());		
	}
	
}
