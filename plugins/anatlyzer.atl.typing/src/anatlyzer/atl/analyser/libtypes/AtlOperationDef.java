package anatlyzer.atl.analyser.libtypes;

public class AtlOperationDef {
	private String name;
	private AtlTypeDef returnType;

	public AtlOperationDef(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void returnType(AtlTypeDef type) {
		this.returnType = type;
	}

	public AtlTypeDef getReturnType() {
		return returnType;
	}
}
