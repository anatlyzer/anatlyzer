package anatlyzer.atl.api.atlmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalAnalyser;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ATLModel.CopiedATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclModel;

public class TestATLModel extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCopyAll() throws Exception {
		String T = trafo("simple_trafo");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		AnalysisResult result = getAnalysisResult();		
		ATLModel original        = result.getATLModel();
		CopiedATLModel freshCopy = result.getATLModel().copyAll();
		
		assertTrue(original == freshCopy.getOriginal());
		assertTrue(original.getResource() != freshCopy.getResource());
		
		// Check that everything has been copied and all references actually
		// point to objects in the copy and not in the original model.
		original.getResource().getAllContents().forEachRemaining(src -> {
			EObject tgt = freshCopy.getTarget(src);
			assertNotNull(tgt);
			
			HashSet<EObject> tocheck = new HashSet<EObject>();
			tgt.eClass().getEAllReferences().stream().filter(r -> !r.isContainment()).forEach(r -> {
				Object o = tgt.eGet(r);
				if ( o != null ) {
					if ( o instanceof Collection ) {
						tocheck.addAll((Collection<EObject>) o);
					} else {
						tocheck.add((EObject) o);
					}
				}
			});
			
			tocheck.stream().filter(e -> !(e instanceof EModelElement)).forEach(tgt2 -> {
				// It is not in the original resource...
				TreeIterator<EObject> it = original.getResource().getAllContents();
				while ( it.hasNext() ) {
					EObject src2 = it.next();
					assertTrue(tgt2 != src2);	
				}	
				
				// It is in the copied resource
				boolean found = false;
				TreeIterator<EObject> it2 = freshCopy.getResource().getAllContents();
				while ( it2.hasNext() ) {
					EObject tgt3 = it2.next();
					if ( tgt3 == tgt2 ) {
						found = true;
						break;
					}
				}	
				
				assertTrue("Object " + tgt2 + " not found", found);				
			});
		});
		
		
		// Check some specific references
		// It is a regression test that discovered that "copyReferences" must be called
		// explicitly to copy non-containment references
		Module modFresh = (Module) freshCopy.getModule();
		for(OclModel in : original.getModule().getInModels()) {			
			assertTrue(in.eResource() != null);
			assertTrue(in.getMetamodel() != null);
		}
		for(OclModel in : modFresh.getInModels()) {
			assertTrue(in.eResource() != null);
			assertTrue(in.getMetamodel() != null);
		}
		
		// Check problems point to the correct elements
		LocalProblem pSrc = (LocalProblem) original.getErrors().getProblems().get(0);
		LocalProblem pTgt = (LocalProblem) freshCopy.getErrors().getProblems().get(0);
		
		assertTrue(pSrc != pTgt);
		assertTrue(pSrc.getElement() != pTgt.getElement());
		assertTrue(pTgt.getElement().eResource() == freshCopy.getResource());
	
	
		// Check metaclass references
		checkMetaclassReferences(original, freshCopy);
	}


	protected void checkMetaclassReferences(ATLModel original,
			CopiedATLModel freshCopy) {

		original.getResource().getContents().forEach(src -> {
			EObject tgt = freshCopy.getTarget(src);
			
			if ( tgt instanceof Metaclass) { 
				assertNotNull(((Metaclass) src).getKlass());
				assertEquals(((Metaclass) src).getKlass(), ((Metaclass) tgt).getKlass());
			}
		});
		
		// Check that no spurious metaclass references has been created
		freshCopy.getResource().getContents().forEach(tgt -> {			
			if ( tgt instanceof Metaclass) { 
				assertNotNull(((Metaclass) tgt).getKlass());
			}
		});
	}

	@Test
	public void testIncrementalChangingMetamodel() throws Exception {
		String T = trafo("simple_trafo");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		AnalysisResult result = getAnalysisResult();		
		MetamodelNamespace nsSrc = result.getNamespace().getNamespace("ABCD");
		
		
		// IncrementalAnalyser inc = new IncrementalAnalyser(result, Collections.singletonList("ABCD"));
		IncrementalAnalyser inc = new IncrementalAnalyser(result, result.getNamespace().getLogicalNamesToMetamodels().keySet());
		MetamodelNamespace nsTgt = inc.getNamespaces().getNamespace("ABCD");
		
		assertTrue(nsSrc != nsTgt);	
	}
}
