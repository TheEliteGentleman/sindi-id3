/**
 * 
 */
package za.co.sindi.id3.tag;

import za.co.sindi.id3.ID3Tag;
import za.co.sindi.id3.constants.ID3v1Constants;


/**
 * @author Bienfait Sindi
 * @since 24 June 2010
 *
 */
public class ID3v1Tag extends ID3Tag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549972392812211581L;
	private String title;
	private String artist;
	private String album;
	private String year;
	private String comment;
	private int trackNumber = 0;
	private int genre;
	
	/**
	 * 
	 */
	public ID3v1Tag() {
		// TODO Auto-generated constructor stub
		setHeader(ID3v1Constants.HEADER);
		setSize(ID3v1Constants.TAG_SIZE);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the trackNumber
	 */
	public int getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @param trackNumber the trackNumber to set
	 */
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @return the genre
	 */
	public int getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(int genre) {
		this.genre = genre;
	}
}
