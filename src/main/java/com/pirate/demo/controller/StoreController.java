package com.pirate.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pirate.demo.dto.BusinessTimes;
import com.pirate.demo.dto.Holidays;
import com.pirate.demo.dto.Store;
import com.pirate.demo.dto.StoreForDetail;
import com.pirate.demo.dto.StoreLists;
import com.pirate.demo.service.StoreServiceImpl;
import com.pirate.demo.validation.EqualsValidation;

@RestController
public class StoreController {
	@Autowired
	StoreServiceImpl storeServiceImpl;

	//점포 추가
	@PostMapping("/stores")
	public ResponseEntity<String> addStore(@RequestBody Store store){
		List<BusinessTimes> businessTimesLists = store.getBusinessTimes();
		EqualsValidation equalsValidation = new EqualsValidation(businessTimesLists);
		Boolean equals = equalsValidation.checkEquals();
		if(equals) {
			return new ResponseEntity<>("Open시간과 Close시간은 같을 수 없습니다.", HttpStatus.BAD_REQUEST);
		}else {
			storeServiceImpl.addStore(store);
			return new ResponseEntity<>("등록 완료", HttpStatus.CREATED);
		}
	}
	
	//특정 휴무일 추가
	@PostMapping("/stores/holidays")
	public ResponseEntity<String> addHolidays(@RequestBody Holidays holidays){
		storeServiceImpl.addHolidays(holidays);
		return new ResponseEntity<>("등록 완료", HttpStatus.CREATED);
	}
	
	//점포 목록 조회
	@GetMapping("/stores")
	public ResponseEntity<Object> getStoreLists(){
		List<StoreLists> storeLists = storeServiceImpl.getStoreLists();
		return new ResponseEntity<>(storeLists, HttpStatus.OK);
	}
	
	//점포 상세 정보 조회
	@GetMapping("/stores/{id}")
	public ResponseEntity<Object> getStoreDetail(@PathVariable("id") int id){
		StoreForDetail storeDetail = storeServiceImpl.getStoreDetail(id);
		return new ResponseEntity<>(storeDetail, HttpStatus.OK);
	}
	
	//점포 삭제
	@DeleteMapping("/stores/{id}")
	public ResponseEntity<String> deleteStore(@PathVariable("id") int id){
		storeServiceImpl.deleteStore(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
}
