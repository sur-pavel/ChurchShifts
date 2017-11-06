package ru.surpavel.churchshifts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class San extends BaseEntity{
    @Column
    private String title;

    private Degree degree;

    public String getTitle() {
        return title;
    }

    public Degree getDegree() {
        return degree;
    }

    public San() {
    }

    public San(String title, Degree degree) {
        this.title = title;
        this.degree = degree;
    }
}
