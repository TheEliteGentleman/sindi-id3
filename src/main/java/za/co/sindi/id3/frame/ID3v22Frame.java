/**
 * 
 */
package za.co.sindi.id3.frame;

/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public abstract class ID3v22Frame extends ID3v2Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757882960612190144L;

	/**
	 * @param id
	 */
	public ID3v22Frame(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.frame.ID3v2Frame#getTextEncoding(int)
	 */
	@Override
	protected String getTextEncoding(int encoding) {
		// TODO Auto-generated method stub
		switch(encoding) {
			case 0: 
				return "ISO-8859-1";
			
			case 1:
				return "UCS-2";
				
			default: throw new IllegalArgumentException("Invalid encoding (" + encoding + ").");
		}
	}
}
