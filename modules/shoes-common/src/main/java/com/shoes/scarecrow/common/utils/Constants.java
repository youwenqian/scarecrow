package com.shoes.scarecrow.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
*	@author wangyc
*	2016
*/
public class Constants {  
	
	public final static String ADMIN_SESSION_USER_KEY = "DBISaaS-UserName";
	
	public final static String ADMIN_SESSION_PASSWORD_KEY = "DBISaaS-Password";
	
	public final static String MOBILE_AREA_CODE = "0086-";
	
	public static String DBISAAS_USERNAME;
	public static String DBISAAS_PASSWORD;
	  
	public static String BASE_PATH;
	
	public static String FILE_PATH;
	
	
	Properties props = new Properties();  
	InputStream inputStream = null;  
	public Constants() {  
	    try {  
	        inputStream = getClass().getResourceAsStream("/configs/properties/dbgo.properties"); 
	        props.load(inputStream);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (inputStream != null) {  
	            try {  
	                inputStream.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    BASE_PATH = props.getProperty("base_path");  
	    DBISAAS_USERNAME = props.getProperty("DBISaaS-UserName");
	    DBISAAS_USERNAME = props.getProperty("DBISaaS-Password");
	    FILE_PATH = props.getProperty("file_path");
	}    

}

