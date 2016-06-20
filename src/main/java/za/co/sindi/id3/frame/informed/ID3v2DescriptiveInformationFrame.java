/**
 * 
 */
package za.co.sindi.id3.frame.informed;

import java.io.Serializable;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public interface ID3v2DescriptiveInformationFrame<T extends Serializable> extends ID3v2InformationFrame<T> {

	public String getDescription();
}
