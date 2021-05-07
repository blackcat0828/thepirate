package com.pirate.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class StoreForDetail {
	private String name;
	private String owner;
	private String description;
	private int level;
	private String address;
	private String phone;	
	private List<BusinessDays> businessTimes;
}
