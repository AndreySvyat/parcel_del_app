package ru.svyat.pdapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.svyat.pdapp.dto.req.ParcelNew;
import ru.svyat.pdapp.dto.req.ParcelPatch;
import ru.svyat.pdapp.dto.resp.ParcelRsp;
import ru.svyat.pdapp.dto.resp.ParcelShortRsp;

public interface ParcelService {

	Page<ParcelShortRsp> getMyParcels(Pageable pageable);

	ParcelRsp getOne(Long id);

	ParcelRsp patchOne(Long id, ParcelPatch patch);

	ParcelRsp createOne(ParcelNew parcelNew);
}
