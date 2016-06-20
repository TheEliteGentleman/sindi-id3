/**
 * 
 */
package za.co.sindi.id3.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author Bienfait Sindi
 * @since 26 July 2010
 *
 */
public class ZLibCompression implements Compression {

	private static final String NO_SOURCE = "No source provided.";
	
	/* (non-Javadoc)
	 * @see com.neurologic.id3.compression.Compression#compress(byte[])
	 */
	public byte[] compress(byte[] source) throws IOException {
		// TODO Auto-generated method stub
		if (source == null || source.length == 0) {
			throw new IllegalArgumentException(NO_SOURCE);
		}
		
		Deflater compressor = new Deflater();
		compressor.setLevel(Deflater.BEST_COMPRESSION);
		compressor.setInput(source);
		compressor.finish();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(source.length);
		byte[] buffer = new byte[1024];
		
		while (!compressor.finished()) {
			int count = compressor.deflate(buffer);
			baos.write(buffer, 0, count);
		}
		
		//End compression
		compressor.end();
		
		//I know it has no effect but for people who don't know...always close OutputStreams
		baos.close();
		
		return baos.toByteArray();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.id3.compression.Compression#decompress(byte[])
	 */
	public byte[] decompress(byte[] source) throws IOException {
		// TODO Auto-generated method stub
		if (source == null || source.length == 0) {
			throw new IllegalArgumentException(NO_SOURCE);
		}
		
		Inflater decompressor = new Inflater();
		decompressor.setInput(source);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(source.length);
		byte[] buffer = new byte[1024];
		
		while (!decompressor.finished()) {
			try {
				int count = decompressor.inflate(buffer);
				baos.write(buffer, 0, count);
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				throw new IOException(e);
			}
		}
		
		//End decompression
		decompressor.end();
		
		//I know it has no effect but for people who don't know...always close OutputStreams
		baos.close();
		
		return baos.toByteArray();
	}
}
