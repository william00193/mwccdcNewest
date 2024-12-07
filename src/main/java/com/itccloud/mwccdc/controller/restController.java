package com.itccloud.mwccdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itccloud.mwccdc.mapper.ccdcRepository;
import com.itccloud.mwccdc.mapper.ccdcUser;

@RestController
public class restController {
	
	
	@Autowired
	ccdcRepository userRepo;
	
	
	
	@GetMapping("/{firstname}")
	public List<ccdcUser> getUser(@PathVariable String firstname) {
		
		return userRepo.findUser();
		
		
	}
	

}
