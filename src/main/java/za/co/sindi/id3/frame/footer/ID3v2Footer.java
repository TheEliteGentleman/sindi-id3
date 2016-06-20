/**
 * 
 */
package za.co.sindi.id3.frame.footer;

import za.co.sindi.id3.ID3Tag;
import za.co.sindi.id3.constants.ID3v2Constants;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v2Footer extends ID3Tag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3561601866211830019L;
	private int majorVersion;
	private int revisionNumber;
//	private boolean unsynchronisationSet;
//	private boolean extendedHeaderSet;
//	private boolean experimental;
	private int flags;
	
	/**
	 * 
	 */
	public ID3v2Footer() {
		// TODO Auto-generated constructor stub
		setHeader(ID3v2Constants.HEADER_END);
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
	 * @return the flags
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * @param flags the flags to set
	 */
	public void setFlags(int flags) {
		this.flags = flags;
	}
}
