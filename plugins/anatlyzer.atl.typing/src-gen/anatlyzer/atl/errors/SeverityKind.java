/**
 */
package anatlyzer.atl.errors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Severity Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.AnalysisResultPackage#getSeverityKind()
 * @model
 * @generated
 */
public enum SeverityKind implements Enumerator {
	/**
	 * The '<em><b>ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR(0, "ERROR", "ERROR"),

	/**
	 * The '<em><b>WARNING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WARNING_VALUE
	 * @generated
	 * @ordered
	 */
	WARNING(1, "WARNING", "WARNING"),

	/**
	 * The '<em><b>STYLE SUGGESTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STYLE_SUGGESTION_VALUE
	 * @generated
	 * @ordered
	 */
	STYLE_SUGGESTION(3, "STYLE_SUGGESTION", "STYLE_SUGGESTION"),

	/**
	 * The '<em><b>PERFORMANCE SUGGESTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PERFORMANCE_SUGGESTION_VALUE
	 * @generated
	 * @ordered
	 */
	PERFORMANCE_SUGGESTION(4, "PERFORMANCE_SUGGESTION", "PERFORMANCE_SUGGESTION");

	/**
	 * The '<em><b>ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_VALUE = 0;

	/**
	 * The '<em><b>WARNING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WARNING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WARNING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WARNING_VALUE = 1;

	/**
	 * The '<em><b>STYLE SUGGESTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STYLE SUGGESTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STYLE_SUGGESTION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STYLE_SUGGESTION_VALUE = 3;

	/**
	 * The '<em><b>PERFORMANCE SUGGESTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERFORMANCE SUGGESTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PERFORMANCE_SUGGESTION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PERFORMANCE_SUGGESTION_VALUE = 4;

	/**
	 * An array of all the '<em><b>Severity Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SeverityKind[] VALUES_ARRAY =
		new SeverityKind[] {
			ERROR,
			WARNING,
			STYLE_SUGGESTION,
			PERFORMANCE_SUGGESTION,
		};

	/**
	 * A public read-only list of all the '<em><b>Severity Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SeverityKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Severity Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeverityKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeverityKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityKind get(int value) {
		switch (value) {
			case ERROR_VALUE: return ERROR;
			case WARNING_VALUE: return WARNING;
			case STYLE_SUGGESTION_VALUE: return STYLE_SUGGESTION;
			case PERFORMANCE_SUGGESTION_VALUE: return PERFORMANCE_SUGGESTION;
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
	private SeverityKind(int value, String name, String literal) {
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
	
} //SeverityKind
