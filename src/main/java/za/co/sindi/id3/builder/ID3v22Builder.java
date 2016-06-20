/**
 * 
 */
package za.co.sindi.id3.builder;

import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.id3.constants.ID3v2Constants;
import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.frame.ID3v22Frame;
import za.co.sindi.id3.register.ID3v22FrameRegister;
import za.co.sindi.id3.tag.ID3v22Tag;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3v22Builder extends ID3v2Builder<ID3v22Tag> {

	private static final int COMPRESSION_FLAG = 0x40;
	
	/**
	 * 
	 */
	public ID3v22Builder() {
		// TODO Auto-generated constructor stub
		id3v2Tag = new ID3v22Tag();
		frameRegister = new ID3v22FrameRegister();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#assignID3v2Flags(int)
	 */
	@Override
	public void assignID3v2Flags(int id3v2Flags) {
		// TODO Auto-generated method stub
		if ((id3v2Flags | 0xC0) != 0xC0) {
			if (logger.isInfoEnabled())
				logger.warn("ID3v2 Flag '" + Integer.toBinaryString(id3v2Flags) + "' doesn't correspond to to format '%ab000000'. Some flags cannot be interpreted.");
		}
		
		//Set if unsynchronisation bit is set
		id3v2Tag.setUnsynchronizationSet((id3v2Flags & ID3v2Constants.UNSYNCHRONISATION_FLAG) == ID3v2Constants.UNSYNCHRONISATION_FLAG);
		
		//Set if extended header flag is set
		id3v2Tag.setCompressionSet((id3v2Flags & COMPRESSION_FLAG) == COMPRESSION_FLAG);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#extractID3v2Frames(java.io.InputStream)
	 */
	@Override
	public void extractID3v2Frames(InputStream in) throws ID3Exception {
		// TODO Auto-generated method stub
		try {			
			//Read all the frames
			int totalFrameSize = readFrames(in);
			if (totalFrameSize == 0) {
				throw new ID3Exception("ID3v2.2 specification states that there must be at least 1 ID3v2.2 declared frame.");
			}
			
			//Read all padding and include the amount of bytes read for padding.
			id3v2Tag.setPaddingReadSize(readPadding(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#readFrames(java.io.InputStream)
	 */
	@Override
	protected int readFrames(InputStream in) throws IOException, ID3Exception {
		// TODO Auto-generated method stub
		int totalFrameRead = 0;
		byte[] id3v2FrameChunk = null;
		
		while (true) {
			if (in.markSupported()) {
				in.mark(-1);
			}
			
			id3v2FrameChunk = new byte[3];
			in.read(id3v2FrameChunk);
			String frameID = new String(id3v2FrameChunk);
			if (!ID3v2Util.isValidID3v22HeaderFrameID(frameID)) {
				break; 
			}
			
			if (!frameRegister.containsFrame(frameID)) {
				throw new ID3Exception("Unknown ID3v22 Frame: " + frameID);
			}
			
			ID3v22Frame id3v2Frame = (ID3v22Frame) frameRegister.getDeclaredID3v2Frame(frameID);
			
			//Read header size
			in.read(id3v2FrameChunk);
			int frameHeaderSize = (((id3v2FrameChunk[0] & 0xFF) << 16) |
								  ((id3v2FrameChunk[1] & 0xFF) << 8) |	
								  ((id3v2FrameChunk[2] & 0xFF) << 0));		//This excludes the 6 byte frame header size
			id3v2Frame.setSize(frameHeaderSize);
			
			//Read frame data
			id3v2FrameChunk = new byte[frameHeaderSize];
			in.read(id3v2FrameChunk);
			id3v2Frame.decodeFrameData(id3v2FrameChunk);
			
			id3v2Tag.addID3v2Frame(id3v2Frame);
			totalFrameRead += (frameHeaderSize + 6);
		}
		
		if (in.markSupported()) {
			in.reset();
		}
		
		//Clear buffer
		id3v2FrameChunk = null;
		return totalFrameRead;
	}
}
