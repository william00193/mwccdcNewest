package com.itccloud.mwccdc.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class csvFileHandling {
	
	public static void main(String[] args) throws IOException {
		
		
		readCSV();
		
		return;
		
	}

	static List<personObject> readCSV() throws FileNotFoundException, IOException {
		
		List<personObject> ret = new ArrayList<personObject>();
		String root = "/Users/texteditor/Desktop/";
		String filePath = root + "summary.csv";
		
		FileReader reader = new FileReader(filePath);
		BufferedReader bReader = new BufferedReader(reader);
		
		String line = "";
		while((line = bReader.readLine()) != null) {
			
			String[] split = line.split(",");
	
			//Specifically parsing each of the string variables back into preferred data type
			
			 int availableSeats = Integer.parseInt(split[1]);
		     int preferredSeats = Integer.parseInt(split[2]);
		     double discountPrice = Double.parseDouble(split[3]);

		        personObject person = new personObject(split[0], availableSeats, preferredSeats, discountPrice);

		        // Calling Calculation function 
		        person.calculateEstimatedTotalEarnings();

		        ret.add(person);
		        System.out.println(person);

		}
	
		bReader.close();
		return ret;
		
	}

}