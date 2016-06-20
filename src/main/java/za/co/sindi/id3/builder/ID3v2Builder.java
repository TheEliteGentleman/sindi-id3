/**
 * 
 */
package za.co.sindi.id3.builder;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.register.ID3v2FrameRegister;
import za.co.sindi.id3.tag.ID3v2Tag;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public abstract class ID3v2Builder<T extends ID3v2Tag<? extends ID3v2Frame>> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	protected T id3v2Tag;
	protected ID3v2FrameRegister<? extends ID3v2Frame> frameRegister;

	/**
	 * @return the id3v2Tag
	 */
	public T getId3v2Tag() {
		return id3v2Tag;
	}
	
	protected int readInt(InputStream in) throws IOException {
		int length = 4;
		byte[] b = new byte[length];
		int value = 0;
		
		in.read(b);
		for (int i = 0; i < length; i ++) {
			value |= ((b[i] & 0xFF) << (8*(length-i-1)));
		}
		
		return value;
	}
	
	/**
	 * Read the total amount of padding set on the ID3v2.X.X frame
	 * 
	 * @param in MPEG <code>InputStream</code> 
	 * @return the total number of bytes used for padding.
	 * @throws IOException
	 */
	protected int readPadding(InputStream in) throws IOException {
		int totalPaddingBytes = 0;
		
		while (true) {
			if (in.markSupported()) {
				in.mark(-1);
			}
			
			int padding = readInt(in);
			if (padding != 0)
				break;
			
			totalPaddingBytes++;
		}
		
		if (in.markSupported()) {
			in.reset();
		}
		
		return totalPaddingBytes;
	}
	
	public abstract void assignID3v2Flags(int id3v2Flags);
	public abstract void extractID3v2Frames(InputStream in) throws ID3Exception;
	protected abstract int readFrames(InputStream in) throws IOException, ID3Exception;
}
