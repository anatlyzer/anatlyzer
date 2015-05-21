package anatlyzer.atl.analyser.generators;

import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.LocatedElement;

public class PathIdStringVisitor extends ATLSerializer {

	private PathId pathId;

	public PathIdStringVisitor(PathId pathId) {
		this.pathId = pathId;
	}

	public static String serialize(LocatedElement obj, PathId pathId) {		
		PathIdStringVisitor s = new PathIdStringVisitor(pathId);
		s.startVisiting(obj);
		return s.g(obj);
	}
	
	
	

}
