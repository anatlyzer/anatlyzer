package anatlyzer.ui.preferences;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.util.ProblemSets.IProblemSelector;

public enum DefaultAnalysisConfiguration {
	ALL_CONTINOUS {
		@Override
		public IProblemSelector getSelector() {
			return new IProblemSelector() {
				@Override
				public boolean isIgnoredByDefault(EClass eClass) {
					return false;
				}
				
				@Override
				public boolean isBatchByDefault(EClass eClass) {
					return false;
				}
			};
		}
	},
	MODEL_FINDING_ON_ERRORS {
		@Override
		public IProblemSelector getSelector() {
			return new IProblemSelector() {
				@Override
				public boolean isIgnoredByDefault(EClass eClass) {
					return false;
				}
				
				@Override
				public boolean isBatchByDefault(EClass c) {
					return 	c == AtlErrorPackage.Literals.BINDING_POSSIBLY_UNRESOLVED ||
							c == AtlErrorPackage.Literals.RESOLVE_TEMP_POSSIBLY_UNRESOLVED ||
							c == AtlErrorPackage.Literals.FEATURE_FOUND_IN_SUBTYPE ||
							c == AtlErrorPackage.Literals.OPERATION_FOUND_IN_SUBTYPE;				
				}
			};
		}		
	},
	NO_MODEL_FINDING {
		@Override
		public IProblemSelector getSelector() {
			return new IProblemSelector() {
				@Override
				public boolean isIgnoredByDefault(EClass eClass) {
					return false;
				}
				
				@Override
				public boolean isBatchByDefault(EClass c) {
					return 	c == AtlErrorPackage.Literals.BINDING_POSSIBLY_UNRESOLVED ||
							c == AtlErrorPackage.Literals.RESOLVE_TEMP_POSSIBLY_UNRESOLVED ||
							c == AtlErrorPackage.Literals.FEATURE_FOUND_IN_SUBTYPE ||
							c == AtlErrorPackage.Literals.OPERATION_FOUND_IN_SUBTYPE ||
							c == AtlErrorPackage.Literals.ACCESS_TO_UNDEFINED_VALUE ||
							c == AtlErrorPackage.Literals.ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION;				
				}
			};
		}				
	};

	public abstract IProblemSelector getSelector();
}
