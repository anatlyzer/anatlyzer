package witness.generator;

import java.util.Calendar;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

import transML.exceptions.transException;

// Just for the moment
public class WitnessGeneratorMemory extends WitnessGenerator {
	protected EPackage errorMM;
	protected MetaModel effectiveMM;
	protected MetaModel languageMM;
	protected String oclConstraint;
	private String projectPath;
	
	public WitnessGeneratorMemory(EPackage errorSliceMetamodel,  EPackage effectiveMetamodel, EPackage languageMetamodel, String oclConstraint) {
		this.effectiveMM = new MetaModel(effectiveMetamodel);
		this.errorMM = errorSliceMetamodel;

		this.languageMM = new MetaModel(languageMetamodel);
		this.oclConstraint = oclConstraint;
	}
	
	public boolean generate() throws transException {		
		// extend error meta-model with mandatory classes in effective meta-model 
		extendMetamodelWithMandatory(errorMM, effectiveMM, languageMM);
	
		// extend error meta-model with concrete children classes of abstract leaf classes
		extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);
		extendMetamodelWithConcreteLeaves(errorMM, languageMM);
	
		// [KM3 meta-models] add instance type name to user-defined data types
		extendMetamodelWithInstanceTypeNames4DataTypes(errorMM);
	
		// copy uri/name/prefix from the language meta-model to the extended error meta-model
		errorMM.setName    (languageMM.getName());
		errorMM.setNsPrefix(languageMM.getNsPrefix());
		errorMM.setNsURI   (languageMM.getNsURI()!=null?languageMM.getNsURI():effectiveMM.getNsURI());

		addBaseObject(errorMM);
		changeNamesToResolveConflicts(errorMM, new USENameModifyier());
		
		loadOclOperations(errorMM);
		
		Calendar c = Calendar.getInstance();
		int time = 1 * c.get(Calendar.SECOND) + 
				   100 * c.get(Calendar.MINUTE) + 
				   10000 * c.get(Calendar.HOUR_OF_DAY) + 
				   1000000 * (c.get(Calendar.DAY_OF_MONTH) + 1)+ 
				   100000000 * (c.get(Calendar.MONTH) + 1); // +
				   // 10000000000 * c.get(Calendar.YEAR);
		
		String witness = generateWitness(getTempDirectoryPath(), errorMM, oclConstraint, time);
		return witness != null;
	}
	
	/**
	 * Needed to have a common type different from OclAny which is not accepted by the validator...
	 * @param errorMM2
	 */
	private void addBaseObject(EPackage errorMM2) {
		EClass eclass = EcoreFactory.eINSTANCE.createEClass();
		eclass.setName("BaseObject_");
		eclass.setAbstract(true);
		errorMM2.getEClassifiers().add(eclass);
		for(EClassifier c : errorMM2.getEClassifiers()) {
			if ( c instanceof EClass && c != eclass ) {
				if ( ((EClass) c).getESuperTypes().size() == 0 ) {
					((EClass) c).getESuperTypes().add(eclass);
				}
			}
		}
	}

	private void changeNamesToResolveConflicts(EPackage errorMM2, USENameModifyier mod) {
		for(EClassifier c : errorMM2.getEClassifiers()) {
			if ( c instanceof EClass ) {
				mod.adapt((EClass) c, true);
			}
		}
	}

	private void changeConflictingClassNames(EPackage errorMM2) {
		// TODO Auto-generated method stub
		
	}

	public String getTempDirectoryPath() {
		return projectPath == null ? "." : projectPath;
	}

	public void setTempDirectoryPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
}

