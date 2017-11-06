package ru.surpavel.churchshifts.dao;

import org.joda.time.LocalTime;
import org.springframework.data.repository.CrudRepository;
import ru.surpavel.churchshifts.domain.Degree;
import ru.surpavel.churchshifts.domain.Shift;

import java.util.List;


public interface ShiftRepository extends CrudRepository<Shift, Long> {
    Shift findByDegreeAndNameAndTime(Degree degree, String name, LocalTime time);

    List<Shift> findByName(String name);

    List<Shift> findByDegree(Degree degree);

    List<Shift> findByTime(LocalTime time);
}
