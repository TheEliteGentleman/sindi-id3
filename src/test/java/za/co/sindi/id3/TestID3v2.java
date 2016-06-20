/**
 * 
 */
package za.co.sindi.id3;

import java.io.File;
import java.io.IOException;

import za.co.sindi.id3.exception.ID3Exception;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.reader.impl.ID3v2Reader;
import za.co.sindi.id3.tag.ID3v24Tag;
import za.co.sindi.id3.tag.ID3v2Tag;

/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class TestID3v2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(TestID3v2.class.getCanonicalName());
			//ID3v2Reader reader = new ID3v2Reader(new File("F:/iPod/F24/WHYO.mp3")); //ID3v23 Tag
			//ID3v2Reader reader = new ID3v2Reader(new File("C:/Users/Public/Music/Sample Music/Sleep Away.mp3"));
			//ID3v2Reader reader = new ID3v2Reader(new File("J:/Desktop/Desktop/E. S. Posthumus - Menouthis.mp3")); //ID3v22 Tag
			ID3v2Reader reader = new ID3v2Reader(new File("C:/Users/Bienfait Sindi/Desktop/Maybe.mp3")); //ID3v24
			//ID3v2Reader reader = new ID3v2Reader(new File("I:/Documents/Music/Pop/Leona Lewis/Spirit/02 - Whatever it Takes.mp3"));
			ID3v2Tag<? extends ID3v2Frame> tag = reader.read();
//			if (tag.getMajorVersion() == 3) {
//				tag = (ID3v23Tag) tag;
//			}
			System.out.println("Header Position: " + tag.getHeaderPosition());
			System.out.println("ID3v2." + tag.getMajorVersion() + "." + tag.getRevisionNumber());
			System.out.println("Tag Size: " + tag.getSize() + " bytes");
			System.out.println("Unsynchronized? " + (tag.isUnsynchronizationSet() ? "Yes" : "No"));
			System.out.println("Found: " + tag.getTotalFrames() + " frames (total sizes = " + tag.getTotalFrameSizes() + " bytes).");
			System.out.println("Padding read: " + tag.getPaddingReadSize() + " bytes");
			
			if(tag.getMajorVersion() == 4) {
				System.out.println("Footer present? " + (((ID3v24Tag)tag).isFooterPresent() ? "Yes" : "No"));
			}
		
			System.out.println();
			System.out.println("Title: " + tag.getTitle());
			System.out.println("Artist: " + tag.getArtist());
			System.out.println("Album: " + tag.getAlbum());
			System.out.println("Genre: " + tag.getGenre());
			System.out.println("BPM: " + tag.getBPM());
			System.out.println("Year: " + tag.getYear());
			System.out.println("Composer: " + tag.getComposer());
			System.out.println("Comment: " + tag.getComment());
			System.out.println("Track #:" + tag.getTrackNumber() + ", Disc #: " + tag.getDiscNumber());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ID3Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
