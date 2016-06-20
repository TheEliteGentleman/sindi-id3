/**
 * 
 */
package za.co.sindi.id3.tag;

import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.frame.extended.ExtendedHeaderFrame;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public abstract class ID3v2TagWithExtendedHeader<T extends ExtendedHeaderFrame, U extends ID3v2Frame> extends ID3v2Tag<U> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151022562645514125L;
	private T extendedHeaderFrame;

	/**
	 * @return the extendedHeaderFrame
	 */
	public T getExtendedHeaderFrame() {
		return extendedHeaderFrame;
	}

	/**
	 * @param extendedHeaderFrame the extendedHeaderFrame to set
	 */
	public void setExtendedHeaderFrame(T extendedHeaderFrame) {
		this.extendedHeaderFrame = extendedHeaderFrame;
	}
}
