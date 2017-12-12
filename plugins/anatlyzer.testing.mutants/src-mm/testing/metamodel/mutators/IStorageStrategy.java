package testing.metamodel.mutators;

import testing.utils.MMResource;

public interface IStorageStrategy {

	void save(MMResource mutatedMetamodel);

}
