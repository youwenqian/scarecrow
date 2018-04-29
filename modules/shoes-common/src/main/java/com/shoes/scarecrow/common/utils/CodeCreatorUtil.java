package com.shoes.scarecrow.common.utils;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class CodeCreatorUtil {
	private static final int PAD_BELOW = 0x10;
	private static final int TWO_BYTES = 0xFF;
	// private static String valueAfterMD5 = "";
	/*
	 * private static String valueBeforeMD5 = "";
	 * 
	 * private static Random myRand = null; private static SecureRandom
	 * mySecureRand = null; private static String s_id = "";
	 * 
	 * 
	 * 
	 * static { mySecureRand = new SecureRandom(); long secureInitializer =
	 * mySecureRand.nextLong(); myRand = new Random(secureInitializer);
	 * System.out.println("11111");
	 * 
	 * try { s_id = InetAddress.getLocalHost().toString(); } catch
	 * (UnknownHostException e) { e.printStackTrace(); TGetConfig
	 * tc=TGetConfig.getInstance(); s_id=tc.getProperties("guid.ip"); } }
	 */

	/*
	 * public GuidCreator() { getRandomGUID(false); }
	 * 
	 * 
	 * public GuidCreator(boolean secure) { getRandomGUID(secure); }
	 */
	public static String GetGuid() {

		return toString1(getRandomGUID(true));
	}

	/*
	 * public static String GetGuid() throws TCardException { valueAfterMD5 ="";
	 * getRandomGUID(true); if(valueAfterMD5.length()<=0) throw new
	 * TCardException(); else
	 * 
	 * return toString1(); }
	 */
	private static String getRandomGUID(boolean secure) {
		// Random myRand = null;
		SecureRandom mySecureRand = null;
		String valueAfterMD5 = "";
		String valueBeforeMD5 = "";
		// byte bRand= new byte[128];
		mySecureRand = new SecureRandom();
		// long secureInitializer = mySecureRand.nextLong();
		// myRand = new Random(secureInitializer);
		String s_id = "";
		/*
		 * try { s_id = InetAddress.getLocalHost().toString(); } catch
		 * (UnknownHostException e) { e.printStackTrace(); TGetConfig
		 * tc=TGetConfig.getInstance(); s_id=tc.getProperties("guid.ip"); }
		 */
		// TGetConfig tc = TGetConfig.getInstance();
		// s_id=tc.getProperties("guid.ip");
		s_id = "192.168.1.122";
		valueAfterMD5 = "";
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer(128);

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			valueAfterMD5 = "";
			return valueAfterMD5;
		}

		try {
			long time = System.currentTimeMillis();
			long rand = mySecureRand.nextLong();// secure ?
												// mySecureRand.nextLong() :
												// myRand.nextLong();
			long lThreadID = Thread.currentThread().getId();

			mySecureRand.setSeed(rand);
			rand = mySecureRand.nextLong();// secure ? mySecureRand.nextLong() :
											// myRand.nextLong();
			mySecureRand.setSeed(rand);
			rand = mySecureRand.nextLong();// secure ? mySecureRand.nextLong() :
											// myRand.nextLong();
			sbValueBeforeMD5.append(s_id);
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(lThreadID));
			valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());

			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer(32);
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & TWO_BYTES;
				if (b < PAD_BELOW) {
					sb.append('0');
				}

				sb.append(Integer.toHexString(b));
			}

			valueAfterMD5 = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			valueAfterMD5 = "";
			return valueAfterMD5;
		}
		return valueAfterMD5;
	}

	private static String toString1(String valueAfterMD5) {
		String raw = valueAfterMD5.toUpperCase();
		raw = raw.trim();
		if (raw.length() <= 0)
			return "";
		StringBuffer sb = new StringBuffer(64);
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));

		return sb.toString();
	}

	public static String GetCorpCode(String AParentCode) {
		String sResult1 = "0000000000000000000000000000000000000000000000000000000000000";
		String sSubCode = GetCorpCode();
		sResult1 = AParentCode + sResult1;

		String sResult = sResult1.replaceFirst("000000", sSubCode);

		return sResult.substring(0, 24);

	}

	private static String GetCorpCode() {
		String SBase = "123456789ABCDEFGHIJKLMNOPQRSTUVWXY";
		String sResult = "";
		int iIndex;
		SecureRandom CodeRand;
		CodeRand = new SecureRandom();
		for (int i = 0; i < 6; i++) {
			iIndex = CodeRand.nextInt(34);
			sResult = sResult + SBase.substring(iIndex, iIndex + 1);
		}

		return sResult;
	}

	public static String GetAuthCode(int ACount) {

		String SBase = "1234567890";
		String sResult = "";
		int iIndex;
		SecureRandom CodeRand;
		CodeRand = new SecureRandom();
		for (int i = 0; i < ACount; i++) {
			iIndex = CodeRand.nextInt(10);
			sResult = sResult + SBase.substring(iIndex, iIndex + 1);
		}
		return sResult;
	}

	public static String GetCardAuthCode(int ACount, boolean AIsZM) {

		String SBase = "1234567890abcdefghijklmnopqrstwuvxyzABCDEFGHIJKLMNOPQRSTWUVXYZ";
		String sResult = "";
		int iLen = 10;
		if (AIsZM)
			iLen = 62;
		int iIndex;
		SecureRandom CodeRand;
		CodeRand = new SecureRandom();
		for (int i = 0; i < ACount; i++) {
			iIndex = CodeRand.nextInt(iLen);
			sResult = sResult + SBase.substring(iIndex, iIndex + 1);
		}
		return sResult;
	}

}
