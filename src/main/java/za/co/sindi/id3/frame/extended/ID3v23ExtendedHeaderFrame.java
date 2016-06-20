/**
 * 
 */
package za.co.sindi.id3.frame.extended;


/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v23ExtendedHeaderFrame extends ExtendedHeaderFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7831062082238479677L;
	private int paddingSize;
	
	/**
	 * @return the paddingSize
	 */
	public int getPaddingSize() {
		return paddingSize;
	}
	/**
	 * @param paddingSize the paddingSize to set
	 */
	public void setPaddingSize(int paddingSize) {
		this.paddingSize = paddingSize;
	}
}
