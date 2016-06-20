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
import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.extended.ID3v24ExtendedHeaderFrame;
import za.co.sindi.id3.frame.footer.ID3v2Footer;
import za.co.sindi.id3.register.ID3v24FrameRegister;
import za.co.sindi.id3.tag.ID3v24Tag;
import za.co.sindi.id3.util.ID3v2Util;

/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public class ID3v24Builder extends ID3v2EnhancedHeaderBuilder<ID3v24Tag> {

	private static final int EXTENDED_HEADER_FLAG 			= 0x40;
	private static final int EXPERIMENTAL_INDICATOR_FLAG 	= 0x20;
	private static final int FOOTER_PRESENT_FLAG			= 0x10;
	
	private static final int TAG_UPDATE_FLAG 				= 0x40;
	private static final int CRC_PRESENT_FLAG				= 0x20;
	private static final int TAG_RESTRICTION_FLAG			= 0x10;
	
	/**
	 * 
	 */
	public ID3v24Builder() {
		// TODO Auto-generated constructor stub
		id3v2Tag = new ID3v24Tag();
		frameRegister = new ID3v24FrameRegister();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2Builder#assignID3v2Flags(int)
	 */
	@Override
	public void assignID3v2Flags(int id3v2Flags) {
		// TODO Auto-generated method stub
		if ((id3v2Flags | 0xF0) != 0xF0) {
			if (logger.isInfoEnabled())
				logger.warn("ID3v2.4 Flag '" + Integer.toBinaryString(id3v2Flags) + "' doesn't correspond to to format '%abcd00000'. Some flags cannot be interpreted.");
		}
		
		//Set if unsynchronisation bit is set
		id3v2Tag.setUnsynchronizationSet((id3v2Flags & ID3v2Constants.UNSYNCHRONISATION_FLAG) == ID3v2Constants.UNSYNCHRONISATION_FLAG);
		
		//Set if extended header flag is set
		id3v2Tag.setExtendedHeaderSet((id3v2Flags & EXTENDED_HEADER_FLAG) == EXTENDED_HEADER_FLAG);
		
		//Set experimental
		id3v2Tag.setExperimental((id3v2Flags & EXPERIMENTAL_INDICATOR_FLAG) == EXPERIMENTAL_INDICATOR_FLAG);

		//Set footer present
		id3v2Tag.setFooterPresent((id3v2Flags & FOOTER_PRESENT_FLAG) == FOOTER_PRESENT_FLAG);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.builder.ID3v2EnhancedHeaderBuilder#extractExtendedHeader(java.io.InputStream)
	 */
	@Override
	protected void extractExtendedHeader(InputStream in) throws ID3Exception {
		// TODO Auto-generated method stub
		byte[] b = null;
		
		if (id3v2Tag.isExtendedHeaderSet()) {
			try {
				b = new byte[4];
				in.read(b);
				
				ID3v24ExtendedHeaderFrame extendedHeader = new ID3v24ExtendedHeaderFrame();
				extendedHeader.setSize((int) ID3v2Util.calculateSynchSafeInt(b));
				
				//Extended Flags
				int noOfFlagBytes = in.read();
				if (noOfFlagBytes != 0x01) {
					throw new ID3Exception("Extended Header 'Number of Flag Bytes' != $" + Integer.toHexString(noOfFlagBytes));
				}
				
				int extendedFlags = in.read();
				if ((extendedFlags | 0x70) != 0x70) {
					if (logger.isInfoEnabled()) {
						logger.warn("ID3v2.4 Extended Flag '" + Integer.toBinaryString(extendedFlags) + "' doesn't correspond to to format '%0bcd00000'. Some flags cannot be interpreted.");
					}
				}
				
				//Tag is an update flag
				extendedHeader.setTagAnUpdate((extendedFlags & TAG_UPDATE_FLAG) == TAG_UPDATE_FLAG);
				
				//CRC present data flag
				extendedHeader.setCrcSet((extendedFlags & CRC_PRESENT_FLAG) == CRC_PRESENT_FLAG);
				
				//Tag Restriction flag
				extendedHeader.setTagRestricted((extendedFlags & TAG_RESTRICTION_FLAG) == TAG_RESTRICTION_FLAG);
				
				//an updated tag has not frame value
				//Read CRC
				if (extendedHeader.isCrcSet()) {
					b = new byte[5];
					in.read(b);
					extendedHeader.setTotalFrameCRC(ID3v2Util.calculateSynchSafeInt(b));
				}
				
				if (extendedHeader.isTagRestricted()) {
					b = new byte[1];
					in.read(b);
					extendedHeader.setTagSizeRestrictionType((b[0] >>> 6) & 0x03);
					extendedHeader.setTextEncodingRestrictionType((b[0] >>> 5) & 0x01);
					extendedHeader.setTextSizeRestrictionType((b[0] >>> 3) & 0x03);
					extendedHeader.setImageEncodingRestrictionType((b[0] >>> 2) & 0x01);
					extendedHeader.setImageSizeRestrictionType(b[0] & 0x03);
				}
				
				//Add to frame
				id3v2Tag.setExtendedHeaderFrame(extendedHeader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e);
				throw new ID3Exception(e);
			} finally {
				if (b != null)
					b = null;
			}
		}
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
				throw new ID3Exception("ID3v2.4 specification states that there must be at least 1 ID3v2.3 declared frame.");
			}
			
			//Read all padding and include the amount of bytes read for padding.
			id3v2Tag.setPaddingReadSize(readPadding(in));
			
			//Read footer, if any.
			readFooter(in);
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
		byte[] id3v2Chunk = null;
		
		while (id3v2Tag.getTotalFrameSizes() < id3v2Tag.getSize()) {
			if (in.markSupported()) {
				in.mark(-1);
			}
			
			id3v2Chunk = new byte[4];
			in.read(id3v2Chunk);
			String frameID = new String(id3v2Chunk);
			if (!ID3v2Util.isValidID3v23HeaderFrameID(frameID)) {
				break;
			}
			
			if (!frameRegister.containsFrame(frameID)) {
				throw new ID3Exception("Unknown ID3v24 Frame: " + frameID);
			}
			
			ID3v24Frame id3v24Frame = (ID3v24Frame) frameRegister.getDeclaredID3v2Frame(frameID);
			
			//Read frame size
			in.read(id3v2Chunk);
			id3v24Frame.setSize((int)ID3v2Util.calculateSynchSafeInt(id3v2Chunk));
			
			//Flags
			id3v2Chunk = new byte[2];
			in.read(id3v2Chunk);
			
			if ((id3v2Chunk[0] | 0x70) != 0x70) {
				if (logger.isInfoEnabled())
					logger.warn("ID3v2.4 Status Flag '" + Integer.toBinaryString(id3v2Chunk[0] & 0xFF) + "' doesn't correspond to to format '%abcd00000'. Some flags cannot be interpreted.");
			}
			
			id3v24Frame.setTagAlterPreserved((id3v2Chunk[0] & 0x40) == 0);
			id3v24Frame.setFileAlterPreserved((id3v2Chunk[0] & 0x20) == 0);
			id3v24Frame.setReadOnly((id3v2Chunk[0] & 0x40) == 0);
			
			if ((id3v2Chunk[1] | 0x4F) != 0x4F) {
				if (logger.isInfoEnabled())
					logger.warn("ID3v2.4 Status Flag '" + Integer.toBinaryString(id3v2Chunk[1] & 0xFF) + "' doesn't correspond to to format '%0h00kmnp'. Some flags cannot be interpreted.");
			}
			
			id3v24Frame.setGroupIdentity((id3v2Chunk[1] & 0x40) == 0x40);
			id3v24Frame.setCompressed((id3v2Chunk[1] & 0x08) == 0x08);
			id3v24Frame.setEncrypted((id3v2Chunk[1] & 0x04) == 0x04);
			id3v24Frame.setUnsynchronized((id3v2Chunk[1] & 0x02) == 0x02);
			id3v24Frame.setDataLengthIndicated((id3v2Chunk[1] & 0x01) == 0x01);
			
			if (id3v24Frame.isCompressed() && !id3v24Frame.isDataLengthIndicated()) {
				throw new ID3Exception("The ID3v2.4 Frame '" + frameID +"' is compressed but the 'Data Length Indicator' bit has not been set.");
			}
			
			//Are we compressed
			if (id3v24Frame.isCompressed()) {
				id3v2Chunk = new byte[4];
				in.read(id3v2Chunk);
				id3v24Frame.setDecompressedSize(((id3v2Chunk[0] & 0xFF) << 24) | 
												((id3v2Chunk[1] & 0xFF) << 16) | 
												((id3v2Chunk[2] & 0xFF) << 8) |
												((id3v2Chunk[3] & 0xFF) << 0));
			}
			
			if (id3v24Frame.isEncrypted()) {
				id3v24Frame.setEncryptionMethod((byte) in.read());
			}
			
			//Read frame data
			id3v2Chunk = new byte[id3v24Frame.getSize()];
			in.read(id3v2Chunk);
			
			//Decompress the compressed
			if (id3v24Frame.isCompressed()) {
				Compression compression = new ZLibCompression();
				id3v2Chunk = compression.decompress(id3v2Chunk);
			}
			
			//Now, read data....
			id3v24Frame.decodeFrameData(id3v2Chunk);
			
			id3v2Tag.addID3v2Frame(id3v24Frame);
			totalFrameRead += (id3v24Frame.getSize() + 10);
		}
		
		if (in.markSupported()) {
			in.reset();
		}
		
		//clear
		id3v2Chunk = null;
		return totalFrameRead;
	}
	
	private void readFooter(InputStream in) throws IOException {
		int pos = 0;
		boolean flagFound = false;
		byte[] id3Chunk = new byte[3];
		
		if(id3v2Tag.isFooterPresent()) {
			try {
				while (in.available() > 0) {
					in.skip(pos);
					if (in.markSupported()) {
						in.mark(-1);
					}
					
					in.read(id3Chunk);
					if ((id3Chunk[0] & 0xFF) == '3' && (id3Chunk[1] & 0xFF) == 'D' && (id3Chunk[2] & 0xFF) == 'I') {
						flagFound = true;
						break;
					}
					
					if (in.markSupported()) {
						in.reset();
					}
					
					pos++;
				}
				
				if (!flagFound) {
					return ;
				}
				
				ID3v2Footer footer = new ID3v2Footer();
				footer.setHeaderPosition(id3v2Tag.getHeaderPosition() + (id3v2Tag.isExtendedHeaderSet() ? id3v2Tag.getExtendedHeaderFrame().getSize() : 0) + id3v2Tag.getTotalFrameSizes() + id3v2Tag.getPaddingReadSize() + pos);
				footer.setMajorVersion(in.read());
				footer.setRevisionNumber(in.read());
				
				if ((id3v2Tag.getMajorVersion() != footer.getMajorVersion()) || (id3v2Tag.getRevisionNumber() != footer.getRevisionNumber())) {
					throw new IOException("ID3v2.4." + id3v2Tag.getRevisionNumber() + " major/revision number doesn't match.");
				}
				
				footer.setFlags(in.read());
				if ((footer.getFlags() | 0xF0) != 0xF0) {
					if (logger.isInfoEnabled())
						logger.warn("ID3v2.4 Footer Flag '" + Integer.toBinaryString(footer.getFlags()) + "' doesn't correspond to to format '%abcd00000'. Some flags cannot be interpreted.");
				}
				
				//Set ID3v2 Size
				id3Chunk = new byte[4];
				in.read(id3Chunk);
				int id3v2Size = (int) ID3v2Util.calculateSynchSafeInt(id3Chunk);
				footer.setSize(id3v2Size);
				
				id3v2Tag.setFooter(footer);
			} finally {
				//Clear buffer
				if (id3Chunk != null)
					id3Chunk = null;
			}
		}
	}
}
