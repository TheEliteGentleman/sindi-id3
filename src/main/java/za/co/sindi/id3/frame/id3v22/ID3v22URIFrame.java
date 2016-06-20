/**
 * 
 */
package za.co.sindi.id3.frame.id3v22;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v22Frame;
import za.co.sindi.id3.frame.informed.ID3v2InformationFrame;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public class ID3v22URIFrame extends ID3v22Frame implements	ID3v2InformationFrame<URI> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4854832318163659240L;
	private static final Logger logger = Logger.getLogger(ID3v22URIFrame.class);
	private String url;
	
	/**
	 * @param id
	 */
	public ID3v22URIFrame(String id) {
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
			int nullPos = 0;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			
			b = new byte[nullPos];
			System.arraycopy(data, 0, b, 0, b.length);
			url = new String(b, getEncoding()).trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println("URL: " + url);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2InformationFrame#getEncoding()
	 */
	public String getEncoding() {
		// TODO Auto-generated method stub
		return "ISO-8859-1";
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
