package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Facilitates the serialization of exception getting the most important information 
 * 
 * @author jesus
 *
 */
@Element(name="exception")
public class TEException {

	@Attribute
	protected String name;
	
	@Element(required=false)
	private String message;
	
	@ElementList
	private List<String> stackTrace;
	
	@Element(required=false)
	private TEException cause;
	
	public TEException() {
		stackTrace = new ArrayList<String>();		
	}
	
	public TEException(Throwable e, boolean recordCause) {
		this();
		this.name = e.getClass().getName();
		this.message = e.getMessage();
		
		for (StackTraceElement element : e.getStackTrace()) {
			stackTrace.add( element.toString() );
		}
		
		if ( e.getCause() != null ) {
			this.cause = new TEException(e.getCause(), false);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public List<String> getStackTrace() {
		return Collections.unmodifiableList(stackTrace);
	}
	
	public TEException getCause() {
		return cause;
	}

}
