/**
 */
package anatlyzer.atl.errors.atl_error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>No Binding For Compulsory Feature Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoBindingForCompulsoryFeatureKind()
 * @model
 * @generated
 */
public enum NoBindingForCompulsoryFeatureKind implements Enumerator {
	/**
	 * The '<em><b>IN NORMAL RULE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_NORMAL_RULE_VALUE
	 * @generated
	 * @ordered
	 */
	IN_NORMAL_RULE(0, "IN_NORMAL_RULE", "IN_NORMAL_RULE"),

	/**
	 * The '<em><b>MISSING SUBRULE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MISSING_SUBRULE_VALUE
	 * @generated
	 * @ordered
	 */
	MISSING_SUBRULE(1, "MISSING_SUBRULE", "MISSING_SUBRULE");

	/**
	 * The '<em><b>IN NORMAL RULE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IN NORMAL RULE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_NORMAL_RULE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IN_NORMAL_RULE_VALUE = 0;

	/**
	 * The '<em><b>MISSING SUBRULE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MISSING SUBRULE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MISSING_SUBRULE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MISSING_SUBRULE_VALUE = 1;

	/**
	 * An array of all the '<em><b>No Binding For Compulsory Feature Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NoBindingForCompulsoryFeatureKind[] VALUES_ARRAY =
		new NoBindingForCompulsoryFeatureKind[] {
			IN_NORMAL_RULE,
			MISSING_SUBRULE,
		};

	/**
	 * A public read-only list of all the '<em><b>No Binding For Compulsory Feature Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NoBindingForCompulsoryFeatureKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>No Binding For Compulsory Feature Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NoBindingForCompulsoryFeatureKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NoBindingForCompulsoryFeatureKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>No Binding For Compulsory Feature Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NoBindingForCompulsoryFeatureKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NoBindingForCompulsoryFeatureKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>No Binding For Compulsory Feature Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NoBindingForCompulsoryFeatureKind get(int value) {
		switch (value) {
			case IN_NORMAL_RULE_VALUE: return IN_NORMAL_RULE;
			case MISSING_SUBRULE_VALUE: return MISSING_SUBRULE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private NoBindingForCompulsoryFeatureKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //NoBindingForCompulsoryFeatureKind
