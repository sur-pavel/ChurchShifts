package ru.surpavel.churchshifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.churchshifts.entity.Holiday;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	List<Holiday> findByDisplayName(String displayName);
}
