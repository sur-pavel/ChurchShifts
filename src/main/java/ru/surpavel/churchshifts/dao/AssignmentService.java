package ru.surpavel.churchshifts.dao;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.domain.Assignment;
import ru.surpavel.churchshifts.domain.Servant;
import ru.surpavel.churchshifts.domain.Shift;

import java.sql.Timestamp;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public void createAssignment(Timestamp timestamp, Shift shift, Servant servant) {
        LocalDate date = new LocalDate(timestamp);
        if (shift.getDegree() != servant.getSan().getDegree()) {
            throw new IllegalArgumentException("Shift degree doesn't match servant degree");
        } else if (assignmentRepository.findByDateAndShiftAndServant(date, shift, servant) != null) {
            throw new IllegalArgumentException("Assignment already exists");
        } else {
            assignmentRepository.save(new Assignment(date, shift, servant));
        }
    }
}
