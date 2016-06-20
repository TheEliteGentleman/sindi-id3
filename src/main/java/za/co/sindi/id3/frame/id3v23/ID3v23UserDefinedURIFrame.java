/**
 * 
 */
package za.co.sindi.id3.frame.id3v23;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v23Frame;
import za.co.sindi.id3.frame.informed.ID3v2DescriptiveInformationFrame;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public class ID3v23UserDefinedURIFrame extends ID3v23Frame implements ID3v2DescriptiveInformationFrame<URI> {

	private static final Logger logger = Logger.getLogger(ID3v23UserDefinedURIFrame.class);
	private String encoding;
	private String description;
	private String url;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257968619487872780L;

	/**
	 * @param id
	 */
	public ID3v23UserDefinedURIFrame(String id) {
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
			
			int nullPos = 1;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			
			b = new byte[nullPos];
			System.arraycopy(data, 1, b, 0, b.length);
			description = new String(b, getEncoding()).trim();
			
			if (nullPos < data.length - 1) {
				b = new byte[getSize() - nullPos - 1];
				System.arraycopy(data, nullPos + 1, b, 0, b.length);
				url = new String(b, getTextEncoding(0)).trim();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println("Description: " + getDescription());
//		System.out.println("URL: " + url);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2UserDefinedInformationFrame#getDescription()
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
	public URI getValue() {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			logger.error(e.getLocalizedMessage());
			return null;
		}
	}
}
