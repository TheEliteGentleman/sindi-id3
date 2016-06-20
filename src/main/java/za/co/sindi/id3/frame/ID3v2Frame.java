/**
 * 
 */
package za.co.sindi.id3.frame;

import java.io.Serializable;

import za.co.sindi.id3.exception.ID3Exception;

/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public abstract class ID3v2Frame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227894773625697190L;
	private String id;
	private int size;
	//private byte[] data;
	
	/**
	 * @param id
	 * @param size
	 * @param data
	 */
	public ID3v2Frame(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
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

	/**
	 * @param data the data to set
	 */
	public abstract void decodeFrameData(byte[] data) throws ID3Exception;
	protected abstract String getTextEncoding(int encoding);
}
