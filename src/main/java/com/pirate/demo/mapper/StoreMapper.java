package com.pirate.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pirate.demo.dto.BusinessDays;
import com.pirate.demo.dto.BusinessTimes;
import com.pirate.demo.dto.Store;
import com.pirate.demo.dto.StoreForDetail;
import com.pirate.demo.dto.StoreLists;



@Mapper
public interface StoreMapper {
	int addStore(Store store);
	void addBusinessDays(List<BusinessTimes> businessTimes);
	void addHolidays(HashMap HolidayHashMap);
	List<StoreLists> getStoreLists();
	StoreForDetail getStoreDetail(int id);
	BusinessDays getStoreDetailTomorrow(int id);
	BusinessDays getStoreDetailAfterTomorrow(int id);
	void deleteStore(int id);
}
