/**
 * 
 */
package za.co.sindi.id3.frame.informed;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public interface ID3v2PictureFrame extends ID3v2DescriptiveInformationFrame<byte[]> {

	public String getMIMEType();
	public int getPictureType();
	public String getPictureType(int type);
}
