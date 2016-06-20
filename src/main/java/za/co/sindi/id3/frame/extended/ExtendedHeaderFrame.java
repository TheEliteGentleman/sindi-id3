/**
 * 
 */
package za.co.sindi.id3.frame.extended;

import java.io.Serializable;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public abstract class ExtendedHeaderFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164283357131119487L;
	private int size;
	private boolean crcSet;
	private long totalFrameCRC;
	
	/**
	 * @return the crcSet
	 */
	public boolean isCrcSet() {
		return crcSet;
	}
	
	/**
	 * @param crcSet the crcSet to set
	 */
	public void setCrcSet(boolean crcSet) {
		this.crcSet = crcSet;
	}
	
	/**
	 * @return the totalFrameCRC
	 */
	public long getTotalFrameCRC() {
		return totalFrameCRC;
	}
	
	/**
	 * @param totalFrameCRC the totalFrameCRC to set
	 */
	public void setTotalFrameCRC(long totalFrameCRC) {
		this.totalFrameCRC = totalFrameCRC;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
