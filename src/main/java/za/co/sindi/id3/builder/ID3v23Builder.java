/**
 * 
 */
package za.co.sindi.id3.builder;

import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.id3.compression.Compression;
import za.co.sindi.id3.compression.ZLibCompression;
import za.co.sindi.id3.constants.ID3v2Constants;
import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.frame.ID3v23Frame;
import za.co.sindi.id3.frame.extended.ID3v23ExtendedHeaderFrame;
import za.co.sindi.id3.register.ID3v23FrameRegister;
import za.co.sindi.id3.tag.ID3v23Tag;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3v23Builder extends ID3v2EnhancedHeaderBuilder<ID3v23Tag> {
	
	private static final int EXTENDED_HEADER_FLAG 			= 0x40;
	private static final int EXPERIMENTAL_INDICATOR_FLAG 	= 0x20;
	private static final int CRC_FLAG 						= 0x80;

	/**
	 * 
	 */
	public ID3v23Builder() {
		// TODO Auto-generated constructor stub
		id3v2Tag = new ID3v23Tag();
		frameRegister = new ID3v23FrameRegister();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#assignID3v2Flags(int)
	 */
	@Override
	public void assignID3v2Flags(int id3v2Flags) {
		// TODO Auto-generated method stub
		if ((id3v2Flags | 0xE0) != 0xE0) {
			if (logger.isInfoEnabled()) {
				logger.warn("ID3v2 Flag '" + Integer.toBinaryString(id3v2Flags) + "' doesn't correspond to to format '%abc00000'. Some flags cannot be interpreted.");
			}
		}
		
		//Set if unsynchronisation bit is set
		id3v2Tag.setUnsynchronizationSet((id3v2Flags & ID3v2Constants.UNSYNCHRONISATION_FLAG) == ID3v2Constants.UNSYNCHRONISATION_FLAG);
		
		//Set if extended header flag is set
		id3v2Tag.setExtendedHeaderSet((id3v2Flags & EXTENDED_HEADER_FLAG) == EXTENDED_HEADER_FLAG);
		
		//Set experimental
		id3v2Tag.setExperimental((id3v2Flags & EXPERIMENTAL_INDICATOR_FLAG) == EXPERIMENTAL_INDICATOR_FLAG);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#extractID3v2Frames(java.io.InputStream)
	 */
	@Override
	public void extractID3v2Frames(InputStream in) throws ID3Exception {
		// TODO Auto-generated method stub
		try {
			//Read Extended Header, if set
			extractExtendedHeader(in);
			
			//Read all the frames
			int totalFrameSize = readFrames(in);
			if (totalFrameSize == 0) {
				throw new ID3Exception("ID3v2.3 specification states that there must be at least 1 ID3v2.3 declared frame.");
			}
			
			//Read all padding and include the amount of bytes read for padding.
			id3v2Tag.setPaddingReadSize(readPadding(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ID3Exception(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2EnhancedHeaderBuilder#extractExtendedHeader(java.io.InputStream)
	 */
	@Override
	protected void extractExtendedHeader(InputStream in) throws ID3Exception {
		// TODO Auto-generated method stub
		if (id3v2Tag.isExtendedHeaderSet()) {
			try {
				ID3v23ExtendedHeaderFrame extendedHeader = new ID3v23ExtendedHeaderFrame();
				extendedHeader.setSize(readInt(in));
				
				//Extended Flags
				byte[] extendedFlagsBytes = new byte[2];
				in.read(extendedFlagsBytes);
				
				//Set CRC Set
				extendedHeader.setCrcSet((extendedFlagsBytes[0] & CRC_FLAG) == CRC_FLAG);
				
				//Do we have CRC?
				if (extendedHeader.isCrcSet()) {
					extendedHeader.setTotalFrameCRC(readInt(in));
					
					if (logger.isInfoEnabled()) {
						logger.info("CRC flag is set, CRC (32-bit) = " + extendedHeader.getTotalFrameCRC());
					}
				}
				
				//Padding
				extendedHeader.setPaddingSize(readInt(in));
				in.skip(extendedHeader.getPaddingSize());
				
				//Add to frame
				id3v2Tag.setExtendedHeaderFrame(extendedHeader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e);
				throw new ID3Exception(e);
			}
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
			
			id3v2FrameChunk = new byte[4];
			in.read(id3v2FrameChunk);
			String frameID = new String(id3v2FrameChunk);
			if (!ID3v2Util.isValidID3v23HeaderFrameID(frameID)) {
				break;
			}
			
			if (!frameRegister.containsFrame(frameID)) {
				throw new ID3Exception("Unknown ID3v23 Frame: " + frameID);
			}
			
			ID3v23Frame id3v2Frame = (ID3v23Frame) frameRegister.getDeclaredID3v2Frame(frameID);
			
			//Read header size
			id3v2Frame.setSize(readInt(in));	//This excludes the 10 byte frame header size
			
			//Check flags;
			id3v2FrameChunk = new byte[2];
			in.read(id3v2FrameChunk);
			if ((id3v2FrameChunk[0] | 0xE0) != 0xE0) {
				if (logger.isInfoEnabled()) {
					logger.warn("Frame Header Flag '" + Integer.toBinaryString(id3v2FrameChunk[0] & 0xFF) + "' doesn't apply to format '%abc00000'. Some field won't be easily interpreted.");
				}
			}
				
			id3v2Frame.setTagAlterPreserved((id3v2FrameChunk[0] & 0x80) == 0);
			id3v2Frame.setFileAlterPreserved((id3v2FrameChunk[0] & 0x40) == 0);
			id3v2Frame.setReadOnly((id3v2FrameChunk[0] & 0x20) == 0x20);
			
			if ((id3v2FrameChunk[1] | 0xE0) != 0xE0) {
				if (logger.isInfoEnabled()) {
					logger.warn("Frame Header Flag '" + Integer.toBinaryString(id3v2FrameChunk[1] & 0xFF) + "' doesn't apply to format '%ijk00000'. Some field won't be easily interpreted.");
				}
			}
						
			id3v2Frame.setCompressed((id3v2FrameChunk[1] & 0x80) == 0x80);
			id3v2Frame.setEncrypted((id3v2FrameChunk[1] & 0x40) == 0x40);
			id3v2Frame.setGroupIdentity((id3v2FrameChunk[1] & 0x20) == 0x20);
			
			//Are we compressed
			if (id3v2Frame.isCompressed()) {
				id3v2FrameChunk = new byte[4];
				in.read(id3v2FrameChunk);
				id3v2Frame.setDecompressedSize(((id3v2FrameChunk[0] & 0xFF) << 24) | 
												((id3v2FrameChunk[1] & 0xFF) << 16) | 
												((id3v2FrameChunk[2] & 0xFF) << 8) |
												((id3v2FrameChunk[3] & 0xFF) << 0));
			}
			
			if (id3v2Frame.isEncrypted()) {
				id3v2Frame.setEncryptionMethod((byte) in.read());
			}
			
			//Read frame data
			id3v2FrameChunk = new byte[id3v2Frame.getSize()];
			in.read(id3v2FrameChunk);
			
			//Decompress the compressed
			if (id3v2Frame.isCompressed()) {
				Compression compression = new ZLibCompression();
				id3v2FrameChunk = compression.decompress(id3v2FrameChunk);
			}
			
			//Now, read data....
			id3v2Frame.decodeFrameData(id3v2FrameChunk);
			
			id3v2Tag.addID3v2Frame(id3v2Frame);
			totalFrameRead += (id3v2Frame.getSize() + 10);
		}
		
		if(in.markSupported()) {
			in.reset();
		}
		
		//Clear buffer
		id3v2FrameChunk = null;		
		return totalFrameRead;
	}
}
