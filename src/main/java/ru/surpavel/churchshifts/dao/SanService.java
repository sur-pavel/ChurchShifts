package ru.surpavel.churchshifts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.domain.Degree;
import ru.surpavel.churchshifts.domain.San;

import java.util.List;

@Service
public class SanService {
    private final SanRepository sanRepository;

    @Autowired
    public SanService(SanRepository sanRepository) {
        this.sanRepository = sanRepository;
    }

    public void createSan(San san){
        if (sanRepository.findByTitle(san.getTitle()) != null) {
            throw new IllegalArgumentException("San already exists");
        } else {
            sanRepository.save(san);
        }
    }
    public void createSan(String title, String sanDegree) {
        Degree degree = Degree.valueOf(sanDegree.toUpperCase());
        if (sanRepository.findByTitle(title) != null) {
            throw new IllegalArgumentException("San already exists");
        } else {
            sanRepository.save(new San(title, degree));
        }
    }

    public List<San> findAll(){
        return (List<San>) sanRepository.findAll();
    }

    public San findByTitle(String title) {
        if(sanRepository.findByTitle(title) == null){
            throw new IllegalArgumentException(String.format("San with title %s doesn't exists", title));
        }
        return sanRepository.findByTitle(title);
    }
}
