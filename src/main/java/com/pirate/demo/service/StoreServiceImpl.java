package com.pirate.demo.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pirate.demo.dto.BusinessDays;
import com.pirate.demo.dto.BusinessTimes;
import com.pirate.demo.dto.Holidays;
import com.pirate.demo.dto.Store;
import com.pirate.demo.dto.StoreForDetail;
import com.pirate.demo.dto.StoreLists;
import com.pirate.demo.mapper.StoreMapper;


@Service
public class StoreServiceImpl {
	@Autowired
	private StoreMapper storeMapper;
	
	public void addStore(Store store) {
		List<BusinessTimes> businessTimes = store.getBusinessTimes();

		for(int i = 0; i < businessTimes.size(); i++) {
			if((businessTimes.get(i).getOpen()).equals("24:00")) {
				businessTimes.get(i).setOpen("23:59:59");
			}
			
			if((businessTimes.get(i).getClose()).equals("24:00")) {
				businessTimes.get(i).setClose("23:59:59");
			}
		}
		
		storeMapper.addStore(store);
		int pk = store.getId();
		
		for(int i = 0; i < businessTimes.size(); i++) {
			businessTimes.get(i).setId(pk);
		}
		
		storeMapper.addBusinessDays(businessTimes);
		
	}
	
	public void addHolidays(Holidays holidays) {
		int id = holidays.getId();
		List<LocalDate> holidayList = holidays.getHolidays();
		HashMap holidayHashMap = new HashMap();
		holidayHashMap.put("id", id);
		holidayHashMap.put("holidayList", holidayList);
		
		storeMapper.addHolidays(holidayHashMap);
	}
	
	public List<StoreLists> getStoreLists(){
		return storeMapper.getStoreLists();
	}
	
	
	public StoreForDetail getStoreDetail(int id) {
		StoreForDetail storeDetail = new StoreForDetail();
		storeDetail = storeMapper.getStoreDetail(id);
		List<BusinessDays> businessDays = storeDetail.getBusinessTimes();
		businessDays.add(storeMapper.getStoreDetailTomorrow(id));
		businessDays.add(storeMapper.getStoreDetailAfterTomorrow(id));
		
		for(int i = 0; i < businessDays.size(); i++) {
			if((businessDays.get(i).getOpen()).equals("23:59:59")) {
				businessDays.get(i).setOpen("24:00");
			}
			
			if((businessDays.get(i).getClose()).equals("23:59:59")) {
				businessDays.get(i).setClose("24:00");
			}
		}
		
		storeDetail.setBusinessTimes(businessDays);
		return storeDetail;
	}
	
	public void deleteStore(int id) {
		storeMapper.deleteStore(id);
	}

	
}
