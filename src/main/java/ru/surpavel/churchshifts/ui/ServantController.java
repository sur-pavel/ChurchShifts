package ru.surpavel.churchshifts.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.churchshifts.dao.ServantService;
import ru.surpavel.churchshifts.domain.Servant;

import java.util.List;

@RestController
@RequestMapping("/servants")
public class ServantController {
    private final ServantService servantService;

    @Autowired
    public ServantController(ServantService servantService) {
        this.servantService = servantService;
    }

    @PostMapping
    public String createServant(@RequestBody Servant servant){
        servantService.createServant(servant);
        return "SUCCESS";
    }

    @GetMapping
    public List<Servant> findAll(){
        return servantService.findAll();
    }
}

