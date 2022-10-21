package ru.surpavel.churchshifts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.entity.Holiday;
import ru.surpavel.churchshifts.repository.HolidayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayManager implements IManager<Holiday> {

	private final HolidayRepository holidayRepository;

	@Autowired
	public HolidayManager(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}

	@Override
	public Holiday save(Holiday entity) {
		return holidayRepository.save(entity);
	}

	@Override
	public Optional<Holiday> findById(Long id) {
		return holidayRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return holidayRepository.existsById(id);
	}

	@Override
	public List<Holiday> findAll() {
		return holidayRepository.findAll();
	}

	@Override
	public void delete(Holiday entity) {
		holidayRepository.delete(entity);
	}

	public List<Holiday> findByDisplayName(String displayName) {
		return holidayRepository.findByDisplayName(displayName);
	}
}
