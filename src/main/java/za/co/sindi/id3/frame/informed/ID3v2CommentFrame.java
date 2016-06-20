/**
 * 
 */
package za.co.sindi.id3.frame.informed;


/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public interface ID3v2CommentFrame extends ID3v2DescriptiveInformationFrame<String> {

	public String getLanguage();
}
