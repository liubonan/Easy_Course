package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String getMd5(String input)
	{
		char[] charArray = input.toCharArray();

	    byte[] byteArray = new byte[charArray.length];

	    for (int i=0; i<charArray.length; i++)
	       byteArray[i] = (byte) charArray[i];

	    byte[] md5Bytes=null;
		try {
			md5Bytes = MessageDigest.getInstance("MD5").digest(byteArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	        StringBuffer hexValue = new StringBuffer();

	    for (int i=0; i<md5Bytes.length; i++)
	    {
	       int val = ((int) md5Bytes[i] ) & 0xff; 
	       if (val < 16) hexValue.append("0");
	       hexValue.append(Integer.toHexString(val));
	    }

	    return hexValue.toString().toLowerCase();
	}

}
