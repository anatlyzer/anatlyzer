package anatlyzer.atl.editor.quickfix.util;

import java.util.Set;
import java.util.stream.Collectors;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;

public class ATLUtils2 {

	public static Set<String> getAllMetamodelNames(ATLModel model) {
		return ATLUtils.getModelInfo(model).stream().map(ModelInfo::getMetamodelName).collect(Collectors.toSet());
	}
	
}
