package ru.surpavel.churchshifts.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

@Data
public class ServiceDto implements Serializable {
	private final Long id;
	private final LocalTime time;
	private final String serviceType;
	private final Set<HolidayDto> holidays;
	private final Set<ServantDto> servants;
}
