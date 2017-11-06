package ru.surpavel.churchshifts.dao;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import ru.surpavel.churchshifts.domain.Assignment;
import ru.surpavel.churchshifts.domain.Servant;
import ru.surpavel.churchshifts.domain.Shift;


public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    Assignment findByDateAndShiftAndServant(LocalDate date, Shift shift, Servant servant);
}
