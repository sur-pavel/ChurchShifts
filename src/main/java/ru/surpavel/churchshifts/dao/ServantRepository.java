package ru.surpavel.churchshifts.dao;

import org.springframework.data.repository.CrudRepository;
import ru.surpavel.churchshifts.domain.San;
import ru.surpavel.churchshifts.domain.Servant;

import java.util.List;

public interface ServantRepository extends CrudRepository<Servant, Long> {
    Servant findBySanAndFirstNameAndLastName(San san, String firstName, String lastName);

    List<Servant> findBySan(San san);
}
