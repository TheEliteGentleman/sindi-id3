/**
 * 
 */
package za.co.sindi.id3.frame.id3v24;

import java.io.UnsupportedEncodingException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.exception.ID3ExceptionStrings;
import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.informed.ID3v2PictureFrame;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v24PictureFrame extends ID3v24Frame implements ID3v2PictureFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441214929001483468L;
	private String encoding;
	private String mimeType;
	private int pictureType;
	private String description;
	private byte[] pictureData;
	
	/**
	 * @param id
	 */
	public ID3v24PictureFrame(String id) {
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
			if (mimeType == null || mimeType.isEmpty()) {
				mimeType = "image/";
			}
			
			//Picture Type
			pictureType = data[++nullPos] & 0xFF;
			
			//Description
			int startPosDesc = ++nullPos;
			while (nullPos < data.length) {
				if (data[nullPos] == 0) {
					break;
				}
				
				nullPos++;
			}
			b = new byte[nullPos - startPosDesc];
			System.arraycopy(data, startPosDesc, b, 0, b.length);
			description = new String(b, getEncoding()).trim();
			
			//Picture Data
			pictureData = new byte[data.length - nullPos - 1];
			System.arraycopy(data, nullPos + 1, pictureData, 0, pictureData.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} finally {
			//Blank data
			b = null;
		}
		
//		System.out.println("ID: " + getId());
//		System.out.println("MIME-Type: " + getMIMEType());
//		System.out.println("Picture Type: " + getPictureType(getPictureType()));
//		System.out.println("Description: " + getDescription());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2PictureFrame#getMIMEType()
	 */
	public String getMIMEType() {
		// TODO Auto-generated method stub
		return mimeType;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2PictureFrame#getPictureType()
	 */
	public int getPictureType() {
		// TODO Auto-generated method stub
		return pictureType;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2PictureFrame#getPictureType(int)
	 */
	public String getPictureType(int type) {
		// TODO Auto-generated method stub
		switch(type) {
			case 0x00:
				return "Other";
			
			case 0x01:
				return "32x32 pixels 'file icon'";
				
			case 0x02:
				return "Other file icon";
			
			case 0x03:
				return "Cover (front)";
				
			case 0x04:
				return "Cover (back)";
				
			case 0x05:
				return "Leaflet page";
			
			case 0x06:
				return "Media (e.g. label side of CD)";
						
			case 0x07:
				return "Lead Artist/Lead Performer/Soloist";
				
			case 0x08:
				return "Artist/Performer";
				
			case 0x09:
				return "Conductor";
			
			case 0x0A:
				return "Band/Orchestra";
			
			case 0x0B:
				return "Composer";
			
			case 0x0C:
				return "Lyricist/Text Writer";
			
			case 0x0D:
				return "Recording Location";
			
			case 0x0E:
				return "During Recording";
			
			case 0x0F:
				return "During Performance";
			
			case 0x10:
				return "Movie/Video Screen Capture";
			
			case 0x11:
				return "A bright coloured fish";
			
			case 0x12:
				return "Illustration";
			
			case 0x13:
				return "Band/Artist Logotype";
			
			case 0x14:
				return "Publisher/Studio Logotype";
			
			default: return null;
		}
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
		return pictureData;
	}
}
