/**
 */
package anatlyzer.atl.errors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Problem Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblemStatus()
 * @model
 * @generated
 */
public enum ProblemStatus implements Enumerator {
	/**
	 * The '<em><b>STATICALLY CONFIRMED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATICALLY_CONFIRMED_VALUE
	 * @generated
	 * @ordered
	 */
	STATICALLY_CONFIRMED(0, "STATICALLY_CONFIRMED", "STATICALLY_CONFIRMED"),

	/**
	 * The '<em><b>INITIALLY DISCARDED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIALLY_DISCARDED_VALUE
	 * @generated
	 * @ordered
	 */
	INITIALLY_DISCARDED(1, "INITIALLY_DISCARDED", "INITIALLY_DISCARDED"), /**
	 * The '<em><b>WITNESS REQUIRED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WITNESS_REQUIRED_VALUE
	 * @generated
	 * @ordered
	 */
	WITNESS_REQUIRED(2, "WITNESS_REQUIRED", "WITNESS_REQUIRED"),

	/**
	 * The '<em><b>ERROR CONFIRMED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_CONFIRMED_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR_CONFIRMED(3, "ERROR_CONFIRMED", "ERROR_CONFIRMED"),

	/**
	 * The '<em><b>ERROR CONFIRMED SPECULATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_CONFIRMED_SPECULATIVE_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR_CONFIRMED_SPECULATIVE(4, "ERROR_CONFIRMED_SPECULATIVE", "ERROR_CONFIRMED_SPECULATIVE"),

	/**
	 * The '<em><b>ERROR DISCARDED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_DISCARDED_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR_DISCARDED(5, "ERROR_DISCARDED", "ERROR_DISCARDED"),

	/**
	 * The '<em><b>ERROR DISCARDED DUE TO METAMODEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_DISCARDED_DUE_TO_METAMODEL_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR_DISCARDED_DUE_TO_METAMODEL(6, "ERROR_DISCARDED_DUE_TO_METAMODEL", "ERROR_DISCARDED_DUE_TO_METAMODEL"),

	/**
	 * The '<em><b>USE TIME OUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USE_TIME_OUT_VALUE
	 * @generated
	 * @ordered
	 */
	USE_TIME_OUT(7, "USE_TIME_OUT", "USE_TIME_OUT"), /**
	 * The '<em><b>USE INTERNAL ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USE_INTERNAL_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	USE_INTERNAL_ERROR(8, "USE_INTERNAL_ERROR", "USE_INTERNAL_ERROR"),

	/**
	 * The '<em><b>IMPL INTERNAL ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPL_INTERNAL_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	IMPL_INTERNAL_ERROR(9, "IMPL_INTERNAL_ERROR", "IMPL_INTERNAL_ERROR"), /**
	 * The '<em><b>CANNOT DETERMINE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CANNOT_DETERMINE_VALUE
	 * @generated
	 * @ordered
	 */
	CANNOT_DETERMINE(10, "CANNOT_DETERMINE", "CANNOT_DETERMINE"), /**
	 * The '<em><b>NOT SUPPORTED BY USE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED_BY_USE_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_SUPPORTED_BY_USE(11, "NOT_SUPPORTED_BY_USE", "NOT_SUPPORTED_BY_USE"),

	/**
	 * The '<em><b>PROBLEMS IN PATH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROBLEMS_IN_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	PROBLEMS_IN_PATH(12, "PROBLEMS_IN_PATH", "PROBLEMS_IN_PATH");

	/**
	 * The '<em><b>STATICALLY CONFIRMED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STATICALLY CONFIRMED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATICALLY_CONFIRMED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATICALLY_CONFIRMED_VALUE = 0;

	/**
	 * The '<em><b>INITIALLY DISCARDED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INITIALLY DISCARDED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INITIALLY_DISCARDED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INITIALLY_DISCARDED_VALUE = 1;

	/**
	 * The '<em><b>WITNESS REQUIRED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WITNESS REQUIRED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WITNESS_REQUIRED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WITNESS_REQUIRED_VALUE = 2;

	/**
	 * The '<em><b>ERROR CONFIRMED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR CONFIRMED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_CONFIRMED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_CONFIRMED_VALUE = 3;

	/**
	 * The '<em><b>ERROR CONFIRMED SPECULATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR CONFIRMED SPECULATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_CONFIRMED_SPECULATIVE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_CONFIRMED_SPECULATIVE_VALUE = 4;

	/**
	 * The '<em><b>ERROR DISCARDED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR DISCARDED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_DISCARDED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_DISCARDED_VALUE = 5;

	/**
	 * The '<em><b>ERROR DISCARDED DUE TO METAMODEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR DISCARDED DUE TO METAMODEL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_DISCARDED_DUE_TO_METAMODEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_DISCARDED_DUE_TO_METAMODEL_VALUE = 6;

	/**
	 * The '<em><b>USE TIME OUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USE TIME OUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USE_TIME_OUT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USE_TIME_OUT_VALUE = 7;

	/**
	 * The '<em><b>USE INTERNAL ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USE INTERNAL ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USE_INTERNAL_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USE_INTERNAL_ERROR_VALUE = 8;

	/**
	 * The '<em><b>IMPL INTERNAL ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IMPL INTERNAL ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IMPL_INTERNAL_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IMPL_INTERNAL_ERROR_VALUE = 9;

	/**
	 * The '<em><b>CANNOT DETERMINE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CANNOT DETERMINE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CANNOT_DETERMINE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CANNOT_DETERMINE_VALUE = 10;

	/**
	 * The '<em><b>NOT SUPPORTED BY USE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT SUPPORTED BY USE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED_BY_USE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_SUPPORTED_BY_USE_VALUE = 11;

	/**
	 * The '<em><b>PROBLEMS IN PATH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROBLEMS IN PATH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROBLEMS_IN_PATH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROBLEMS_IN_PATH_VALUE = 12;

	/**
	 * An array of all the '<em><b>Problem Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ProblemStatus[] VALUES_ARRAY =
		new ProblemStatus[] {
			STATICALLY_CONFIRMED,
			INITIALLY_DISCARDED,
			WITNESS_REQUIRED,
			ERROR_CONFIRMED,
			ERROR_CONFIRMED_SPECULATIVE,
			ERROR_DISCARDED,
			ERROR_DISCARDED_DUE_TO_METAMODEL,
			USE_TIME_OUT,
			USE_INTERNAL_ERROR,
			IMPL_INTERNAL_ERROR,
			CANNOT_DETERMINE,
			NOT_SUPPORTED_BY_USE,
			PROBLEMS_IN_PATH,
		};

	/**
	 * A public read-only list of all the '<em><b>Problem Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ProblemStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Problem Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ProblemStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ProblemStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Problem Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ProblemStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ProblemStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Problem Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ProblemStatus get(int value) {
		switch (value) {
			case STATICALLY_CONFIRMED_VALUE: return STATICALLY_CONFIRMED;
			case INITIALLY_DISCARDED_VALUE: return INITIALLY_DISCARDED;
			case WITNESS_REQUIRED_VALUE: return WITNESS_REQUIRED;
			case ERROR_CONFIRMED_VALUE: return ERROR_CONFIRMED;
			case ERROR_CONFIRMED_SPECULATIVE_VALUE: return ERROR_CONFIRMED_SPECULATIVE;
			case ERROR_DISCARDED_VALUE: return ERROR_DISCARDED;
			case ERROR_DISCARDED_DUE_TO_METAMODEL_VALUE: return ERROR_DISCARDED_DUE_TO_METAMODEL;
			case USE_TIME_OUT_VALUE: return USE_TIME_OUT;
			case USE_INTERNAL_ERROR_VALUE: return USE_INTERNAL_ERROR;
			case IMPL_INTERNAL_ERROR_VALUE: return IMPL_INTERNAL_ERROR;
			case CANNOT_DETERMINE_VALUE: return CANNOT_DETERMINE;
			case NOT_SUPPORTED_BY_USE_VALUE: return NOT_SUPPORTED_BY_USE;
			case PROBLEMS_IN_PATH_VALUE: return PROBLEMS_IN_PATH;
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
	private ProblemStatus(int value, String name, String literal) {
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
	
} //ProblemStatus
