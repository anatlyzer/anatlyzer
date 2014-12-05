package witness.visualizer.eclectic.modeling.emf;

import java.util.Iterator;

public interface ICollectionConverter {
	public Object convertList(Object list);
	
	/**
	 * @param list A list of the internal type
	 * @return Returns true if the object is a list
	 */
	public boolean isList(Object list);
	
	public Iterator<Object> toIterator(Object list);

	// Streaming
	public Object convertToStreamingList(Object list);
}
