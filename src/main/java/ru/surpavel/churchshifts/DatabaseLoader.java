package ru.surpavel.churchshifts;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.surpavel.churchshifts.dao.AssignmentRepository;
import ru.surpavel.churchshifts.dao.SanRepository;
import ru.surpavel.churchshifts.dao.ServantRepository;
import ru.surpavel.churchshifts.dao.ShiftRepository;
import ru.surpavel.churchshifts.domain.*;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final SanRepository sanRepository;
    private final ShiftRepository shiftRepository;
    private final ServantRepository servantRepository;
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public DatabaseLoader(SanRepository sanRepository, ShiftRepository shiftRepository,
                          ServantRepository servantRepository, AssignmentRepository assignmentRepository) {
        this.sanRepository = sanRepository;
        this.shiftRepository = shiftRepository;
        this.servantRepository = servantRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        San deacon = new San("диакон", Degree.DEACON);
        sanRepository.save(deacon);
        Servant deaconPavel = new Servant(deacon, "Павел", "Сурков");
        servantRepository.save(deaconPavel);
        Shift deaconLiturgy = new Shift(Degree.DEACON, "служит вечером", new LocalTime(8, 0));
        shiftRepository.save(deaconLiturgy);
        assignmentRepository.save(new Assignment(new LocalDate(2017, 11, 17), deaconLiturgy, deaconPavel));

    }
}
