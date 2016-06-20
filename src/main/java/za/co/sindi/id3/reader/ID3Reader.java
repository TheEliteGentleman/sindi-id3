/**
 * 
 */
package za.co.sindi.id3.reader;

import za.co.sindi.id3.ID3Tag;
import za.co.sindi.id3.exception.ID3Exception;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public interface ID3Reader<T extends ID3Tag> {

	public T read() throws ID3Exception;
}
