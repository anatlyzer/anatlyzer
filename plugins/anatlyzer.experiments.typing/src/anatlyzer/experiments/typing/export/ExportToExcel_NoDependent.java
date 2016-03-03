package anatlyzer.experiments.typing.export;

import anatlyzer.experiments.typing.raw.ITEFilter;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TEProject;
import anatlyzer.experiments.typing.raw.TETransformation;

public class ExportToExcel_NoDependent extends ExportToExcel {

	public ExportToExcel_NoDependent(TEData data) {
		super(deriveData(data));
	}
	
	public static TEData deriveData(TEData data) {
		return data.filter(new ITEFilter() {			
			@Override
			public boolean include(TEProblem p) {
				return ! p.isDependent();
			}
			
			@Override
			public boolean include(TETransformation t) {
				return true;
			}
			
			@Override
			public boolean include(TEProject p) {
				return true;
			}
		});
	}
	
	
}
