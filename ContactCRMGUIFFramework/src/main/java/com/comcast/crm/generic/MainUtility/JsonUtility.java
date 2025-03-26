package com.comcast.crm.generic.MainUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getdataFromJsonFile(String key) throws ParseException, IOException {
		FileReader fileR = new FileReader("./configAppData/AppCommonData.json");
		
		JSONParser parser = new JSONParser();
		Object obj=parser.parse(fileR);
		
		
		JSONObject map=(JSONObject)obj;
		String data = (String) map.get(key);
		return data;
	    
	}

}
