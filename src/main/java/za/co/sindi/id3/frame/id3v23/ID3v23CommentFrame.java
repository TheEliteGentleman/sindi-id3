/**
 * 
 */
package za.co.sindi.id3.frame.id3v23;

import java.io.UnsupportedEncodingException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v23Frame;
import za.co.sindi.id3.frame.informed.ID3v2CommentFrame;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v23CommentFrame extends ID3v23Frame implements ID3v2CommentFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2172938819674810371L;
	private String encoding;
	private String language;
	private String description;
	private String comment;
	
	/**
	 * @param id
	 */
	public ID3v23CommentFrame(String id) {
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
			
			//Language
			b = new byte[3];
			System.arraycopy(data, 1, b, 0, b.length);
			language = new String(b);
			
			int nullPos = 4;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			
			b = new byte[nullPos];
			System.arraycopy(data, 1, b, 0, b.length);
			description = new String(b, getEncoding()).trim();
			
			b = new byte[data.length - nullPos - 1];
			System.arraycopy(data, nullPos + 1, b, 0, b.length);
			comment = new String(b, getEncoding()).trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println("Encoding: " + getEncoding());
//		System.out.println("Language: " + getLanguage());
//		System.out.println("Description: " + getDescription());
//		System.out.println("Comment: " + getValue());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.id3v23.ID3v2CommentFrame#getLanguage()
	 */
	public String getLanguage() {
		// TODO Auto-generated method stub
		return language;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2DescriptiveInformationFrame#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2InformationFrame#getEncoding()
	 */
	public String getEncoding() {
		// TODO Auto-generated method stub
		return encoding;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2InformationFrame#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return comment;
	}
}
