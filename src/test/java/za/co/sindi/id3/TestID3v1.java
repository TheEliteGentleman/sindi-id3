/**
 * 
 */
package za.co.sindi.id3;

import java.io.File;
import java.io.IOException;

import za.co.sindi.id3.constants.ID3v1Constants;
import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.reader.impl.ID3v1Reader;
import za.co.sindi.id3.tag.ID3v1Tag;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class TestID3v1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ID3v1Reader reader = new ID3v1Reader(new File("F:/iPod/F11/XNLL.mp3"));
			ID3v1Tag tag = reader.read();
			System.out.println(tag.getTrackNumber());
			System.out.println(tag.getAlbum());
			System.out.println(tag.getArtist());
			System.out.println(ID3v1Constants.GENRES[tag.getGenre()]);
		} catch (ID3Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
