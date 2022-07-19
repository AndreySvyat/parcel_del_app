package ru.svyat.pdapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.svyat.pdapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
