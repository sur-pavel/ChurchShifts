package ru.surpavel.churchshifts.dto;

import lombok.Data;
import ru.surpavel.churchshifts.entity.DayOfWeek;

import java.io.Serializable;

@Data
public class ServantDto implements Serializable {
	private final Long id;
	private final String rank;
	private final String firstName;
	private final String lastName;
	private final int maxDaysInWeek;
	private final DayOfWeek possibleDaysInWeek;
}
