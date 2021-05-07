package com.pirate.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pirate.demo.controller.StoreController;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PirateDemoApplicationTests {
	private Gson gson = new Gson();
	
	@Autowired
	private StoreController storeController;
	
	private MockMvc mock;
	
	@BeforeEach
	public void setUp() throws Exception{
		mock = MockMvcBuilders.standaloneSetup(storeController).addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
	}
	

	
	//점포 추가 테스트
	@Test
	@Order(1)
	public void addStoreTest() throws Exception {
		JsonObject obj = new JsonObject();
		obj.addProperty("name", "해적수산");
		obj.addProperty("owner", "박해적");
		obj.addProperty("description", "스토어 등록 테스트");
		obj.addProperty("level", 2);
		obj.addProperty("address", "부산");
		obj.addProperty("phone", "010-3344-1115");
		
		JsonArray list = new JsonArray();
		
		JsonObject bt1 = new JsonObject();
		bt1.addProperty("day", "Monday");
		bt1.addProperty("open", "09:00");
		bt1.addProperty("close", "23:00");
		
		JsonObject bt2 = new JsonObject();
		bt2.addProperty("day", "Tuesday");
		bt2.addProperty("open", "09:00");
		bt2.addProperty("close", "23:00");
		
		JsonObject bt3 = new JsonObject();
		bt3.addProperty("day", "Wednesday");
		bt3.addProperty("open", "09:00");
		bt3.addProperty("close", "23:00");
		
		JsonObject bt4 = new JsonObject();
		bt4.addProperty("day", "Thursday");
		bt4.addProperty("open", "09:00");
		bt4.addProperty("close", "23:00");
		
		JsonObject bt7 = new JsonObject();
		bt7.addProperty("day", "Friday");
		bt7.addProperty("open", "09:00");
		bt7.addProperty("close", "23:00");
		
		JsonObject bt6 = new JsonObject();
		bt6.addProperty("day", "Sunday");
		bt6.addProperty("open", "09:00");
		bt6.addProperty("close", "23:00");
		
		list.add(bt1);
		list.add(bt2);
		list.add(bt3);
		list.add(bt4);
		list.add(bt7);
		list.add(bt6);
		obj.add("businessTimes", list);
		String json = gson.toJson(obj);
		
		
		
		mock.perform(post("/stores").contentType(MediaType.APPLICATION_JSON)
		.content(json)).andDo(print()).andExpect(status().isCreated());
		
	}
	
	@Test
	@Order(2)
	public void addHolidaysTest() throws Exception {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", 1);
		
		JsonArray list = new JsonArray();
		list.add("2021-05-07");
		list.add("2021-05-08");
		
		obj.add("holidays", list);
		String json = gson.toJson(obj);

		mock.perform(post("/stores/holidays").contentType(MediaType.APPLICATION_JSON)
		.content(json)).andDo(print()).andExpect(status().isCreated());
		
	}
	
	@Test
	@Order(3)
	public void getStoreListsTest() throws Exception {

		
		mock.perform(get("/stores").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	@Order(4)
	public void getStoreDetailTest() throws Exception {

		
		mock.perform(get("/stores/1").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	@Order(5)
	public void deleteStoreTest() throws Exception {

		
		mock.perform(delete("/stores/1").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
		
	}

}
