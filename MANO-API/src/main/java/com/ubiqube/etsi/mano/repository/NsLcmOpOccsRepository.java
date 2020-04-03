package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;

public interface NsLcmOpOccsRepository extends CrudRepository<NsLcmOpOcc> {

	NsLcmOpOcc createLcmOpOccs(String nsInstanceId, @Nonnull NsLcmOpType instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String lcmOpOccsId, String processId);

}
