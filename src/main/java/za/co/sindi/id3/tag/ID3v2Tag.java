/**
 * 
 */
package za.co.sindi.id3.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import za.co.sindi.id3.ID3Tag;
import za.co.sindi.id3.constants.ID3v1Constants;
import za.co.sindi.id3.constants.ID3v2Constants;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.frame.informed.ID3v2InformationFrame;
import za.co.sindi.id3.frame.informed.ID3v2PictureFrame;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 * 
 */
public abstract class ID3v2Tag<T extends ID3v2Frame> extends ID3Tag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853076224547290653L;
	private int majorVersion;
	private int revisionNumber;
	private boolean unsynchronizationSet;
	private List<T> id3v2Frames;
	private int paddingReadSize;
	
	/**
	 * 
	 */
	public ID3v2Tag() {
		// TODO Auto-generated constructor stub
		setHeader(ID3v2Constants.HEADER_START);
	}

	/**
	 * @return the majorVersion
	 */
	public int getMajorVersion() {
		return majorVersion;
	}

	/**
	 * @param majorVersion the majorVersion to set
	 */
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * @return the revisionNumber
	 */
	public int getRevisionNumber() {
		return revisionNumber;
	}

	/**
	 * @param revisionNumber the revisionNumber to set
	 */
	public void setRevisionNumber(int revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	/**
	 * @return the unsynchronizationSet
	 */
	public boolean isUnsynchronizationSet() {
		return unsynchronizationSet;
	}

	/**
	 * @param unsynchronizationSet the unsynchronizationSet to set
	 */
	public void setUnsynchronizationSet(boolean unsynchronizationSet) {
		this.unsynchronizationSet = unsynchronizationSet;
	}
	
	/**
	 * @return the paddingReadSize
	 */
	public int getPaddingReadSize() {
		return paddingReadSize;
	}

	/**
	 * @param paddingReadSize the paddingReadSize to set
	 */
	public void setPaddingReadSize(int paddingReadSize) {
		this.paddingReadSize = paddingReadSize;
	}

	/**
	 * Add an ID3v2 specific frame.
	 * 
	 * @param id3v2Frame and ID3v2.X.X frame
	 */
	public void addID3v2Frame(T id3v2Frame) {
		if (id3v2Frames == null) {
			id3v2Frames = new ArrayList<T>();
		}
		
		id3v2Frames.add(id3v2Frame);
	}
	
	public List<T> getFrames(String frameID) {
		List<T> frames = null;
		
		if (id3v2Frames != null && !id3v2Frames.isEmpty()) {
			frames = new ArrayList<T>();
			
			synchronized (id3v2Frames) {	
				for (T frame : id3v2Frames) {
					if (frame.getId().equals(frameID)) {
						frames.add(frame);
					}
				}
			}
		}
		
		return frames;
	}
	
	public String[] getFrameIDs() {
		if (id3v2Frames == null || id3v2Frames.isEmpty()) {
			return null;
		}
		
		List<String> idList = new ArrayList<String>();
		synchronized (id3v2Frames) {
			Iterator<T> iterator = id3v2Frames.iterator();
			while(iterator.hasNext()) {
				T t = iterator.next();
				if (!idList.contains(t.getId())) {
					idList.add(t.getId());
				}
			}
		}
		
		return idList.toArray(new String[idList.size()]);
	}
	
	public int getTotalFrames() {
		if (id3v2Frames == null) {
			return 0;
		}
		
		return id3v2Frames.size();
	}
	
	protected int getTotalFrameSize(int excludedFrameHeaderSize) {
		int totalFrameSize = 0;
		for (int i = 0; i < getTotalFrames(); i++) {
			totalFrameSize += (id3v2Frames.get(i).getSize() + excludedFrameHeaderSize);
		}
		
		return totalFrameSize;
	}
	
	@SuppressWarnings("unchecked")
	protected String getGenre(String genreFrameID) {
		List<? extends ID3v2Frame> frameList = getFrames(genreFrameID);
		
		if (frameList == null || frameList.isEmpty()) {
			return null;
		}
		
		String genreID = ((ID3v2InformationFrame<String>) frameList.get(0)).getValue();
		int startPos = 0;
		while (startPos < genreID.length() && genreID.charAt(startPos) != '(')
			startPos++;
		
		//If there's no '('
		if (startPos == genreID.length()) {
			return genreID;
		}
		
		int endPos = startPos + 1;
		while (genreID.charAt(endPos) != ')')
			endPos++;
		
		String genre = genreID.substring(startPos + 1, endPos);
		if ("RX".equals(genre)) {
			return "Remix";
		}
		
		int genreValue = Integer.parseInt(genre);
		if (genreValue < ID3v1Constants.GENRES.length) {
			return ID3v1Constants.GENRES[genreValue];
		}
		
		//Else
		startPos = endPos + 1;
		while (genreID.charAt(startPos) != '(')
			startPos++;
		
		endPos = startPos + 1;
		while (genreID.charAt(endPos) != ')')
			endPos++;
		
		return genreID.substring(startPos + 1, endPos);
	}
	
	public abstract int getTotalFrameSizes();

	public abstract String getAlbum();
	public abstract String getArtist();
	public abstract String getBPM();
	public abstract String getComment();
	public abstract String getComposer();
	public abstract String getCopyright();
	public abstract String getDiscNumber();
	public abstract String getEncodedBy();
	public abstract String getGenre();
	public abstract List<? extends ID3v2PictureFrame> getPictures();
	public abstract String getTitle();
	public abstract String getTrackNumber();
	public abstract String getYear();
}
