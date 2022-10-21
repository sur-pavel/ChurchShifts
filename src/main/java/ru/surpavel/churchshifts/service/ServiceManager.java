package ru.surpavel.churchshifts.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.surpavel.churchshifts.entity.Holiday;
import ru.surpavel.churchshifts.entity.Servant;
import ru.surpavel.churchshifts.entity.Service;
import ru.surpavel.churchshifts.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceManager implements IManager<Service> {
	private final ServiceRepository serviceRepository;

	@Autowired
	public ServiceManager(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	public Service save(Service entity) {
		return serviceRepository.save(entity);
	}

	@Override
	public Optional<Service> findById(Long id) {
		return serviceRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return serviceRepository.existsById(id);
	}

	@Override
	public List<Service> findAll() {
		return serviceRepository.findAll();
	}

	@Override
	public void delete(Service entity) {
		serviceRepository.delete(entity);
	}

	public List<Service> findByServants(List<Servant> servants) {
		return serviceRepository.findByServantsIn(servants);
	}

	public List<Service> findByServiceType(String serviceType) {
		return serviceRepository.findByServiceType(serviceType);
	}

	public List<Service> findByHolidays(List<Holiday> holidays) {
		return serviceRepository.findByHolidaysIn(holidays);
	}
}
