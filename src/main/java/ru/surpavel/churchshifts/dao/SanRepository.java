package ru.surpavel.churchshifts.dao;

import org.springframework.data.repository.CrudRepository;
import ru.surpavel.churchshifts.domain.San;

public interface SanRepository extends CrudRepository <San, Long> {
    San findByTitle(String title);
}
