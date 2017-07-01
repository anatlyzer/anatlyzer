package anatlyzer.example.checkconstraints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import anatlyzer.example.sconstraints.ConstraintModel;
import anatlyzer.example.sconstraints.NumClassesConstraint;
import anatlyzer.example.sconstraints.NumRefsConstraint;
import anatlyzer.example.sconstraints.SimpleConstraintsFactory;

/**
 * This class parses a language to specify constraints like the following:
 * <pre>
 *      # Constraints for UML packages
 *	    class Package > 1
 *   	ref   Package.packagedElement > 2
 * </pre>
 * 
 * @author jesus
 *
 */
public class SimpleConstraintsParser {

	public ConstraintModel parse(String text) throws ParseError {
		try (BufferedReader br = new BufferedReader(new StringReader(text))) {
		    
			ConstraintModel model = SimpleConstraintsFactory.eINSTANCE.createConstraintModel();
			String line;
		    while ((line = br.readLine()) != null) {
		    	line = line.trim();
		    	
		    	if ( line.startsWith("#") )
		    		continue;
		    	
		    	String[] elems = line.split("\\s+");
		    	switch( elems[0] ) {
		    	case "class":
		    		String className = elems[1];
		    		int num = Integer.parseInt(elems[3]);
		    		
					NumClassesConstraint constraint = SimpleConstraintsFactory.eINSTANCE.createNumClassesConstraint();
		    		constraint.setClassName(className);
		    		constraint.setNumber(num);
		    		
		    		model.getConstraints().add(constraint);
		    		
		    		break;
		    	case "ref":
		    		String refClassName = elems[1].split("\\.")[0];
		    		String refName      = elems[1].split("\\.")[1];		    		
		    		int refNum = Integer.parseInt(elems[3]);
		    		
					NumRefsConstraint refConstraint = SimpleConstraintsFactory.eINSTANCE.createNumRefsConstraint();
					refConstraint.setClassName(refClassName);
					refConstraint.setRefName(refName);
					refConstraint.setNumber(refNum);
		    		
		    		model.getConstraints().add(refConstraint);

		    		break;
		    	default:
		    		throw new ParseError("Error at: " + line);
		    	}
		    }
		    
		    return model;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static class ParseError extends Exception {
		private static final long serialVersionUID = -5162556827195591253L;

		public ParseError(String msg) {
			super(msg);
		}
	}
	
}
