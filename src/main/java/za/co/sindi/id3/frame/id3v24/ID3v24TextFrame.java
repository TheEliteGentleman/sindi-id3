/**
 * 
 */
package za.co.sindi.id3.frame.id3v24;

import java.io.UnsupportedEncodingException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.informed.ID3v2InformationFrame;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public class ID3v24TextFrame extends ID3v24Frame implements ID3v2InformationFrame<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6751503957626106195L;
	private String encoding;
	private String value;
	
	/**
	 * @param id
	 */
	public ID3v24TextFrame(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2Frame#decodeFrameData(byte[])
	 */
	@Override
	public void decodeFrameData(byte[] data) throws ID3Exception {
		// TODO Auto-generated method stub
		byte[] b = null;
		
		if (data == null || data.length != getSize()) {
			throw new ID3Exception(ID3ExceptionStrings.generateInvalidFrameSize(data == null? 0 : data.length, getSize()));
		}
				
		try {
			encoding = getTextEncoding(data[0] & 0xFF);
			if (encoding == null) {
				throw new ID3Exception(ID3ExceptionStrings.generateInvalidEncodingFormat(3, data[0] & 0xFF));
			}

			if (encoding.equals("UCS-2") && !ID3v2Util.isBOMEncoded(data[1], data[2])) {
				throw new ID3Exception(ID3ExceptionStrings.generateInvalidBOMEncoding());
			}
			
			b = new byte[data.length - 1];
			System.arraycopy(data, 1, b, 0, b.length);
			value = new String(b, getEncoding()).trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println(getEncoding() + ": " + getValue());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2TextInformationFrame#getEncoding()
	 */
	public String getEncoding() {
		// TODO Auto-generated method stub
		return encoding.equals("UCS-2") ? "UTF-16" : encoding;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2TextInformationFrame#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
