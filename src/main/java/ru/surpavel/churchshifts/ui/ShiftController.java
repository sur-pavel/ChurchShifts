package ru.surpavel.churchshifts.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.churchshifts.dao.ShiftService;
import ru.surpavel.churchshifts.domain.Shift;

import java.sql.Timestamp;
import java.util.List;

@RestController()
@RequestMapping("/shifts")
public class ShiftController {
    private final
    ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping
    public void createShift(@RequestParam("degree") String degree,
                            @RequestParam("name") String name,
                            @RequestParam("time") Timestamp timestamp) {
        shiftService.createShift(degree, name, timestamp);
    }

    @GetMapping("/find/{string}")
    public List<Shift> findByName(@PathVariable String string) {
        List <Shift> shifts = shiftService.findByDegree(string);
        if (shifts == null){
            shifts = shiftService.findByName(string);
        }

        return shifts;
    }

    @GetMapping
    public List<Shift> findAll(){
        return shiftService.findAll();
    }

}
