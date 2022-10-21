package ru.surpavel.churchshifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.churchshifts.entity.Day;

import java.time.LocalDate;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {
	Day findByDate(LocalDate date);

	List<Day> findDaysByDate(LocalDate date);

}
