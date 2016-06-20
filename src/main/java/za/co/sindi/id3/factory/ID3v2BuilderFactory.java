/**
 * 
 */
package za.co.sindi.id3.factory;

import za.co.sindi.id3.builder.ID3v2Builder;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.tag.ID3v2Tag;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public interface ID3v2BuilderFactory {

	public ID3v2Builder<? extends ID3v2Tag<? extends ID3v2Frame>> createID3v2Builder(int majorNumber);
}
