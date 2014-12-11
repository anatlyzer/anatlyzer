package anatlyzer.atl.tasks;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import tinytools.metamodel.copier.CopierCreator;
import tinytools.metamodel.copier.CopierMatcher;
import tinytools.metamodel.copier.CopierOptions;

public class GenerateCopier {

	public static void main(String[] args) throws Exception {
		ResourceSet rs = new ResourceSetImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		// GenModelPackage.eINSTANCE
		rs.getPackageRegistry().put(GenModelPackage.eINSTANCE.getNsURI(), GenModelPackage.eINSTANCE);
		
		Resource r = rs.getResource(URI.createURI("file:///home/jesus/projects/anatlyzer/plugins/anatlyzer.atl.typing/model/original/ATLStatic.ecore"), true);
		// Resource r2 = rs.getResource(URI.createURI("file:///home/jesus/projects/genericity/compiler/genericity.compiler.atl.typing/model/ATLExt.ecore"), true);
		Resource r3 = rs.getResource(URI.createURI("file:///home/jesus/projects/anatlyzer/plugins/anatlyzer.atl.typing/model/generated/merged.genmodel"), true);
		
		CopierOptions options = new CopierOptions ();
		// options.setBaseDir("tmp_");
		options.setBaseDir("/home/jesus/projects/anatlyzer/plugins/anatlyzer.atl.typing/src-gen");
		options.setPackagePrefix("anatlyzer.atlext.processing");
		options.setCopierClass("AbstractDynamicToStaticCopier");
		options.setMatcher(new CopierForModifiedATL());
		
		CopierCreator compiler = new CopierCreator();
		compiler.compile(r, r3, options);
	}

	public static class CopierForModifiedATL implements CopierMatcher {

		@Override
		public boolean match(String original, String target) {
			if ( original == null ) throw new IllegalArgumentException();
			if ( target   == null ) throw new IllegalArgumentException();
			
			return original.equals(target) ||
				   (original.equals("LazyMatchedRule") && target.equals("LazyRule") );
		}
		
	}
}
