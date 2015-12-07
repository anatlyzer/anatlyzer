package anatlyzer.atl.analyser.libtypes;

public class AtlTypes {
	private static final StringTypeDef string_instance = new StringTypeDef();
	private static final FloatTypeDef float_instance = new FloatTypeDef();
	private static IntegerTypeDef integer_instance = new IntegerTypeDef();
	private static BooleanTypeDef boolean_instance = new BooleanTypeDef();
	private static OclTypeDef ocltype_instance = new OclTypeDef();
	private static MapTypeDef maptype_instance = new MapTypeDef();
	private static OclAnyTypeDef oclany_instance = new OclAnyTypeDef();

	private static SequenceTypeDef seq_instance = new SequenceTypeDef(oclany_instance);
	private static SetTypeDef set_instance = new SetTypeDef(oclany_instance);
	
	
	static {
		oclany_instance.init();
		string_instance.init();
		float_instance.init();
		integer_instance.init();
		boolean_instance.init();
		ocltype_instance.init();
		maptype_instance.init();
		
		seq_instance.init();
		set_instance.init();
		
	}
	
	public static StringTypeDef string() { return string_instance; }
	public static IntegerTypeDef integer() { return integer_instance; }
	public static FloatTypeDef float_() { return float_instance; }
	public static BooleanTypeDef boolean_() { return boolean_instance; }
	public static OclTypeDef oclType() { return ocltype_instance; }
	public static MapTypeDef map() { return maptype_instance; }
	public static OclAnyTypeDef oclAny() { return oclany_instance; }
	
	public static SequenceTypeDef seq(AtlTypeDef t) {
		return new SequenceTypeDef(t);
	}
	public static AtlTypeDef set(AtlTypeDef t) {
		return new SetTypeDef(t);
	}
	
	public static SequenceTypeDef seq() {
		return seq_instance;
	}
	public static AtlTypeDef set() {
		return set_instance;
	}
	
	
	
}
