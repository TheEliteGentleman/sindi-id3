/**
 * 
 */
package za.co.sindi.id3.frame;

/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public abstract class ID3v24Frame extends ID3v2Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1898444183297964546L;
	private boolean tagAlterPreserved;
	private boolean fileAlterPreserved;
	private boolean readOnly;
	private boolean groupIdentity;
	private boolean compressed;
	private int decompressedSize = 0;
	private boolean encrypted;
	private byte encryptionMethod;
	private boolean unsynchronized;
	private boolean dataLengthIndicated;
	
	/**
	 * @param id
	 */
	public ID3v24Frame(String id) {
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
				
			case 2:
				return "UTF-16BE";
				
			case 3: 
				return "UTF-8";
				
			default: throw new IllegalArgumentException("Invalid encoding (" + encoding + ").");
		}
	}

	/**
	 * @return the tagAlterPreserved
	 */
	public boolean isTagAlterPreserved() {
		return tagAlterPreserved;
	}

	/**
	 * @param tagAlterPreserved the tagAlterPreserved to set
	 */
	public void setTagAlterPreserved(boolean tagAlterPreserved) {
		this.tagAlterPreserved = tagAlterPreserved;
	}

	/**
	 * @return the fileAlterPreserved
	 */
	public boolean isFileAlterPreserved() {
		return fileAlterPreserved;
	}

	/**
	 * @param fileAlterPreserved the fileAlterPreserved to set
	 */
	public void setFileAlterPreserved(boolean fileAlterPreserved) {
		this.fileAlterPreserved = fileAlterPreserved;
	}

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @return the groupIdentity
	 */
	public boolean isGroupIdentity() {
		return groupIdentity;
	}

	/**
	 * @param groupIdentity the groupIdentity to set
	 */
	public void setGroupIdentity(boolean groupIdentity) {
		this.groupIdentity = groupIdentity;
	}

	/**
	 * @return the compressed
	 */
	public boolean isCompressed() {
		return compressed;
	}

	/**
	 * @param compressed the compressed to set
	 */
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}

	/**
	 * @return the decompressedSize
	 */
	public int getDecompressedSize() {
		return decompressedSize;
	}

	/**
	 * @param decompressedSize the decompressedSize to set
	 */
	public void setDecompressedSize(int decompressedSize) {
		this.decompressedSize = decompressedSize;
	}

	/**
	 * @return the encrypted
	 */
	public boolean isEncrypted() {
		return encrypted;
	}

	/**
	 * @param encrypted the encrypted to set
	 */
	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	/**
	 * @return the encryptionMethod
	 */
	public byte getEncryptionMethod() {
		return encryptionMethod;
	}

	/**
	 * @param encryptionMethod the encryptionMethod to set
	 */
	public void setEncryptionMethod(byte encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	/**
	 * @return the unsynchronized
	 */
	public boolean isUnsynchronized() {
		return unsynchronized;
	}

	/**
	 * @param unsynchronized the unsynchronized to set
	 */
	public void setUnsynchronized(boolean unsynchronized) {
		this.unsynchronized = unsynchronized;
	}

	/**
	 * @return the dataLengthIndicated
	 */
	public boolean isDataLengthIndicated() {
		return dataLengthIndicated;
	}

	/**
	 * @param dataLengthIndicated the dataLengthIndicated to set
	 */
	public void setDataLengthIndicated(boolean dataLengthIndicated) {
		this.dataLengthIndicated = dataLengthIndicated;
	}
}
