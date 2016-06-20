/**
 * 
 */
package za.co.sindi.id3.compression;

import java.io.IOException;

/**
 * @author Bienfait Sindi
 * @since 26 July 2010
 *
 */
public interface Compression {

	public byte[] compress(byte[] source) throws IOException;
	public byte[] decompress(byte[] source) throws IOException;
}
