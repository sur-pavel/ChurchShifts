package ru.surpavel.churchshifts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.entity.Day;
import ru.surpavel.churchshifts.repository.DayRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DayManager implements IManager<Day> {
	private final DayRepository dayRepository;

	@Autowired
	public DayManager(DayRepository dayRepository) {
		this.dayRepository = dayRepository;
	}

	@Override
	public Day save(Day entity) {
		return dayRepository.save(entity);
	}

	@Override
	public Optional<Day> findById(Long id) {
		return dayRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return dayRepository.existsById(id);
	}

	@Override
	public List<Day> findAll() {
		return dayRepository.findAll();
	}

	@Override
	public void delete(Day entity) {
		dayRepository.delete(entity);
	}

	public Day findByDate(LocalDate date) {
		return dayRepository.findByDate(date);
	}

	public List<Day> findDaysByDate(LocalDate date) {
		return dayRepository.findDaysByDate(date);
	}

}
