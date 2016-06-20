/**
 * 
 */
package za.co.sindi.id3.reader;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import za.co.sindi.id3.ID3Tag;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public abstract class AbstractID3Reader<T extends ID3Tag> implements ID3Reader<T> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	private InputStream inputStream;
	private int length = 0;
	
	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public AbstractID3Reader(File file) throws IOException {
		this(new BufferedInputStream(new FileInputStream(file)), (int) file.length());
	}
	
	/**
	 * @param inputStream
	 */
	public AbstractID3Reader(InputStream inputStream, int length) {
		this.inputStream = inputStream;
		this.length = length;
	}
	
	/**
	 * 
	 * @param data
	 */
	public AbstractID3Reader(byte[] data) {
		this(new ByteArrayInputStream(data), data.length);
	}
	
	public void close() throws IOException {
		if (inputStream != null) {
			inputStream.close();
		}
		
		inputStream = null;
	}

	/**
	 * @return the inputStream
	 */
	protected final InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @return the length
	 */
	protected final int getLength() {
		return length;
	}
}
