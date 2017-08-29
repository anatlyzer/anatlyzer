package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_FindSimilar;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_SelectDialog;

public class NoClassFoundInMetamodel_QuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new NoClassFoundInMetamodelQuickFix_FindSimilar(),
				new NoClassFoundInMetamodelQuickFix_SelectDialog(),				
				new NoClassFoundInMetamodelQuickFix_ChangeMetamodel()			
		};
	}
}
