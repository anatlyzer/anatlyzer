/**
 */
package anatlyzer.atlext.ATL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Rule Resolution Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleResolutionStatus()
 * @model
 * @generated
 */
public enum RuleResolutionStatus implements Enumerator {
	/**
	 * The '<em><b>RESOLUTION UNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	RESOLUTION_UNKNOWN(0, "RESOLUTION_UNKNOWN", "RESOLUTION_UNKNOWN"),

	/**
	 * The '<em><b>RESOLUTION CONFIRMED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_CONFIRMED_VALUE
	 * @generated
	 * @ordered
	 */
	RESOLUTION_CONFIRMED(1, "RESOLUTION_CONFIRMED", "RESOLUTION_CONFIRMED"),

	/**
	 * The '<em><b>RESOLUTION DISCARDED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_DISCARDED_VALUE
	 * @generated
	 * @ordered
	 */
	RESOLUTION_DISCARDED(2, "RESOLUTION_DISCARDED", "RESOLUTION_DISCARDED");

	/**
	 * The '<em><b>RESOLUTION UNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESOLUTION UNKNOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_UNKNOWN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESOLUTION_UNKNOWN_VALUE = 0;

	/**
	 * The '<em><b>RESOLUTION CONFIRMED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESOLUTION CONFIRMED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_CONFIRMED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESOLUTION_CONFIRMED_VALUE = 1;

	/**
	 * The '<em><b>RESOLUTION DISCARDED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESOLUTION DISCARDED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESOLUTION_DISCARDED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESOLUTION_DISCARDED_VALUE = 2;

	/**
	 * An array of all the '<em><b>Rule Resolution Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RuleResolutionStatus[] VALUES_ARRAY =
		new RuleResolutionStatus[] {
			RESOLUTION_UNKNOWN,
			RESOLUTION_CONFIRMED,
			RESOLUTION_DISCARDED,
		};

	/**
	 * A public read-only list of all the '<em><b>Rule Resolution Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RuleResolutionStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Rule Resolution Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RuleResolutionStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RuleResolutionStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Rule Resolution Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RuleResolutionStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RuleResolutionStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Rule Resolution Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RuleResolutionStatus get(int value) {
		switch (value) {
			case RESOLUTION_UNKNOWN_VALUE: return RESOLUTION_UNKNOWN;
			case RESOLUTION_CONFIRMED_VALUE: return RESOLUTION_CONFIRMED;
			case RESOLUTION_DISCARDED_VALUE: return RESOLUTION_DISCARDED;
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
	private RuleResolutionStatus(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	
} //RuleResolutionStatus
