package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.ATLUtils2;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class OperationNotFoundAbstractQuickFix extends AbstractAtlQuickfix {
	protected static final int threshold = 3;				// threshold distance to try an operation name with +1 or -1 params
	protected Map<Integer, List<String>> candidateOps;		// to be populated by children classes
	private StringDistance sd = new StringDistance(new Levenshtein(), new LongestCommonSubstring());
	
	// force to populate the Map somehow
	protected abstract Map<Integer, List<String>> populateCandidateOps(Predicate<Helper> predicate);
	protected Map<Integer, List<String>> populateCandidateOps () {
		return populateCandidateOps(h -> true);
	}
	
	protected static HashMap<String, List<CollType>> primitiveParam = new HashMap<>();		// probably move this to superclass
	static {
		primitiveParam.put("append", Collections.singletonList(CollType.UserDefined));
		primitiveParam.put("at", Collections.singletonList(CollType.Integer));
		primitiveParam.put("subsequence", Arrays.asList(CollType.Integer, CollType.Integer));
		primitiveParam.put("refGetValue", Collections.singletonList(CollType.String));
		primitiveParam.put("resolveTemp", Arrays.asList(CollType.String, CollType.UserDefined));
	}
	
	enum CollType { 
		Integer ("0"), 
		String("''"),
		UserDefined("param");
		
		private String defaultLiteral;
		
		CollType(String dl) { this.defaultLiteral = dl; }		
		public String defaultLiteral() { return this.defaultLiteral;}
		public void setDefaultLiteral(String dl) { this.defaultLiteral = dl; }
	}	
	
	/**
	 * Auxiliary operation for getClosest
	 * @param op
	 * @param numPar
	 * @param distance
	 * @return
	 */
	protected int getClosestDistance(String op, int numPar, List<Integer> distance) {
		if (!(this.candidateOps.containsKey(numPar))) return 1000;
		distance.addAll(this.sd.distance(op, this.candidateOps.get(numPar)));	
		System.out.println(this.candidateOps.get(numPar)+"\n"+distance);		
		return Collections.min(distance);
	}
	
	protected String getClosestOperation(String op, int numPar) {
		if (!(this.candidateOps.containsKey(numPar))) return op;
		return this.sd.closest(op, this.candidateOps.get(numPar));
	}
	
	/**
	 * Heuristic method to obtain closest operation name, given an operation op. The algorithm takes
	 * into account the number of parameters and uses {@link Levenshtein} distance
	 * @param op
	 * @param numPar number of parameters of operation op
	 * @return
	 */
/*	protected String getClosest(String op, int numPar) {
		HashMap<Integer, List<Integer>> distances = new HashMap<>();
		distances.put(numPar, new ArrayList<Integer>());
		
		int minDistance = this.getClosestDistance(op, numPar, distances.get(numPar));
		
		if (minDistance >= OperationNotFoundAbstractQuickFix.threshold) {		// Not very close to operations with same number of parameters
			List<Integer> pars2explore = new ArrayList<Integer>();
			pars2explore.add(numPar+1);											// Let's check operations with one more parameter (if any)
			if (numPar > 0) pars2explore.add(numPar-1);							// Let's check operations with one less parameter (if not already 0) 
			
			int minD = minDistance;												// Threshold set to above the best solution with same number of params
			int param = -1;
			
			for (int p : pars2explore) {
				distances.put(p, new ArrayList<Integer>());
				int currentD = this.getClosestDistance(op, p, distances.get(p)); // Check if we are closer with different number of parameters
				if (currentD < minD) {
					param = p;
					minD = currentD;
				}
			}
			if (minD < minDistance) {											// Found a closer operation with different parameters
				numPar = param;
				minDistance = minD;
			}
		}
			
		int closestIndex = distances.get(numPar).indexOf(minDistance);
		String closestOp;
		if (closestIndex >= 0) 
			closestOp = this.candidateOps.get(numPar).get(closestIndex);
		else return randomOperation(op);										// We cannot recommend anything.... TODO: What to do?
		System.out.println("Closest is "+closestOp);
		return closestOp;					
	}*/
	
	protected String getClosest(String op, int numPar) {
		HashMap<Integer, List<Integer>> distances = new HashMap<>();
		distances.put(numPar, new ArrayList<Integer>());
		
		String bestOp = this.getClosestOperation(op, numPar);
		
		if (bestOp.equals(op)) {		// Not very close to operations with same number of parameters
			List<Integer> pars2explore = new ArrayList<Integer>();
			pars2explore.add(numPar+1);											// Let's check operations with one more parameter (if any)
			if (numPar > 0) pars2explore.add(numPar-1);							// Let's check operations with one less parameter (if not already 0) 
			
			for (int p : pars2explore) {
				bestOp = this.getClosestOperation(op, p);
				if (!bestOp.equals(op)) break;
			}
		}
			
		if (bestOp.equals(op))
			return randomOperation(op);										// We cannot recommend anything.... TODO: What to do?
		System.out.println("Closest is "+bestOp);
		return bestOp;					
	}
	
	protected String randomOperation(String defaultValue) {
		for (int i : this.candidateOps.keySet()) {
			List<String> ops = this.candidateOps.get(i);
			if (ops.size()>0) return ops.get(0);
		}
		return defaultValue;
	}
	
	/**
	 * @param closest
	 * @return number of params of closest
	 */
	protected int getParamsClosest(String closest) {
		for (int par : Arrays.asList(0, 1, 2)) {
			if (this.candidateOps.getOrDefault(par, Collections.emptyList()).contains(closest)) 
				return par;			
		}
		return 0;
	}
	
	private Helper getHelper(String name) {
		List<Helper> helpers = ATLUtils.getAllHelpers(this.getATLModel());
		Optional<Helper> helper = helpers.stream().filter( p -> ATLUtils.getHelperName(p).equals(name)).findAny();
		return helper.orElse(null);
	}
	
	private void fixParams( Helper replaced, OperationCallExp c, int numP, int paramsClosest ) {
		Type [] types = ATLUtils.getArgumentTypes(replaced);
		for (int i = numP; i < paramsClosest; i++) {
			c.getArguments().add(Conversions.createDefaultOCLLiteral(types[i]));
		}	
	}
	
	private boolean isCompatible (Type t , String s) {
		if (t instanceof Metaclass) return ((Metaclass)t).getName().equals(s);
		if (t instanceof BooleanType) return s.equals("Boolean");
		if (t instanceof FloatType) return s.equals("Float");
		if (t instanceof IntegerType) return s.equals("Integer");
		if (t instanceof StringType) return s.equals("String");
		return false;
	}
	
	private void fixParams( String replaced, OperationCallExp c, int numP, int paramsClosest ) {
		for (int i = numP; i < paramsClosest; i++) {
			CollType ct = primitiveParam.get(replaced).get(i);
			OclExpression exp = Conversions.createDefaultOCLLiteral(ct.name());
			System.out.println("Needed type: "+this.neededType());
			if (exp instanceof VariableExp) {
				VariableExp ve = (VariableExp) exp;
				List<VariableDeclaration> lvd = ATLUtils2.getAvailableDeclarations(c);
				if (lvd.size()>0) {
					if (this.neededType()!=null) {
						List<VariableDeclaration> filtered = lvd.stream().filter( p -> this.isCompatible(p.getInferredType(), this.neededType())).collect(Collectors.toList());
						if (filtered.size()>0) ve.setReferredVariable(filtered.get(0));
						else ve.setReferredVariable(lvd.get(0));
					} else ve.setReferredVariable(lvd.get(0));		// TODO: Do type checking
				}
				else {} // TODO: create an object?
			}
			c.getArguments().add(exp);
		}	
	}
		
	protected void fixParams( String closest, OperationCallExp c) {
		int paramsClosest = this.getParamsClosest(closest);
		int numP = c.getArguments().size();
		
		Helper replaced = this.getHelper(closest);		
		
		if (paramsClosest > numP) {
			System.out.println("You need to add "+(paramsClosest - numP )+" params");
			if (replaced != null) this.fixParams(replaced, c, numP, paramsClosest);
			else if (primitiveParam.containsKey(closest)) this.fixParams(closest, c, numP, paramsClosest);
		}
		else if (paramsClosest < numP) {
			System.out.println("You need to remove "+(numP - paramsClosest )+" params");
			// We remove the last ones?
			for (int i = 0; i< numP-paramsClosest; i++)
				c.getArguments().remove(c.getArguments().size()-1);
		}
		else System.out.println("number of params match.");
	}
	
	public String neededType() { return null; }


	protected List<ContextHelper> getCompatibleContextHelpers(Type srcType, ATLModel m, Predicate<Helper> predicate) {		
		return ATLUtils.getAllHelpers(m).stream().
						filter(e -> e instanceof ContextHelper).
						filter(predicate).
						map(e -> (ContextHelper) e).
						filter(e -> hasCompatibleType(srcType, e.getDefinition().getContext_().getContext_().getInferredType())).collect(Collectors.toList());
	}
	
	protected boolean hasCompatibleType(Type receptorType, Type helperType) {
		return TypingModel.assignableTypesStatic(helperType, receptorType);
	}

}
