package ru.surpavel.churchshifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.churchshifts.entity.Servant;

import java.util.List;

public interface ServantRepository extends JpaRepository<Servant, Long> {
	List<Servant> findByFirstName(String firstName);
	List<Servant> findByLastName(String lastName);
	List<Servant> findByFirstNameAndLastName(String firstName, String lastName);
}
