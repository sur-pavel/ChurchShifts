package ru.surpavel.churchshifts.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.surpavel.churchshifts.dto.DayDto;
import ru.surpavel.churchshifts.entity.Day;
import ru.surpavel.churchshifts.service.DayManager;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DayController {

	private final DayManager dayManager;
	private final ModelMapper modelMapper;

	@GetMapping("/days")
	public List<DayDto> findByMonth(@DateTimeFormat(pattern = "yyyy-MM") LocalDate month) {
		return dayManager.findDaysByDate(month).stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	@GetMapping("/days/{date}")
	public DayDto findByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		return convertToDTO(dayManager.findByDate(date));
	}

	@PostMapping("/days")
	public HttpStatus saveDays(@RequestBody @Valid List<DayDto> daysDto, BindingResult result) {
		if (result.hasErrors()) {
			return HttpStatus.BAD_REQUEST;
		} else {
			for (DayDto dayDto : daysDto) {
				dayManager.save(convertToEntity(dayDto));
			}
			return HttpStatus.OK;
		}
	}

	private Day convertToEntity(DayDto dayDto) {
		return modelMapper.map(dayDto, Day.class);
	}

	private DayDto convertToDTO(Day day) {
		return modelMapper.map(day, DayDto.class);
	}
}
