package ru.surpavel.churchshifts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surpavel.churchshifts.domain.Degree;
import ru.surpavel.churchshifts.domain.San;
import ru.surpavel.churchshifts.domain.Servant;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServantService {
    private final ServantRepository servantRepository;
    private final SanRepository sanRepository;

    @Autowired
    public ServantService(ServantRepository servantRepository, SanRepository sanRepository) {
        this.servantRepository = servantRepository;
        this.sanRepository = sanRepository;
    }

    public void createServant(Servant servant) {
        San san = sanRepository.findByTitle(servant.getSan().getTitle());
        if (san == null) {
            throw new IllegalArgumentException("Such with title san doesn't exists");
        } else if (servantRepository.findBySanAndFirstNameAndLastName(san, servant.getFirstName(), servant.getLastName()) != null) {
            throw new IllegalArgumentException("Servant already exists");
        } else {
            servantRepository.save(new Servant(san, servant.getFirstName(), servant.getLastName()));
        }
    }
    public void createServant(String title, String firstName, String lastName) {
        San san = sanRepository.findByTitle(title);
        if (san == null) {
            throw new IllegalArgumentException(String.format("Such with title %s san doesn't exists", title));
        } else if (servantRepository.findBySanAndFirstNameAndLastName(san, firstName, lastName) != null) {
            throw new IllegalArgumentException("Servant already exists");
        } else {
            servantRepository.save(new Servant(san, firstName, lastName));
        }
    }

    public List<Servant> findByDegree(String degree) {
        List<Servant> servants = (List<Servant>) servantRepository.findAll();

        return servants.stream()
                .filter(servant -> servant.getSan().getDegree() == Degree.valueOf(degree))
                .collect(Collectors.toList());
    }

    public List<Servant> findAll(){
        return (List<Servant>) servantRepository.findAll();
    }
}
