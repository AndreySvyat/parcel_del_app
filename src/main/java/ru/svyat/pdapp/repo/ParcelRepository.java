package ru.svyat.pdapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.svyat.pdapp.model.Parcel;
import ru.svyat.pdapp.model.User;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
	@Query("select parcel from Parcel parcel where parcel.requestor.login = ?1")
	Page<Parcel> findByRecipient(String recipientLogin, Pageable pageable);

	@Query("select parcel from Parcel parcel where parcel.courier.user.login = ?1")
	Page<Parcel> findByCourier(String login, Pageable pageable);
}
