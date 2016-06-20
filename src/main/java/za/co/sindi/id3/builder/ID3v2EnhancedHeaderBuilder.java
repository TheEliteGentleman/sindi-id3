/**
 * 
 */
package za.co.sindi.id3.builder;

import java.io.InputStream;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.tag.ID3v2Tag;

/**
 * @author Bienfait Sindi
 * @since 26 June 2010
 *
 */
public abstract class ID3v2EnhancedHeaderBuilder<T extends ID3v2Tag<? extends ID3v2Frame>> extends ID3v2Builder<T> {

	protected abstract void extractExtendedHeader(InputStream in) throws ID3Exception;
}
