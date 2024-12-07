package com.itccloud.mwccdc.controller;


//Before changing data types and adding in calculation condition
//public class personObject {
//
//	
//	public personObject(String StandPosition, String AvailSeats, String PreferredSeats, String DiscountPrice, String EstimatedTotalEarnings) {
//		super();
//	this.StandPosition = StandPosition;
//	this.AvailSeats = AvailSeats;
//	this.PreferredSeats = PreferredSeats;
//		this.DiscountPrice = DiscountPrice;
//	this.EstimatedTotalEarnings = EstimatedTotalEarnings;
//	}
//	
//	private String StandPosition;
//	private String AvailSeats;
//	private String PreferredSeats;
//	private String DiscountPrice;
//	private String EstimatedTotalEarnings;


public class personObject {
    private String StandPosition;
    private int AvailSeats;
    private int PreferredSeats;
    private double DiscountPrice;
    private double EstimatedTotalEarnings;

    
    //object constructor and referencing attributes contained with this.
    public personObject(String StandPosition, int AvailSeats, int PreferredSeats, double DiscountPrice) {
    	super();
        this.StandPosition = StandPosition;
        this.AvailSeats = AvailSeats;
        this.PreferredSeats = PreferredSeats;
        this.DiscountPrice = DiscountPrice;
        
        //If 5th column is present in csv were turning everything into a 0.0 and a type of double, if empty were adding a new column 
        this.EstimatedTotalEarnings = 0.0; 
    }

    
  //Small condition that will determine whether the number of preferred seats is greater than the number of available seats
    public void calculateEstimatedTotalEarnings() {
        if (PreferredSeats > AvailSeats) {
        	//if true, number of available seats is multiplied by the discount price
        	EstimatedTotalEarnings = AvailSeats * DiscountPrice;
        } else {
        	//if false preferred seats are multiplied by this discount price
        	EstimatedTotalEarnings = PreferredSeats * DiscountPrice;
        }
    }
    
    
    
    
  
	
	
	
	public String getStandPosition() {
		return StandPosition;
	}
	public void setStandPosition(String StandPosition) {
		this.StandPosition = StandPosition;
	}
	
	
	//Had to change out the data types for each of my getters and setters to match new convention
	
	public int getAvailSeats() {
		return AvailSeats;
	}
	public void setAvailSeats(int AvailSeats) {
		this.AvailSeats = AvailSeats;
	}
	
	
	public int getPreferredSeats() {
		return PreferredSeats;
	}
	public void setPreferredSeats(int PreferredSeats) {
		this.PreferredSeats = PreferredSeats;
	}
	
	
	public double getDiscountPrice() {
		return DiscountPrice;
	}
	
	public void setDiscountPrice(double DiscountPrice) {
		this.DiscountPrice = DiscountPrice;
	}
	
	
	public double getEstimatedTotalEarnings() {
		return EstimatedTotalEarnings;
	}
	public void setEstimatedTotalEarnings(double EstimatedTotalEarnings) {
		this.EstimatedTotalEarnings = EstimatedTotalEarnings;
	}
	

	//Keeping this pretty much the same as before, just adding a few new attributes
	@Override
	public String toString() {
		return "personObject [StandPosition=" + StandPosition + ", AvailSeats=" + AvailSeats + ", PreferredSeats=" + PreferredSeats + ",DiscountPrice=" + DiscountPrice + ", EstimatedTotalEarnings="
				+ EstimatedTotalEarnings + "]";
	}
	
}	
	

