/**
 * 
 */
package za.co.sindi.id3.constants;

/**
 * @author Bienfait Sindi
 * @since 05 December 2012
 * 
 */
public class ID3v2Constants {

	/**
	 * 
	 */
	private ID3v2Constants() {
		// TODO Auto-generated constructor stub
	}

	public static final String HEADER_START = "ID3";
	public static final String HEADER_END = "3DI";
	
	/**
	 * Unsynchronisation flag.
	 */
	public static final int UNSYNCHRONISATION_FLAG = 0x80;
}
