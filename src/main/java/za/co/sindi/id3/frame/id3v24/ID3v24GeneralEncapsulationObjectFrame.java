/**
 * 
 */
package za.co.sindi.id3.frame.id3v24;

import java.io.UnsupportedEncodingException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.informed.ID3v2GeneralEncapsulationObjectFrame;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v24GeneralEncapsulationObjectFrame extends ID3v24Frame implements ID3v2GeneralEncapsulationObjectFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6823613395740532681L;
	private String encoding;
	private String mimeType;
	private String fileName;
	private String description;
	private byte[] object;
	
	/**
	 * @param id
	 */
	public ID3v24GeneralEncapsulationObjectFrame(String id) {
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
			
			//MiME Type
			int nullPos = 1;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			
			b = new byte[nullPos];
			System.arraycopy(data, 1, b, 0, b.length);
			mimeType = new String(b, getEncoding()).trim();

			//file Name
			int startPosDesc = ++nullPos;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			b = new byte[nullPos - startPosDesc];
			System.arraycopy(data, startPosDesc, b, 0, b.length);
			fileName = new String(b, getEncoding()).trim();
			
			//Description
			startPosDesc = ++nullPos;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			b = new byte[nullPos - startPosDesc];
			System.arraycopy(data, startPosDesc, b, 0, b.length);
			description = new String(b, getEncoding()).trim();
			
			//Object
			object = new byte[data.length - nullPos - 1];
			System.arraycopy(data, nullPos + 1, object, 0, object.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println("MIME-Type: " + getMIMEType());
//		System.out.println("FileName: " + getFileName());
//		System.out.println("Description: " + getDescription());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2GeneralEncapsulationObjectFrame#getFileName()
	 */
	public String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2GeneralEncapsulationObjectFrame#getMIMEType()
	 */
	public String getMIMEType() {
		// TODO Auto-generated method stub
		return mimeType;
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
	public byte[] getValue() {
		// TODO Auto-generated method stub
		return object;
	}
}
