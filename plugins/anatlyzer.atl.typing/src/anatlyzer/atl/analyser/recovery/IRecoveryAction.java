package anatlyzer.atl.analyser.recovery;

import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.types.Type;

public interface IRecoveryAction {
	Type recover(ErrorModel m, LocalProblem p);
}
