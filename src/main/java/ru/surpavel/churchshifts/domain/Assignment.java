package ru.surpavel.churchshifts.domain;

import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Assignment extends BaseEntity {
    @Column
    private LocalDate date;
    @OneToOne
    private Shift shift;
    @OneToOne
    private Servant servant;

    public LocalDate getDate() {
        return date;
    }

    public Shift getShift() {
        return shift;
    }

    public Servant getServant() {
        return servant;
    }

    public Assignment() {
    }

    public Assignment(LocalDate date, Shift shift, Servant servant) {
        this.date = date;
        this.shift = shift;
        this.servant = servant;
    }
}
