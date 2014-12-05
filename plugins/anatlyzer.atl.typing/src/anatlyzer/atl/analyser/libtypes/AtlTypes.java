package anatlyzer.atl.analyser.libtypes;

public class AtlTypes {
	private static final StringTypeDef string_instance = new StringTypeDef();
	private static final FloatTypeDef float_instance = new FloatTypeDef();
	private static IntegerTypeDef integer_instance = new IntegerTypeDef();
	private static BooleanTypeDef boolean_instance = new BooleanTypeDef();
	
	static {
		string_instance.init();
		float_instance.init();
		integer_instance.init();
		boolean_instance.init();
	}
	
	public static StringTypeDef string() { return string_instance; }
	public static IntegerTypeDef integer() { return integer_instance; }
	public static FloatTypeDef float_() { return float_instance; }
	public static BooleanTypeDef boolean_() { return boolean_instance; }
	
}
