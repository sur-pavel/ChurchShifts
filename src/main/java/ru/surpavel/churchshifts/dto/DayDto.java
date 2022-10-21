package ru.surpavel.churchshifts.dto;

import lombok.Data;
import ru.surpavel.churchshifts.dto.ServiceDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class DayDto implements Serializable {
	private final Long id;
	private final LocalDate date;
	private final List<ServiceDto> services;
}
