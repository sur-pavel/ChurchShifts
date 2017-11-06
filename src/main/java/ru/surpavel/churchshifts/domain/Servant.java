package ru.surpavel.churchshifts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Servant extends BaseEntity {

    @Column(length = 25)
    private String firstName;
    @Column(length = 25)
    private String lastName;
    @OneToOne
    private San san;

    public San getSan() {
        return san;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Servant() {
    }


    public Servant(San san, String firstName, String lastName) {
        this.san = san;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
