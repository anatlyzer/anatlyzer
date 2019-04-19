package anatlyzer.atl.witness;

import java.util.Iterator;

public interface IScrollingIterator extends Iterator<IWitnessModel> {

	void setMetamodelRewritingData(IMetamodelRewrite data);
	
}
