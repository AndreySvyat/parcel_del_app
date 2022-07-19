package ru.svyat.pdapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svyat.pdapp.dto.enums.ParcelStatus;
import ru.svyat.pdapp.dto.req.ParcelNew;
import ru.svyat.pdapp.dto.req.ParcelPatch;
import ru.svyat.pdapp.dto.resp.ParcelRsp;
import ru.svyat.pdapp.dto.resp.ParcelShortRsp;
import ru.svyat.pdapp.model.Parcel;
import ru.svyat.pdapp.model.User;
import ru.svyat.pdapp.repo.ParcelRepository;
import ru.svyat.pdapp.repo.UserRepository;
import ru.svyat.pdapp.service.ParcelService;

import java.time.ZonedDateTime;

import static ru.svyat.pdapp.dto.enums.UserRole.*;
import static ru.svyat.pdapp.utils.NullityCheck.let;
import static ru.svyat.pdapp.utils.SecurityUtils.currentLogin;
import static ru.svyat.pdapp.utils.SecurityUtils.hasScope;

@Service
@RequiredArgsConstructor
public class BaseParcelService implements ParcelService {

	private final ParcelRepository parcelRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public Page<ParcelShortRsp> getMyParcels(Pageable pageable) {
		String me = currentLogin();
		if(hasScope(ADMIN.name()))
			return parcelRepository.findAll(pageable).map(this::rspShortOfParcel);
		if(hasScope(COURIER.name()))
			return parcelRepository.findByCourier(me, pageable).map(this::rspShortOfParcel);
		return parcelRepository.findByRecipient(me, pageable).map(this::rspShortOfParcel);
	}

	@Override
	@Transactional
	public ParcelRsp getOne(Long id) {
		return rspOfParcel(parcelRepository.findById(id)
				                   .orElseThrow(() -> new RuntimeException("No parcels with id " + id)));
	}

	@Override
	@Transactional
	public ParcelRsp patchOne(Long id, ParcelPatch patch) {
		Parcel parcel = parcelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No parcels with id " + id));
		boolean updated = false;
		if(patch.getStatus() != null &&
				hasScope(ADMIN.name()) ||
				hasScope(COURIER.name())) {
			parcel.setStatus(parcel.getStatus());
			updated = true;
		}
		if(patch.getCurrentLocation() != null && hasScope(COURIER.name())) {
			parcel.setCurrentLocation(patch.getCurrentLocation());
			updated = true;
		}
		if(patch.getDestination() != null && hasScope(USER.name())) {
			parcel.setDestination(patch.getDestination());
			updated = true;
		}
		if(updated)
			parcel.setUpdated(ZonedDateTime.now());
		return rspOfParcel(parcelRepository.saveAndFlush(parcel));
	}

	@Override
	@Transactional
	public ParcelRsp createOne(ParcelNew parcelNew) {
		String myLogin = currentLogin();
		User me = userRepository.findById(myLogin)
				.orElseThrow(()->new RuntimeException(String.format("User %s is not registered", myLogin)));
		Parcel parcel = Parcel.builder()
				.created(ZonedDateTime.now())
				.updated(ZonedDateTime.now())
				.currentLocation(parcelNew.getStartLocation())
				.destination(parcelNew.getDestination())
				.requestor(me)
				.status(ParcelStatus.NEW)
				.price(1000L)
				.build();
		return rspOfParcel(parcelRepository.saveAndFlush(parcel));
	}

	private ParcelRsp rspOfParcel(Parcel parcel){
		return ParcelRsp.builder()
				.id(parcel.getId())
				.status(parcel.getStatus())
				.currentLoc(parcel.getCurrentLocation())
				.destination(parcel.getDestination())
				.courier(let(parcel.getCourier(), it -> it.getUser().getLogin()))
				.requestor(parcel.getRequestor().getLogin())
				.price(parcel.getPrice())
				.build();
	}

	private ParcelShortRsp rspShortOfParcel(Parcel parcel){
		return ParcelShortRsp.builder()
				.id(parcel.getId())
				.status(parcel.getStatus())
				.courier(let(parcel.getCourier(), it -> it.getUser().getLogin()))
				.build();
	}
}
