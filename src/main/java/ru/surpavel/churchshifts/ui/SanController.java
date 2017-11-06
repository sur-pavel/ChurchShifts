package ru.surpavel.churchshifts.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.surpavel.churchshifts.dao.SanService;
import ru.surpavel.churchshifts.domain.San;

import java.util.List;

@RestController
@RequestMapping("/sans")
public class SanController {
    private final SanService sanService;

    @Autowired
    public SanController(SanService sanService) {
        this.sanService = sanService;
    }

    @PostMapping
    public String createSan(@RequestBody San san) {
        sanService.createSan(san);
        return "SUCCESS";
    }


    @GetMapping
    public List<San> findAll(){
        return sanService.findAll();
    }

    @GetMapping("/{title}")
    public San findByTitle(@PathVariable String title) {


        return sanService.findByTitle(title);
    }
}
