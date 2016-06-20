/**
 * 
 */
package za.co.sindi.id3.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class ID3v2Util {

	/**
	 * This method calculates the Synch Safe integer as per ID3v2.3.0 specification.
	 * 
	 * @return the Synch Safe integer
	 */
	public static long calculateSynchSafeInt(byte[] b) {
		long value  = 0;
		int length = b.length;
		
		for (int i = 0; i < length; i++) {
			value |= ((b[i] & 0x7F) << (7*(length-i-1)));
		}

		return value;
	}
	
	public static boolean isBOMEncoded(byte b1, byte b2) {
		return 	(((b1 & 0xFF) == 0xFF) && ((b2 & 0xFF) == 0xFE)) || 
				(((b1 & 0xFF) == 0xFE) && ((b2 & 0xFF) == 0xFF));
	}
	
	public static boolean isValidID3v22HeaderFrameID(String headerFrame) {
		return isValidHeaderFrameID(headerFrame, 3);
	}
	
	
	public static boolean isValidID3v23HeaderFrameID(String headerFrame) {
		return isValidHeaderFrameID(headerFrame, 4);
	}
	
	private static boolean isValidHeaderFrameID(String headerFrame, int maxLength) {
		String regExp = "^[A-Z]{1}[A-Z0-9]{" + (maxLength - 1)+ "}$";
		
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(headerFrame);
		
		return matcher.matches();
	}
}
