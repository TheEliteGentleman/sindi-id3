/**
 * 
 */
package za.co.sindi.id3.reader.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.id3.constants.ID3v1Constants;
import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.reader.AbstractID3Reader;
import za.co.sindi.id3.tag.ID3v1Tag;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class ID3v1Reader extends AbstractID3Reader<ID3v1Tag> {

	/**
	 * @param data
	 */
	public ID3v1Reader(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	public ID3v1Reader(File file) throws IOException {
		super(file);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param inputStream
	 * @param length
	 */
	public ID3v1Reader(InputStream inputStream, int length) {
		super(inputStream, length);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.reader.ID3Reader#read()
	 */
	public ID3v1Tag read() throws ID3Exception {
		// TODO Auto-generated method stub
		ID3v1Tag tag = null;
		
		try {
			if (getInputStream() == null) {
				throw new IOException("No data provided.");
			}
			
			if (getLength() < ID3v1Constants.TAG_SIZE) {
				throw new IOException("Data length of " + getLength() + " bytes is less than the required " + ID3v1Constants.TAG_SIZE + " needed to read ID3v1 data structure.");
			}
			
			// Read the ID3v1 Tag
			byte[] id3v1 = new byte[128];
			int bitPos = getLength() - ID3v1Constants.TAG_SIZE;
			
			//Skip bitPos bytes...
			getInputStream().skip(bitPos);
			getInputStream().read(id3v1);
						
			if ((id3v1[0] & 0xFF) != 'T' && (id3v1[1] & 0xFF) != 'A' && (id3v1[2] & 0xFF) != 'G') {
				throw new IOException("ID3v1 tag not found.");
			}
			
			//Initialize
			tag = new ID3v1Tag();
			tag.setHeaderPosition(bitPos);
			
			byte[] data = new byte[30]; //temp bytes;
			
			//Set Title
			System.arraycopy(id3v1, 3, data, 0, 30);
			tag.setTitle(new String(data).trim());
			
			//Set Artist
			System.arraycopy(id3v1, 33, data, 0, 30);
			tag.setArtist(new String(data).trim());
			
			//Set Album
			System.arraycopy(id3v1, 63, data, 0, 30);
			tag.setAlbum(new String(data).trim());
			
			//Set year
			byte[] year = new byte[4];
			System.arraycopy(id3v1, 93, year, 0, 4);
			tag.setYear(new String(year).trim());
			
			//Set Comment
			System.arraycopy(id3v1, 97, data, 0, 30);
			tag.setComment(new String(data).trim());
			
			if (tag.getComment().length() < 29) {
				tag.setTrackNumber(((id3v1[125] & 0xFF) << 4) | (id3v1[126] & 0xFF));
			}
			
			//Set Genre
			tag.setGenre(id3v1[127] & 0xFF);
			
			//Done...clear buffer;
			data = null;
			year = null;
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
			throw new ID3Exception(e.getMessage(), e);
		} finally {
			// TODO Auto-generated catch block
			try {
				close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Closing failed: \n" + e.getLocalizedMessage());
			}
		}
		
		return tag;
	}
}
