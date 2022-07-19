package ru.svyat.pdapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.svyat.pdapp.model.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {
	@NonNull
	Page<Courier> findAll(@NonNull Pageable pageable);
}
