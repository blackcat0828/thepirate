package com.pirate.demo.validation;

import java.util.List;

import com.pirate.demo.dto.BusinessTimes;

public class EqualsValidation {
	List<BusinessTimes> businessTimesLists;
	
	public EqualsValidation(List<BusinessTimes> businessTimesLists){
		this.businessTimesLists = businessTimesLists;
	}
	
	public Boolean checkEquals() {
		Boolean result = false;
		for(int i=0; i<businessTimesLists.size(); i++) {
			if((businessTimesLists.get(i).getOpen()).equals(businessTimesLists.get(i).getClose())) {
				result = true;
			}
		}
		
		return result;
	}
}
