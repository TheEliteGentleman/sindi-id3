/**
 * 
 */
package za.co.sindi.id3.tag;

import java.util.List;

import za.co.sindi.id3.frame.ID3v23Frame;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.frame.extended.ID3v23ExtendedHeaderFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23CommentFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23PictureFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23TextFrame;
import za.co.sindi.id3.frame.informed.ID3v2PictureFrame;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3v23Tag extends ID3v2TagWithExtendedHeader<ID3v23ExtendedHeaderFrame, ID3v23Frame> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 142137806129812159L;
	private boolean extendedHeaderSet;
	private boolean experimental;
//	private boolean crcSet;
//	private int totalFrameCRC;
	
	/**
	 * Default constructor. Sets the major number to 3 (ID3v2.3)
	 */
	public ID3v23Tag() {
		super();
		
		// TODO Auto-generated constructor stub
		setMajorVersion(3);
	}

	/**
	 * @return the extendedHeader
	 */
	public boolean isExtendedHeaderSet() {
		return extendedHeaderSet;
	}

	/**
	 * @param extendedHeaderSet the extendedHeaderSet to set
	 */
	public void setExtendedHeaderSet(boolean extendedHeaderSet) {
		this.extendedHeaderSet = extendedHeaderSet;
	}

	/**
	 * @return the experimental
	 */
	public boolean isExperimental() {
		return experimental;
	}

	/**
	 * @param experimental the experimental to set
	 */
	public void setExperimental(boolean experimental) {
		this.experimental = experimental;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getAlbum()
	 */
	@Override
	public String getAlbum() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TALB");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getArtist()
	 */
	@Override
	public String getArtist() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TPE1");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getBPM()
	 */
	@Override
	public String getBPM() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TBPM");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getComment()
	 */
	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("COMM");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23CommentFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getComposer()
	 */
	@Override
	public String getComposer() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TCOM");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getCopyright()
	 */
	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TCOP");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return "Copyright © " + ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getDiscNumber()
	 */
	@Override
	public String getDiscNumber() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TPOS");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getEncodedBy()
	 */
	@Override
	public String getEncodedBy() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TENC");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getGenre()
	 */
	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return getGenre("TCON");
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getPictures()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends ID3v2PictureFrame> getPictures() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("PIC");
		return (List<ID3v23PictureFrame>)frameList;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTitle()
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TIT2");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTotalFrameSizes()
	 */
	@Override
	public int getTotalFrameSizes() {
		// TODO Auto-generated method stub
		return getTotalFrameSize(10);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTrackNumber()
	 */
	@Override
	public String getTrackNumber() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TRCK");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getYear()
	 */
	@Override
	public String getYear() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TYER");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v23TextFrame) frameList.get(0)).getValue();
	}
}
