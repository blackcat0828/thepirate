package com.pirate.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Holidays {
	private int id;
	private List<LocalDate> holidays;
}
