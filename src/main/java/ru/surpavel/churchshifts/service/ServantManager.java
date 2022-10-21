package ru.surpavel.churchshifts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.entity.Servant;
import ru.surpavel.churchshifts.repository.ServantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServantManager implements IManager<Servant> {
	private final ServantRepository servantRepository;

	@Autowired
	public ServantManager(ServantRepository servantRepository) {
		this.servantRepository = servantRepository;
	}

	@Override
	public Servant save(Servant entity) {
		return servantRepository.save(entity);
	}

	@Override
	public Optional<Servant> findById(Long id) {
		return servantRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return servantRepository.existsById(id);
	}

	@Override
	public List<Servant> findAll() {
		return servantRepository.findAll();
	}

	@Override
	public void delete(Servant entity) {
		servantRepository.delete(entity);
	}

	public List<Servant> findByFirstName(String firstName) {
		return servantRepository.findByFirstName(firstName);
	}

	public List<Servant> findByLastName(String lastName) {
		return servantRepository.findByLastName(lastName);
	}

	public List<Servant> findByFirstNameAndLastName(String firstName, String lastName) {
		return servantRepository.findByFirstNameAndLastName(firstName, lastName);
	}

}
