/**
 * 
 */
package za.co.sindi.id3.frame.id3v24;

import java.io.UnsupportedEncodingException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.informed.ID3v2DescriptiveInformationFrame;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public class ID3v24PrivateFrame extends ID3v24Frame implements ID3v2DescriptiveInformationFrame<byte[]> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1993435152030127428L;
	private String ownerInformation;
	private byte[] privateData = null;
	
	/**
	 * @param id
	 */
	public ID3v24PrivateFrame(String id) {
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
			System.arraycopy(data, 0, b, 0, nullPos);
			ownerInformation = new String(b, getEncoding()).trim();
			
			if (nullPos < data.length - 1) {
				privateData = new byte[data.length - nullPos - 1];
				System.arraycopy(data, nullPos + 1, privateData, 0, privateData.length);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2InformationFrame#getEncoding()
	 */
	public String getEncoding() {
		// TODO Auto-generated method stub
		return super.getTextEncoding(0);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2UserDefinedInformationFrame#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return ownerInformation;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2InformationFrame#getValue()
	 */
	public byte[] getValue() {
		// TODO Auto-generated method stub
		return privateData;
	}
}
