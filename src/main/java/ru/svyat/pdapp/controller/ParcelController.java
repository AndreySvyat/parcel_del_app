package ru.svyat.pdapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.svyat.pdapp.dto.req.ParcelNew;
import ru.svyat.pdapp.dto.req.ParcelPatch;
import ru.svyat.pdapp.dto.resp.ParcelRsp;
import ru.svyat.pdapp.dto.resp.ParcelShortRsp;
import ru.svyat.pdapp.service.ParcelService;

@RestController
@RequestMapping("/parcels")
@RequiredArgsConstructor
public class ParcelController {

	private final ParcelService parcelService;

	@GetMapping
	public Page<ParcelShortRsp> getMy(Pageable pageable){
		return parcelService.getMyParcels(pageable);
	}

	@GetMapping("/{id}")
	public ParcelRsp getOne(@PathVariable Long id){
		return parcelService.getOne(id);
	}

	@PatchMapping("/{id}")
	public ParcelRsp patchOne(
			@PathVariable Long id,
			@RequestBody ParcelPatch patch){
		return parcelService.patchOne(id, patch);
	}

	@PostMapping
	public ParcelRsp createOne(@RequestBody ParcelNew parcelNew){
		return parcelService.createOne(parcelNew);
	}
}
