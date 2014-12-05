package anatlyzer.atl.tasks;

public class All {
	public static void main(String[] args) throws Exception {
		RefactorATLMetamodel.main(args);
		ApplyPackageMerge.main(args);
		GenerateCopier.main(args);
		GenerateVisitor.main(args);
	}
}
