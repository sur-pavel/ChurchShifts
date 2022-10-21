package ru.surpavel.churchshifts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVANT")
public class Servant implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "RANK")
	private String rank;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MAX_DAYS_IN_WEEK")
	private int maxDaysInWeek;

	@Column(name = "POSSIBLE_DAYS_IN_WEEK")
	@Enumerated(EnumType.STRING)
	private DayOfWeek possibleDaysInWeek;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Servant servant = (Servant) o;
		return id != null
				&& Objects.equals(id, servant.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
