/**
 * 
 */
package za.co.sindi.id3.tag;

import java.util.List;

import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.frame.extended.ID3v24ExtendedHeaderFrame;
import za.co.sindi.id3.frame.footer.ID3v2Footer;
import za.co.sindi.id3.frame.id3v24.ID3v24CommentFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24PictureFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24TextFrame;
import za.co.sindi.id3.frame.informed.ID3v2PictureFrame;


/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public class ID3v24Tag extends ID3v2TagWithExtendedHeader<ID3v24ExtendedHeaderFrame, ID3v24Frame> {
	
	private boolean extendedHeaderSet;
	private boolean experimental;
	private boolean footerPresent;
	private ID3v2Footer footer;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2481651324667486682L;

	/**
	 * 
	 */
	public ID3v24Tag() {
		super();
		
		// TODO Auto-generated constructor stub
		setMajorVersion(4);
	}

	/**
	 * @return the extendedHeaderSet
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

	/**
	 * @return the footer
	 */
	public ID3v2Footer getFooter() {
		return footer;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(ID3v2Footer footer) {
		this.footer = footer;
	}

	/**
	 * @return the footerPresent
	 */
	public boolean isFooterPresent() {
		return footerPresent;
	}

	/**
	 * @param footerPresent the footerPresent to set
	 */
	public void setFooterPresent(boolean footerPresent) {
		this.footerPresent = footerPresent;
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24CommentFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return "Copyright © " + ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		return (List<ID3v24PictureFrame>)frameList;
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
		
		return ((ID3v24TextFrame) frameList.get(0)).getValue();
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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.tag.ID3v2Tag#getYear()
	 */
	@Override
	public String getYear() {
		// TODO Auto-generated method stub
		List<? extends ID3v2Frame> frameList = getFrames("TDRC");
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		String date = ((ID3v24TextFrame) frameList.get(0)).getValue();
		if (date.length() > 4) {
			date = date.substring(0, 4);
		}
		
		return date;
	}
}
