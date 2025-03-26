package com.comcast.crm.generic.MainUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	 public int getRandomNumber() {
		 Random random =new Random();
		int randomNumber= random.nextInt();
		return randomNumber;
		 }
	 
	 // for current date
	 public String getSystemDate() { 
		 
		 Date dateObj = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMm-dd");
		 String date =sdf.format(dateObj);
		 return date;
	 }
	 // for required date
	 public String gerequiredDate(int days) {
		 SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		 
		 Calendar cal=sim.getCalendar();
		 cal.add(Calendar.DAY_OF_MONTH,days);
		 String reqDate = sim.format(cal.getTime());
		 return reqDate;
	 }

}
