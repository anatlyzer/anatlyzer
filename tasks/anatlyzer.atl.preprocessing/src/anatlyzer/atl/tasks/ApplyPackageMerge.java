package anatlyzer.atl.tasks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import tinytools.metamodel.merger.Merger;
import tinytools.metamodel.merger.MergerOptions;

public class ApplyPackageMerge {

	public static void main(String[] args) throws Exception {
		ResourceSet rs = new ResourceSetImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		// GenModelPackage.eINSTANCE
		rs.getPackageRegistry().put(GenModelPackage.eINSTANCE.getNsURI(), GenModelPackage.eINSTANCE);
		
//		rs.getURIConverter().getURIMap().put(
//				URI.createURI("platform:/resource/anatlyzer.atl.typing/model/original/ATLStatic.ecore"),
//				URI.createURI("file:///home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/original/ATLStatic.ecore"));

		rs.getURIConverter().getURIHandlers().add(new URIHandlerImpl() {
			@Override
			public boolean canHandle(URI uri) {
				System.out
						.println("TestPackageMerge.testGenerateATL().new URIHandlerImpl() {...}.canHandle()");
				// TODO Auto-generated method stub
				return super.canHandle(uri);
			}
			@Override
			public InputStream createInputStream(URI uri, Map<?, ?> options)
					throws IOException {
				System.out.println(uri);
				return super.createInputStream(uri, options);
			}			
		});
		
		rs.getURIConverter().getURIMap().put(
				URI.createURI("/home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/types.ecore"),
				URI.createURI("/resource/anatlyzer.atl.typing/model/types.ecore"));

		Resource receiving = rs.getResource(URI.createURI("file:///home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/generated/ATLmodified.ecore"), true);
		Resource merged    = rs.getResource(URI.createURI("file:///home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/ext.ecore"), true);
		// Resource merged    = rs.getResource(URI.createURI("platform:/resource/anatlyzer.atl.typing/model/ext.ecore"), true);
		
		MergerOptions options = new MergerOptions ();
		
		Merger compiler = new Merger();
		Resource target = compiler.compile(receiving, merged, options);
		rs.getResources().add(target);
		rs.getURIConverter().getURIMap().put(
				URI.createURI("file:///home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/generated/ATLModified.ecore"),
				URI.createURI("platform:/resource/anatlyzer.atl.typing/model/original/ATLModified.ecore"));

		
		target.save(new FileOutputStream("/home/jesus/projects/genericity/atlide/anatlyzer.atl.typing/model/generated/merged.ecore"), null);
	}

}
