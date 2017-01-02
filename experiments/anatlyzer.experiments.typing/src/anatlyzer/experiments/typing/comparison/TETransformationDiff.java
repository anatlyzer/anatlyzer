package anatlyzer.experiments.typing.comparison;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.util.Pair;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TETransformation;

public class TETransformationDiff {

	private TETransformation trafo1;
	private TETransformation trafo2;
	private List<TEProblem> problemsNotFound_1 = new ArrayList<TEProblem>();
	private List<Pair<TEProblem, TEProblem>> problemsWithDifferentStatus = new ArrayList<Pair<TEProblem, TEProblem>>();
	
	public TETransformationDiff(TETransformation trafo1, TETransformation trafo2) {
		this.trafo1 = trafo1;
		this.trafo2 = trafo2;
	}

	public String getTransformationName() {
		return trafo1.getName();
	}
	
	public void addProblemNotFound_1(TEProblem p1) {
		problemsNotFound_1.add(p1);		
	}

	public void addProblemWithDifferentStatus(TEProblem p1, TEProblem p2) {
		problemsWithDifferentStatus.add(new Pair<TEProblem, TEProblem>(p1, p2));
	}
	
	public List<TEProblem> getProblemsNotFound_1() {
		return problemsNotFound_1;
	}
	
	public List<Pair<TEProblem, TEProblem>> getProblemsWithDifferentStatus() {
		return problemsWithDifferentStatus;
	}
	
}
