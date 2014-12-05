package witness.visualizer.eclectic.idc.datatypes;

import java.util.*;

import witness.visualizer.eclectic.modeling.emf.ICollectionConverter;


public class JavaListConverter implements ICollectionConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertList(Object list) {
		return list;
		//return ImmutableList.convertList((java.util.List<Object>) list);
	}

	@Override
	public boolean isList(Object list) {
		return list instanceof List;
		//return list instanceof ImmutableList;
	}

	@Override
	public Iterator<Object> toIterator(Object list) {
		return ((List<Object>) list).iterator();
	}

	@Override
	public Object convertToStreamingList(Object list) {
		return list;
		//return ImmutableList.convertList((java.util.List<Object>) list);
	}

}
