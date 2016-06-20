/**
 * 
 */
package za.co.sindi.id3.exception;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3Exception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472495381973554258L;

	/**
	 * @param message
	 * @param cause
	 */
	public ID3Exception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ID3Exception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ID3Exception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
