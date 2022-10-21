package ru.surpavel.churchshifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surpavel.churchshifts.entity.Holiday;
import ru.surpavel.churchshifts.entity.Servant;
import ru.surpavel.churchshifts.entity.Service;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	List<Service> findByServantsIn(List<Servant> servants);

	List<Service> findByHolidaysIn(List<Holiday> holidays);

	List<Service> findByServiceType(String serviceType);
}
