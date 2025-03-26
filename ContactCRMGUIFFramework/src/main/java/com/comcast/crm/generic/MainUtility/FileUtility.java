package com.comcast.crm.generic.MainUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesfile(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\java full course\\ContactCRMGUIFFramework\\configAppData\\commondata (2).properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data =pobj.getProperty(key);
		
		return data;
	}

	}

