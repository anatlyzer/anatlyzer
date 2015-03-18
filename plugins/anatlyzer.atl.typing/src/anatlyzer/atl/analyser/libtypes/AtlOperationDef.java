package anatlyzer.atl.analyser.libtypes;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.types.Type;


public class AtlOperationDef {
	private String name;
	private AtlTypeDef returnType;
	private List<AtlTypeDef> paramTypes = new ArrayList<AtlTypeDef>();
	private List<String> paramNames = new ArrayList<String>();

	public AtlOperationDef(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
	public AtlOperationDef param(String name, AtlTypeDef type) {
		this.paramTypes.add(type);
		this.paramNames.add(name);
		return this;
	}
	
	public AtlOperationDef returnType(AtlTypeDef type) {
		this.returnType = type;
		return this;
	}

	public AtlTypeDef getReturnType() {
		return returnType;
	}

	public Type[] getParameterTypes() {
		Type[] result = new Type[paramTypes.size()];
		for(int i = 0; i < paramTypes.size(); i++) {
			result[i] = paramTypes.get(i).getAnalyserType();
		}
		return result;
	}
	
	public String[] getParameterNames() {
		String[] result = new String[paramNames.size()];
		for(int i = 0; i < paramNames.size(); i++) {
			result[i] = paramNames.get(i);
		}
		return result;
	}
}

