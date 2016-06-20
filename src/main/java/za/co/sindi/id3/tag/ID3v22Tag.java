/**
 * 
 */
package za.co.sindi.id3.tag;

import java.util.List;

import za.co.sindi.id3.frame.ID3v22Frame;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.frame.id3v22.ID3v22CommentFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22PictureFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22TextFrame;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3v22Tag extends ID3v2Tag<ID3v22Frame> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2108343198874722295L;
	private boolean compressionSet;
	
	/**
	 * 
	 */
	public ID3v22Tag() {
		super();
		
		// TODO Auto-generated constructor stub
		setMajorVersion(2);
	}

	/**
	 * @return the compressionSet
	 */
	public boolean isCompressionSet() {
		return compressionSet;
	}

	/**
	 * @param compressionSet the compressionSet to set
	 */
	public void setCompressionSet(boolean compressionSet) {
		this.compressionSet = compressionSet;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getAlbum()
	 */
	@Override
	public String getAlbum() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TAL");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getArtist()
	 */
	@Override
	public String getArtist() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TP1");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getBPM()
	 */
	@Override
	public String getBPM() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TBP");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getComment()
	 */
	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("COM");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22CommentFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getComposer()
	 */
	@Override
	public String getComposer() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TCM");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getCopyright()
	 */
	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TCR");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return "Copyright © " + ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getDiscNumber()
	 */
	@Override
	public String getDiscNumber() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TPA");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getEncodedBy()
	 */
	@Override
	public String getEncodedBy() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TEN");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getGenre()
	 */
	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return getGenre("TCO");
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getPictures()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ID3v22PictureFrame> getPictures() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("PIC");
		return (List<ID3v22PictureFrame>)frameList;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTitle()
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TT2");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTrackNumber()
	 */
	@Override
	public String getTrackNumber() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TRK");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getTotalFrameSizes()
	 */
	@Override
	public int getTotalFrameSizes() {
		// TODO Auto-generated method stub
		return getTotalFrameSize(6);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getYear()
	 */
	@Override
	public String getYear() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TYE");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		return ((ID3v22TextFrame) frameList.get(0)).getValue();
	}
}
