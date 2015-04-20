package anatlyzer.atl.editor.quickfix.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class ATLUtils2 {

	public static Set<String> getAllMetamodelNames(ATLModel model) {
		return ATLUtils.getModelInfo(model).stream().map(ModelInfo::getMetamodelName).collect(Collectors.toSet());
	}
	
	/**
	 * Returns the Collection of available variable declarations in the (increasing) scope of c
	 * @param c
	 * @return the list of accesible variable declarations, ordered from closer scope
	 */
	public static List<VariableDeclaration> getAvailableDeclarations (OperationCallExp c) {
		Set<VariableDeclaration> vdeclas = new LinkedHashSet<VariableDeclaration>();		// To avoid duplicates
		
		EObject current = c;

		while (current != null) {		// TODO: check more cases
			
			if (current instanceof IteratorExp) {
				vdeclas.addAll(((IteratorExp) current).getIterators());
			} 
			else if (current instanceof Rule) {
				Rule r = (Rule)current;
				vdeclas.addAll(r.getVariables());
				vdeclas.addAll(r.getOutPattern().getElements());
				if (current instanceof RuleWithPattern) {
					vdeclas.addAll(((RuleWithPattern)r).getInPattern().getElements());
				}
			}
			else if (current instanceof OutPattern) {
				vdeclas.addAll(((OutPattern)current).getElements());
			}
			current = current.eContainer();
		}
		
		return new ArrayList<VariableDeclaration>(vdeclas);
	}
}
