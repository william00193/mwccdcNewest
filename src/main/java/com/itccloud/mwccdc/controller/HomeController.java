package com.itccloud.mwccdc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itccloud.mwccdc.mapper.ccdcInfo;
import com.itccloud.mwccdc.mapper.ccdcPreferences;
import com.itccloud.mwccdc.mapper.ccdcRepository;
import com.itccloud.mwccdc.mapper.ccdcService;
import com.itccloud.mwccdc.mapper.ccdcUser;

@Controller
public class HomeController {
	
	
	@Autowired
	private ccdcRepository repository;
	
	
	
	@GetMapping("/home")
	public String home(Model model) throws IOException {
		
	
	    return "home-form";
	    
	    
	}
	
	@GetMapping("/business1")
	public String business1(Model model) throws IOException {
		
	
	    return "business1-form";
	    
	    
	}
	
	
	@GetMapping("/advance")
	public String bootstrapHome(Model model) throws IOException {
		
		
		
	    List<personObject> users = csvFileHandling.readCSV(); 
	    model.addAttribute("users1", users);

	    
	
	    return "advance-form";
	    
	    
	}
	
	
	
	@GetMapping("/bootstrap-feature1")
	public String bootstrapFeature1(Model model) throws IOException {
		
	
		
	    List<personObject> users = csvFileHandling.readCSV(); 
	    model.addAttribute("users1", users);

	    
	    return "bootstrap-feature1-form";
	    
	    
	}
	
	
	
	@GetMapping("/bootstrap-point")
	public String bootstrapPoint(Model model) throws IOException {
		
	
		List<ccdcUser> ccdcUser = repository.findUser();
		List<ccdcPreferences> ccdcPreferences = repository.findPreferences();
		List<ccdcInfo> ccdcInfo = repository.findInfo();
		
		model.addAttribute("ccdcuser", ccdcUser);
		model.addAttribute("ccdcpreferences", ccdcPreferences);
		model.addAttribute("ccdcinfo", ccdcInfo);
		
		
	    return "bootstrap-point-form";
	    
	    
	}
	

	
// This was another part I had to add for the HW11 challenge
// Kept things very similar to the last mappers, but this time I just @autowired here instead of in the ajaxController
// Somehow it being referenced elsewhere was throwing errors. 
	    @Autowired
	    private ccdcService ccdcService;

	    @GetMapping("/bootstrap-reward")
	    public String rewardPage(Model model) throws IOException {
	        ccdcService.allocateSeats();
	        List<ccdcUser> allocatedSeats = ccdcService.getAllocatedSeats();
	        model.addAttribute("allocatedSeats", allocatedSeats);
	        return "bootstrap-reward-form";
	    }

	
}

		
