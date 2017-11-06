package ru.surpavel.churchshifts.domain;

import javax.persistence.*;

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    BaseEntity() {
        id = null;
    }
}
