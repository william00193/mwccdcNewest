package com.itccloud.mwccdc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ccdcRepository {
	
	@Select("SELECT * FROM customerinfo")
	@Results(id="userResult", value={
			@Result(column="firstname", property="firstname"),
			@Result(column="lastname", property="lastname"),
			@Result(column="email", property="email"),
			@Result(column="phone", property="phone"),
			@Result(column="firstoccupation", property="firstoccupation"),
			@Result(column="preferredstand", property="preferredstand"),
			@Result(column="reservationtime", property="reservationtime")}
	)
	public List<ccdcUser> findUser();
	
	
	//Update and insert statement, This is an awesome library! 
	@Update("UPDATE customerinfo set firstname=#{firstname}, lastname=#{lastname}, email=#{email}, phone=#{phone}, firstoccupation=#{firstoccupation},preferredstand=#{preferredstand}, reservationtime=now()"
			+ "WHERE firstname=#{firstname} AND lastname=#{lastname}")
	public int updateUser(ccdcUser user);
	
	
	//More DB action statements
	@Insert("INSERT into customerinfo(firstname,lastname,email,phone,firstoccupation,preferredstand,reservationtime)"
			+ "VALUES(#{firstname},#{lastname},#{email},#{phone},#{firstoccupation},#{preferredstand},now())")
	public int insertUser(ccdcUser user);

	
	
	
	
	
	


	@Select("WITH UserRows AS (" +
	        "SELECT *, ROW_NUMBER() OVER (PARTITION BY firstName, "
	        + "lastName ORDER BY reservationtime) AS row_num " +
	        "FROM customerinfo ci " +
	        "LEFT JOIN standinfo sa ON ci.preferredstand = sa.standposition " +
	        ") " +
	        "SELECT * FROM UserRows WHERE row_num = 1")
	@Results(id="userResultWithSeat", value={
	        
	        @Result(column="seatNumber", property="seatNumber")
	})
	public List<ccdcUser> findUserWithSeatNumber();
	
	
	
	
	
	
	
	
	
	//My other two tables which I will come back to later..
	
	@Select("SELECT * FROM preferences")
	@Results(id="preferencesResult", value={
			@Result(column="standposition", property="standposition"),
			@Result(column="numberseats", property="numberseats"),
			@Result(column="numberpreferred", property="numberpreferred"),
			@Result(column="discountprice", property="discountprice"),
			@Result(column="totalearnings", property="totalearnings")}
	)
	public List<ccdcPreferences> findPreferences();
	
	
	
	@Select("SELECT * FROM standinfo")
	@Results(id="infoResult", value={
			@Result(column="positionid", property="positionid"),
			@Result(column="standposition", property="standposition"),
			@Result(column="seatnumber", property="seatnumber")}
	)
	public List<ccdcInfo> findInfo();
}
