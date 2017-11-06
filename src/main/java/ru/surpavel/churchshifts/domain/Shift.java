package ru.surpavel.churchshifts.domain;

import org.joda.time.LocalTime;

import javax.persistence.Entity;

@Entity
public class Shift extends BaseEntity{

    private Degree degree;
    private String name;
    private LocalTime time;

    public Degree getDegree() {
        return degree;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public Shift(Degree degree, String name, LocalTime time) {
        this.degree = degree;
        this.name = name;
        this.time = time;
    }

    public Shift() {

    }
}
