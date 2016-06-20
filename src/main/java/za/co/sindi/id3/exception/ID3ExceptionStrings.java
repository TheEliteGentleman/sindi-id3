/**
 * 
 */
package za.co.sindi.id3.exception;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 * 
 */
public class ID3ExceptionStrings {
	
	private ID3ExceptionStrings() {
		//NOOP
	}

	public static final String generateInvalidFrameSize(int frameSize, int desiredFrameSize) {
		return "Frame data length is not of size " + desiredFrameSize + ". Size = " + frameSize + ".";
	}
	
	public static final String generateInvalidEncodingFormat(int id3MajorVersion, int format) {
		return "ID3v2." + id3MajorVersion + " frame doesn't support encoding of type: 0x" + Integer.toHexString(format & 0xFF);
	}
	
	public static final String generateInvalidBOMEncoding() {
		return "String data isn't Unicode BOM Encoded";
	}
}
