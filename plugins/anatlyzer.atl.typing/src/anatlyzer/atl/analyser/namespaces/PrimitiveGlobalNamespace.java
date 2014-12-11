package anatlyzer.atl.analyser.namespaces;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Type;

public class PrimitiveGlobalNamespace implements IMetamodelNamespace {

	public static final String	SEQUENCE_TYPE	= "Sequence";
	public static final String	SET_TYPE	= "Set";
	public static final String	STRING_TYPE	= "String";
	public static final String	INTEGER_TYPE	= "Integer";
	public static final String	FLOAT_TYPE	= "Float";
	public static final String	BOOLEAN_TYPE	= "Boolean";

	protected StringNamespace  stringNamespace;
	protected IntegerNamespace integerNamespace;
	protected BooleanNamespace booleanNamespace;
	protected FloatNamespace   floatNamespace;

	protected TypingModel	typ;
	
	public PrimitiveGlobalNamespace(TypingModel typ) {
		this.typ = typ;
		this.stringNamespace = new StringNamespace();
		this.integerNamespace = new IntegerNamespace();
		this.booleanNamespace = new BooleanNamespace();
		this.floatNamespace   = new FloatNamespace();
	}

	@Override
	public ITypeNamespace getClassifier(String name) {
		if ( name.equals(STRING_TYPE) ) {
			return stringNamespace;
		} else if ( name.equals(INTEGER_TYPE) ) {
			return integerNamespace;
		} else if ( name.equals(BOOLEAN_TYPE) ) {
			return booleanNamespace;
		} else if ( name.equals(FLOAT_TYPE)) {
			return floatNamespace;
		}
		throw new UnsupportedOperationException(name);
	}

	public Object getClassifier(String name, Type nested) {
		if ( name.equals(SEQUENCE_TYPE) ) {
			return new SequenceNamespace(typ, nested);
		} else if ( name.equals(SET_TYPE) ) {
			return new SetNamespace(typ, nested);
		}
		return null;
	}

	@Override
	public IClassNamespace getClass(EClass c) {
		throw new UnsupportedOperationException();
	}

}
