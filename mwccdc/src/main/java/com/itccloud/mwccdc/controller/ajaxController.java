package com.itccloud.mwccdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itccloud.mwccdc.mapper.ccdcService;
import com.itccloud.mwccdc.mapper.ccdcUser;
import com.fasterxml.jackson.core.type.TypeReference;

@RestController
public class ajaxController {
	
	
	@Autowired
	ccdcService service;
	

	
	@PostMapping("/api/saveUser")
	public String handleUpdate(@RequestBody String requestBody) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		boolean ret = true;
		
		try {
			
			List<ccdcUser> users = mapper.readValue(requestBody, new TypeReference<List<ccdcUser>>() {});
			service.saveUser(users);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ret) {
			return "{\"code\":\"success\"}";
		} else {
			return "{\"code\":\"failed\"}";
		}
		

		
	}
	
	
}
