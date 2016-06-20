/**
 * 
 */
package za.co.sindi.id3.reader.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.id3.builder.ID3v2Builder;
import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.factory.ID3v2BuilderFactoryImpl;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.reader.AbstractID3Reader;
import za.co.sindi.id3.tag.ID3v2Tag;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class ID3v2Reader extends AbstractID3Reader<ID3v2Tag<? extends ID3v2Frame>> {

	/**
	 * @param file
	 * @throws IOException
	 */
	public ID3v2Reader(File file) throws IOException {
		super(file);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param inputStream
	 * @param length
	 */
	public ID3v2Reader(InputStream inputStream, int length) {
		super(inputStream, length);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 */
	public ID3v2Reader(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.reader.ID3Reader#read()
	 */
	public ID3v2Tag<? extends ID3v2Frame> read() throws ID3Exception {
		// TODO Auto-generated method stub
		try {
			if (getInputStream() == null) {
				throw new IOException("No data provided.");
			}
			
			//ID3 header declaration;
			byte[] id3Chunk = new byte[3];
			int pos = 0;
			boolean flagFound = false;
			
			while (getInputStream().available() > 0) {
				getInputStream().skip(pos);
				
				if (getInputStream().markSupported()) {
					getInputStream().mark(-1);
				}
				
				getInputStream().read(id3Chunk);
				if ((id3Chunk[0] & 0xFF) == 'I' && (id3Chunk[1] & 0xFF) == 'D' && (id3Chunk[2] & 0xFF) == '3') {
					flagFound = true;
					break;
				}
				
				if (getInputStream().markSupported()) {
					getInputStream().reset();
				}
				
				pos++;
			}
			
			if (!flagFound) {
				return null;
			}
			
			if (logger.isInfoEnabled())
				logger.info("Found ID3 Header at position " + pos);
			
			//Get the builder
			int majorNumber = getInputStream().read();
			int revisionNumber = getInputStream().read();
			
			//According to the specification, ID3 tags (major and revision) must NEVER reach $FF
			if (majorNumber == 0xFF || revisionNumber == 0xFF) {
				throw new ID3Exception("ID3 Major/Revision number(s) is/are invalid.");
			}
			
			ID3v2Builder<? extends ID3v2Tag<? extends ID3v2Frame>> builder = new ID3v2BuilderFactoryImpl().createID3v2Builder(majorNumber);
			if (builder == null) {
				throw new IOException("ID3v2." + majorNumber + "." + revisionNumber + " not implemented.");
			}
			
			builder.getId3v2Tag().setHeaderPosition(pos);
			builder.getId3v2Tag().setRevisionNumber(revisionNumber);
			builder.assignID3v2Flags(getInputStream().read());
			
			//Set ID3v2 Size
			id3Chunk = new byte[4];
			getInputStream().read(id3Chunk);
			int id3v2Size = (int) ID3v2Util.calculateSynchSafeInt(id3Chunk);
			builder.getId3v2Tag().setSize(id3v2Size);
			
			//Now, this function does everything, including finalization.
			builder.extractID3v2Frames(getInputStream());
			
			return builder.getId3v2Tag();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getLocalizedMessage(), e);
			throw new ID3Exception(e.getLocalizedMessage(), e);
		} catch (ID3Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getLocalizedMessage(), e);
			throw e;
		} finally {
			try {
				close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Closing failed.\n" + e.getLocalizedMessage());
			}
		}
	}
}
