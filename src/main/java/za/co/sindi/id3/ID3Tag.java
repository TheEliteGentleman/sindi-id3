/**
 * 
 */
package za.co.sindi.id3;

import java.io.Serializable;

/**
 * An abstract ID3 tag class.
 * 
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public abstract class ID3Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864896173259159201L;
	private String header;
	private int headerPosition;
	private int size;
	
	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	
	/**
	 * @param header the header to set
	 */
	protected void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the headerPosition
	 */
	public int getHeaderPosition() {
		return headerPosition;
	}

	/**
	 * @param headerPosition the headerPosition to set
	 */
	public void setHeaderPosition(int headerPosition) {
		this.headerPosition = headerPosition;
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
