package ru.surpavel.churchshifts.dao;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.domain.Degree;
import ru.surpavel.churchshifts.domain.Shift;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ShiftService {
    private final
    ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void createShift(String degree, String name, Timestamp timestamp){
        if (shiftRepository.findByDegreeAndNameAndTime(Degree.valueOf(degree), name, new LocalTime(timestamp)) == null){
            shiftRepository.save(new Shift(Degree.valueOf(degree), name, new LocalTime(timestamp)));
        } else {
            throw new IllegalArgumentException("Shift already exists");
        }
    }

    public List<Shift> findByName(String name){
        return shiftRepository.findByName(name);
    }

    public List<Shift> findByDegree(String degree) {
        return shiftRepository.findByDegree(Degree.valueOf(degree.toUpperCase()));
    }

    public List<Shift> findByTime(Timestamp timestamp) {
        return shiftRepository.findByTime(new LocalTime(timestamp));
    }

    public List<Shift> findAll() {
        return (List<Shift>) shiftRepository.findAll();
    }
}
