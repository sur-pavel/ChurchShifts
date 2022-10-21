package ru.surpavel.churchshifts.dto;

import lombok.Data;
import ru.surpavel.churchshifts.entity.VestmentColor;

import java.io.Serializable;
import java.util.Set;

@Data
public class HolidayDto implements Serializable {
	private final Long id;
	private final String displayName;
	private final boolean isChurchDevoted;
	private final VestmentColor vestmentColor;
	private final Set<ServiceDto> services;
}
