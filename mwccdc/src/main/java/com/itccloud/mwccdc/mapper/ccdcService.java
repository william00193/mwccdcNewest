package com.itccloud.mwccdc.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ccdcService {
	

 
	
	@Autowired
	private ccdcRepository ccdcRepository;
	
	public List<ccdcPointView> findPointViews(){
		List<ccdcPointView> rets = new ArrayList<ccdcPointView>();
		
		List<ccdcUser> users = ccdcRepository.findUser();
		List<ccdcPreferences> preferences = ccdcRepository.findPreferences();
		List<ccdcInfo> info = ccdcRepository.findInfo();
		
		
//		Decided against this part for now, as i'm only referencing the user object. 
//		Might need to come back to this later in the project
		
//		pointView.setDate()
//		pointView.setPreferredstand(user.getPreferredStand());
//		pointView.setPreferredstand(user.getPreferredStand());
//		pointView.setPreferredstand(user.getPreferredStand());
//		pointView.setPreferredstand(user.getPreferredStand());
//		pointView.setPreferredstand(user.getPreferredStand());
//		
//		
//		
//		for(ccdcUser user: users) {
//			for(ccdcPreferences preference: preferences) {
//				String preferenceMatch = user.getPreferredstand();
//		
//			}
//		}
//		
		
		return rets;
		
	}
	
	
//	Homework #10 backend service
//  HW11 This I feel needs to be reworked its alot of code and if there was one area I could spend more time in this would be it
	public void allocateSeats() {
		
//		referencing my tables as a list from repository
	    List<ccdcUser> fans = ccdcRepository.findUser();
	    List<ccdcInfo> availableSeats = ccdcRepository.findInfo();
	    
	    // Group fans by preferred stand using a handy 'stream' functionality and then collecting and grouping
	    Map<String, List<ccdcUser>> fansByStand = fans.stream()
	            .collect(Collectors.groupingBy(ccdcUser::getPreferredstand));

	    // Allocate seats for each stand
	    for (Map.Entry<String, List<ccdcUser>> entry : fansByStand.entrySet()) {
	        String stand = entry.getKey();
	        List<ccdcUser> standFans = entry.getValue();
	        

	        // Filter available seats for the current stand
	        List<ccdcInfo> standSeats = availableSeats.stream()
	        	    .filter(seat -> seat.getStandposition().equals(stand))
	        	    .collect(Collectors.toList());

	        // Allocation logic, in form of case statement and if preferred stand is N,S,E,W
	        switch (stand) {
	        case "East":
	        	
	            // First come first serve basis for East and West will stay the same, sorted by reservation time
	            standFans.sort(Comparator.comparing(ccdcUser::getReservationtime)); 
	            for (ccdcUser fan : standFans) {
	                if (standSeats.isEmpty()) {
	                    break;
	                }

	                //Assigning the seats
	                ccdcInfo seat = standSeats.remove(0);
	                fan.setSeatNumber(seat.getSeatnumber());
	                ccdcRepository.updateUser(fan);
	            }
	            break;
	        case "West":
	        	// First come first serve basis for East and West will stay the same, sorted by reservation time
	            standFans.sort(Comparator.comparing(ccdcUser::getReservationtime)); // Sort by reservation time
	            for (ccdcUser fan : standFans) {
	                if (standSeats.isEmpty()) {
	                    break;
	                }
	                
	                //Assigning the seats
	                ccdcInfo seat = standSeats.remove(0);
	                fan.setSeatNumber(seat.getSeatnumber());
	                ccdcRepository.updateUser(fan);
	            }
	            break;
	       
	        case "South":
	            // Randomly shuffle fans
	            Collections.shuffle(standFans);

	            // Assign seats to randomly selected fans
	            for (ccdcUser fan : standFans) {
	                if (standSeats.isEmpty()) {
	                    break;
	                }

	                //Fix to make sure same seat isn't assigned twice to two different people..
	                ccdcInfo seat = standSeats.remove(0);
	                fan.setSeatNumber(seat.getSeatnumber());
	                ccdcRepository.updateUser(fan);
	            }
	            break;
	            
	        case "North":
	            standFans.sort(Comparator.comparing(this::getMilitaryPriority).reversed());

	            for (ccdcInfo seat : standSeats) {
	                ccdcUser militaryFan = standFans.stream()
	                        .filter(this::isMilitaryFan)
	                        .findFirst().orElse(null);
	                if (militaryFan != null) {
	                    militaryFan.setSeatNumber(seat.getSeatnumber());
	                    ccdcRepository.updateUser(militaryFan);
	                    standFans.remove(militaryFan);
	                } else {

	                }
	            }
	            break;
	        }
	    }
	}
	    
	
		//Checking if 'firstoccupation' value is equal to MLT
	    private boolean isMilitaryFan(ccdcUser fan) {
	        return "MLT".equals(fan.getFirstoccupation());
	    }
	    
	    //If 'firstoccupation' is equal, assigning it a weight of .8, if not it gets .2 or 20%
	    private double getMilitaryPriority(ccdcUser fan) {
	        return isMilitaryFan(fan) ? 0.8 : 0.2;
	    }
	    
	    
	    
	    
//	    New for hw11 
	    public List<ccdcUser> getAllocatedSeats() {
	        List<ccdcUser> allUsers = ccdcRepository.findUserWithSeatNumber();
	        return allUsers;
	    }

	    
	    
	
	//saveUser function calling both the update and insert calls from the ccdcRepository file
	public void saveUser(List<ccdcUser> users) {
		for(ccdcUser user: users) {
			int count = ccdcRepository.updateUser(user);
			if(count == 0) {
				
				ccdcRepository.insertUser(user);
			}
		}
	}
	


}
