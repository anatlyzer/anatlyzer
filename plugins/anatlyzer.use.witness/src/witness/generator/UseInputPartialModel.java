package witness.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.main.Session;

import com.google.common.base.Preconditions;

import anatlyzer.atl.witness.IInputPartialModel;
import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.SourceMetamodelsData;

public class UseInputPartialModel {

	private IInputPartialModel partialModel;
	private IMetamodelRewrite rewrite;

	public UseInputPartialModel(IInputPartialModel partialModel, IMetamodelRewrite rewrite) {
		this.partialModel = partialModel;
		this.rewrite = rewrite;
	}

	public void init(Session fSession) {
		UseSystemApi sysApi = UseSystemApi.create(fSession);
		
		TreeIterator<EObject> it = partialModel.getResource().getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			convert(obj, sysApi);
		}

		it = partialModel.getResource().getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			resolveReferences(obj, sysApi);
		}

		// TODO: Not sure if the system should be returned or not
	}

	private int objCounter = 0;
	private Map<EObject, String> ecoreToUseId = new HashMap<>();
	
	private void convert(EObject obj, UseSystemApi sysApi) {
		EClass c = obj.eClass();
		EClass tgt = (EClass) rewrite.getTarget(c, (o1, o2) -> IMetamodelRewrite.nominalCheck(o1, o2));
		Preconditions.checkState(tgt != null);
		
		String id = tgt.getName() + "_" + objCounter++;
		ecoreToUseId.put(obj, id);
		
		try {
			sysApi.createObject(tgt.getName(), id);
			for (EAttribute att : c.getEAllAttributes()) {
				if ( ! obj.eIsSet(att) )
					continue;
				
				sysApi.setAttributeValue(id, att.getName(), getValue(obj, att));
			}				
		} catch (UseApiException e) {
			throw new RuntimeException(e);
		}		
	}
	
	private void resolveReferences(EObject obj, UseSystemApi sysApi) {
		EClass c = obj.eClass();
		EClass tgt = (EClass) rewrite.getTarget(c, (o1, o2) -> IMetamodelRewrite.nominalCheck(o1, o2));
		Preconditions.checkState(tgt != null);

		//try {
			for (EReference ref : c.getEAllReferences()) {
				if ( ! obj.eIsSet(ref) )
					continue;
				
				EReference tgtRef = (EReference) rewrite.getTarget(ref, (o1, o2) -> IMetamodelRewrite.nominalCheck(o1, o2));
				String assocName = Solver_use_Transition.computeAssociationName(tgtRef);

				// hack: see failure in RelSchema example
				String alt2 = null;
				if ( tgtRef.getEOpposite() != null ) {
					String src_role = ref.getName(); 
					alt2 = src_role + "_" + ref.getEOpposite().getEContainingClass().getName() + "_" + ref.getEOpposite().getName();
					// alt2 = Solver_use_Transition.computeAssociationName(tgtRef);
				}
				
				Object o = obj.eGet(ref);
				String srcId = ecoreToUseId.get(obj);
				Preconditions.checkNotNull(srcId);
				if ( o instanceof Collection ) {
//					Collection<EObject> col = (Collection<EObject>) o;
//					String[] str = new String[col.size() + 1];
//					int i = 1;
//					str[0] = srcId;
//					for(EObject eobj : col) {
//						String tgtId = ecoreToUseId.get(eobj);
//						Preconditions.checkNotNull(tgtId);
//						str[i] = tgtId;
//						i++;
//					}
//					sysApi.createLink(assocName, str);
							
					for(EObject eobj : (Collection<EObject>) o) {
						String tgtId = ecoreToUseId.get(eobj);
						Preconditions.checkNotNull(tgtId);
//						sysApi.createLink(assocName, srcId, tgtId);		
						try {
							sysApi.createLink(assocName, srcId, tgtId);							
						} catch ( Exception e ) { // UseApiException 
							try {
							sysApi.createLink(alt2, tgtId, srcId);
							} catch ( Exception e2 ) {
								// ignore... 
							}
						}
						
					}
				} else {
					String tgtId = ecoreToUseId.get((EObject) o);
					Preconditions.checkNotNull(tgtId);
					try {
						sysApi.createLink(assocName, srcId, tgtId);							
					} catch ( Exception e ) { // UseApiException 
						try {
						sysApi.createLink(alt2, tgtId, srcId);
						} catch ( Exception e2 ) {
							// ignore... 
						}
					}
				}
			}
			
//		} catch (UseApiException e) {
//			throw new RuntimeException(e);
//		}		

	}

	private String getValue(EObject obj, EAttribute att) {
		Object value = obj.eGet(att);
		if ( value instanceof String ) {
			return "'" + value + "'";
		}
		return value.toString();
	}	
}


//UseSystemApi sysApi = UseSystemApi.create(fSession);
//try {
//	sysApi.createObject("psm_State", "s1");
//	sysApi.setAttributeValue("s1", "name", "'myState-test-test'");
//	system = sysApi.getSystem();
//} catch (UseApiException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
