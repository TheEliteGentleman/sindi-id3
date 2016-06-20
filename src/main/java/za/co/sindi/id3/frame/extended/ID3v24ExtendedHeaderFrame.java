/**
 * 
 */
package za.co.sindi.id3.frame.extended;


/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v24ExtendedHeaderFrame extends ExtendedHeaderFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5150916288459622090L;
	private boolean tagAnUpdate;
	private boolean tagRestricted;
	private int tagSizeRestrictionType;
	private int textEncodingRestrictionType;
	private int textSizeRestrictionType;
	private int imageEncodingRestrictionType;
	private int imageSizeRestrictionType;
	
	/**
	 * @return the tagAnUpdate
	 */
	public boolean isTagAnUpdate() {
		return tagAnUpdate;
	}
	
	/**
	 * @param tagAnUpdate the tagAnUpdate to set
	 */
	public void setTagAnUpdate(boolean tagAnUpdate) {
		this.tagAnUpdate = tagAnUpdate;
	}
	
	/**
	 * @return the tagRestricted
	 */
	public boolean isTagRestricted() {
		return tagRestricted;
	}
	
	/**
	 * @param tagRestricted the tagRestricted to set
	 */
	public void setTagRestricted(boolean tagRestricted) {
		this.tagRestricted = tagRestricted;
	}
	
	/**
	 * @return the tagSizeRestrictionType
	 */
	public int getTagSizeRestrictionType() {
		return tagSizeRestrictionType;
	}
	
	/**
	 * @param tagSizeRestrictionType the tagSizeRestrictionType to set
	 */
	public void setTagSizeRestrictionType(int tagSizeRestrictionType) {
		this.tagSizeRestrictionType = tagSizeRestrictionType;
	}
	
	/**
	 * @return the textEncodingRestrictionType
	 */
	public int getTextEncodingRestrictionType() {
		return textEncodingRestrictionType;
	}
	
	/**
	 * @param textEncodingRestrictionType the textEncodingRestrictionType to set
	 */
	public void setTextEncodingRestrictionType(int textEncodingRestrictionType) {
		this.textEncodingRestrictionType = textEncodingRestrictionType;
	}
	
	/**
	 * @return the textSizeRestrictionType
	 */
	public int getTextSizeRestrictionType() {
		return textSizeRestrictionType;
	}
	
	/**
	 * @param textSizeRestrictionType the textSizeRestrictionType to set
	 */
	public void setTextSizeRestrictionType(int textSizeRestrictionType) {
		this.textSizeRestrictionType = textSizeRestrictionType;
	}
	
	/**
	 * @return the imageEncodingRestrictionType
	 */
	public int getImageEncodingRestrictionType() {
		return imageEncodingRestrictionType;
	}
	
	/**
	 * @param imageEncodingRestrictionType the imageEncodingRestrictionType to set
	 */
	public void setImageEncodingRestrictionType(int imageEncodingRestrictionType) {
		this.imageEncodingRestrictionType = imageEncodingRestrictionType;
	}

	/**
	 * @return the imageSizeRestrictionType
	 */
	public int getImageSizeRestrictionType() {
		return imageSizeRestrictionType;
	}

	/**
	 * @param imageSizeRestrictionType the imageSizeRestrictionType to set
	 */
	public void setImageSizeRestrictionType(int imageSizeRestrictionType) {
		this.imageSizeRestrictionType = imageSizeRestrictionType;
	}
}
