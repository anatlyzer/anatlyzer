/**
 */
package anatlyzer.atl.errors.atl_error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Invalid Rule Inheritance Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidRuleInheritanceKind()
 * @model
 * @generated
 */
public enum InvalidRuleInheritanceKind implements Enumerator {
	/**
	 * The '<em><b>DIFFERENT NUMBER OF IPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIFFERENT_NUMBER_OF_IPE_VALUE
	 * @generated
	 * @ordered
	 */
	DIFFERENT_NUMBER_OF_IPE(0, "DIFFERENT_NUMBER_OF_IPE", "DIFFERENT_NUMBER_OF_IPE"),

	/**
	 * The '<em><b>DIFFERENT IPE NAMES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIFFERENT_IPE_NAMES_VALUE
	 * @generated
	 * @ordered
	 */
	DIFFERENT_IPE_NAMES(1, "DIFFERENT_IPE_NAMES", "DIFFERENT_IPE_NAMES"), /**
	 * The '<em><b>OTHER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OTHER_VALUE
	 * @generated
	 * @ordered
	 */
	OTHER(2, "OTHER", "OTHER");

	/**
	 * The '<em><b>DIFFERENT NUMBER OF IPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIFFERENT NUMBER OF IPE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIFFERENT_NUMBER_OF_IPE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIFFERENT_NUMBER_OF_IPE_VALUE = 0;

	/**
	 * The '<em><b>DIFFERENT IPE NAMES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIFFERENT IPE NAMES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIFFERENT_IPE_NAMES
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIFFERENT_IPE_NAMES_VALUE = 1;

	/**
	 * The '<em><b>OTHER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OTHER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OTHER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OTHER_VALUE = 2;

	/**
	 * An array of all the '<em><b>Invalid Rule Inheritance Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final InvalidRuleInheritanceKind[] VALUES_ARRAY =
		new InvalidRuleInheritanceKind[] {
			DIFFERENT_NUMBER_OF_IPE,
			DIFFERENT_IPE_NAMES,
			OTHER,
		};

	/**
	 * A public read-only list of all the '<em><b>Invalid Rule Inheritance Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<InvalidRuleInheritanceKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Invalid Rule Inheritance Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InvalidRuleInheritanceKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InvalidRuleInheritanceKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Invalid Rule Inheritance Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InvalidRuleInheritanceKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InvalidRuleInheritanceKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Invalid Rule Inheritance Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InvalidRuleInheritanceKind get(int value) {
		switch (value) {
			case DIFFERENT_NUMBER_OF_IPE_VALUE: return DIFFERENT_NUMBER_OF_IPE;
			case DIFFERENT_IPE_NAMES_VALUE: return DIFFERENT_IPE_NAMES;
			case OTHER_VALUE: return OTHER;
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
	private InvalidRuleInheritanceKind(int value, String name, String literal) {
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
	
} //InvalidRuleInheritanceKind
