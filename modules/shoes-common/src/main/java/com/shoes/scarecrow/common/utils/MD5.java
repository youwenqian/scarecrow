package com.shoes.scarecrow.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
*	@author wangyc
*	2016
*/
public class MD5 {
	
	 /** 
    * 获取该输入流的MD5值 
    *  
    * @param is 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {  
       StringBuffer md5 = new StringBuffer();  
       MessageDigest md = MessageDigest.getInstance("MD5");  
       byte[] dataBytes = new byte[1024];  
         
       int nread = 0;   
       while ((nread = is.read(dataBytes)) != -1) {  
           md.update(dataBytes, 0, nread);  
       };  
       byte[] mdbytes = md.digest();  
         
       // convert the byte to hex format  
       for (int i = 0; i < mdbytes.length; i++) {  
           md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));  
       }  
       return md5.toString();  
   }  
     
   /** 
    * 获取该文件的MD5值 
    *  
    * @param file 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static String getMD5(File file) throws NoSuchAlgorithmException, IOException {  
       FileInputStream fis = new FileInputStream(file);  
       return getMD5(fis);  
   }  
     
   /** 
    * 获取指定路径文件的MD5值 
    *  
    * @param path 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static String getMD5(String path) throws NoSuchAlgorithmException, IOException {  
       FileInputStream fis = new FileInputStream(path);  
       return getMD5(fis);  
   }  
 
   /** 
    * 校验该输入流的MD5值 
    *  
    * @param is 
    * @param toBeCheckSum 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static boolean md5CheckSum(InputStream is, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
       return getMD5(is).equals(toBeCheckSum);  
   }  
     
   /** 
    * 校验该文件的MD5值 
    *  
    * @param file 
    * @param toBeCheckSum 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static boolean md5CheckSum(File file, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
       return getMD5(file).equals(toBeCheckSum);  
   }  
     
   /** 
    * 校验指定路径文件的MD5值 
    *  
    * @param path 
    * @param toBeCheckSum 
    * @return 
    * @throws NoSuchAlgorithmException 
    * @throws IOException 
    */  
   public static boolean md5CheckSum(String path, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
       return getMD5(path).equals(toBeCheckSum);  
   }  
	
	public static String excute(String AStr) {
		MessageDigest algorithm;
		byte[] messageDigest;
		// StringBuffer hexString;
		try {
			algorithm = MessageDigest.getInstance("MD5");

			algorithm.reset();
			try {
				algorithm.update(AStr.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
			messageDigest = algorithm.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		return ToHex(messageDigest).toUpperCase();

	}

	public static String excute(byte[] ABytes) {
		MessageDigest algorithm;
		byte[] messageDigest;
		// StringBuffer hexString;
		try {
			algorithm = MessageDigest.getInstance("MD5");

			algorithm.reset();

			algorithm.update(ABytes);

			messageDigest = algorithm.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		return ToHex(messageDigest);

	}

	public static String ToHex(byte[] ABin) {
		StringBuffer hexString = new StringBuffer();
		String sTmp;
		for (int i = 0; i < ABin.length; i++) {
			sTmp = Integer.toHexString(0xFF & ABin[i]);
			sTmp = sTmp.trim();
			if (sTmp.length() == 1) {
				sTmp = "0" + sTmp;
			}
			hexString.append(sTmp);
		}
		return hexString.toString();
	}

	public static byte[] ToBin(String AHex) {
		// int m=0,n=0;
		int l = AHex.length() / 2;
		int c;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			// m=i*2+1;
			// n=m+1;
			c = Integer.decode("0x" + AHex.substring(i * 2, i * 2 + 2));
			ret[i] = (byte) c;
		}
		// AHex.su
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(CodeCreatorUtil.GetGuid());
		//Jane
		System.out.println(excute("admin"));
	}
	  
}

