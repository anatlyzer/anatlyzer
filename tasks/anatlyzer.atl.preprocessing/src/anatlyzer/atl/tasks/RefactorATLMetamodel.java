package anatlyzer.atl.tasks;

import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class RefactorATLMetamodel {
	private Resource r;
	
	public static void main(String[] args) throws Exception {
		ResourceSet rs = new ResourceSetImpl();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		
//		rs.getURIConverter().getURIMap().put(URI.createURI("model/generated/ATLmodified.ecore"), 
//				URI.createURI("ATLmodified.ecore"));
		
		Resource original = rs.getResource(URI.createURI("file:///home/jesus/projects/anatlyzer/plugins/anatlyzer.atl.typing/model/original/ATLStatic.ecore"), true);
		// Resource resource = rs.createResource(URI.createFileURI("model/generated/ATLmodified.ecore"));
		Resource resource = rs.createResource(URI.createURI("file:///home/jesus/projects/anatlyzer/plugins/anatlyzer.atl.typing/model/generated/ATLmodified.ecore"));		
		resource.getContents().addAll(EcoreUtil.copyAll(original.getContents()));
		
		new RefactorATLMetamodel(resource).refactor();
		
		resource.save(null);
	}
	
	public RefactorATLMetamodel(Resource original) {
		this.r = original;
	}

	public void refactor() {
		EClass callable = EcoreFactory.eINSTANCE.createEClass();
		callable.setName("Callable");
		callable.setAbstract(true);

		EClass moduleCallable = EcoreFactory.eINSTANCE.createEClass();
		moduleCallable.setName("ModuleCallable");
		moduleCallable.setAbstract(true);
		moduleCallable.getESuperTypes().add(callable);

		EClass staticRule = EcoreFactory.eINSTANCE.createEClass();
		staticRule.setName("StaticRule");
		staticRule.setAbstract(true);
		staticRule.getESuperTypes().add(moduleCallable);
		
		for(EClass c : getAllOfType(r, EClass.class)) {
			if ( c.getName().equals("MatchedRule") ) {
				c.setName("RuleWithPattern");
				c.setAbstract(true);

				EClass newMatchedRule = EcoreFactory.eINSTANCE.createEClass();
				newMatchedRule.setName("MatchedRule");				
				newMatchedRule.getESuperTypes().add(c);
				
				int idx = c.getEPackage().getEClassifiers().indexOf(c);
				c.getEPackage().getEClassifiers().add(idx + 1, newMatchedRule);
			}
			else if ( c.getName().equals("LazyMatchedRule") ) {
				c.setName("LazyRule");
				c.getESuperTypes().add(staticRule);
			} else if ( c.getName().equals("InPattern") ) {
				// This is needed because rule : MatchedRule which is no longer
				// valid because it should be rule : RuleWithPattern, but
				// the CopierCreator does not support this type of mapping, so
				// it is easier to remove it here.
				EcoreUtil.delete( c.getEStructuralFeature("rule") );								
			} else if ( c.getName().equals("CalledRule") ) {
				c.getESuperTypes().clear();
				c.getESuperTypes().add(staticRule);
			} else if ( c.getName().equals("Rule") ) {
				staticRule.getESuperTypes().add(c);		
				int idx = c.getEPackage().getEClassifiers().indexOf(c);
				c.getEPackage().getEClassifiers().add(idx + 1, callable);
				c.getEPackage().getEClassifiers().add(idx + 1, moduleCallable);
				c.getEPackage().getEClassifiers().add(idx + 1, staticRule);
			} else if ( c.getName().equals("Helper") ) {
				c.setAbstract(true);
				c.getESuperTypes().add(callable);
				
				EClass contextHelper = EcoreFactory.eINSTANCE.createEClass();
				contextHelper.setName("ContextHelper");
				contextHelper.getESuperTypes().add(c);
				
				EClass staticHelper = EcoreFactory.eINSTANCE.createEClass();
				staticHelper.setName("StaticHelper");
				staticHelper.getESuperTypes().add(c);				
				staticHelper.getESuperTypes().add(moduleCallable);				

				int idx = c.getEPackage().getEClassifiers().indexOf(c);
				c.getEPackage().getEClassifiers().add(idx + 1, contextHelper);
				c.getEPackage().getEClassifiers().add(idx + 1, staticHelper);
			}
		}
	}
	
	protected <T> List<T> getAllOfType(Resource r, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		TreeIterator<EObject> it = r.getAllContents();
		while (it.hasNext()) {
			EObject o = it.next();
			if (clazz.isInstance(o)) {
				list.add(clazz.cast(o));
			}
		}
		return list;
	}

}
