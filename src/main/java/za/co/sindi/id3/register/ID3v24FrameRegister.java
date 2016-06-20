/**
 * 
 */
package za.co.sindi.id3.register;

import java.util.HashMap;

import za.co.sindi.id3.frame.ID3v24Frame;
import za.co.sindi.id3.frame.id3v24.ID3v24CommentFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24GeneralEncapsulationObjectFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24PictureFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24PrivateFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24TextFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24URIFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24UserDefinedTextFrame;
import za.co.sindi.id3.frame.id3v24.ID3v24UserDefinedURIFrame;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v24FrameRegister extends ID3v2FrameRegister<ID3v24Frame> {

	/**
	 * 
	 */
	public ID3v24FrameRegister() {
		// TODO Auto-generated constructor stub
		id3v2FrameRegister = new HashMap<String, Class<? extends ID3v24Frame>>();
		
		id3v2FrameRegister.put("APIC", ID3v24PictureFrame.class);
		id3v2FrameRegister.put("COMM", ID3v24CommentFrame.class);
		id3v2FrameRegister.put("GEOB", ID3v24GeneralEncapsulationObjectFrame.class);
		id3v2FrameRegister.put("PRIV", ID3v24PrivateFrame.class);
		id3v2FrameRegister.put("TALB", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TBPM", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TCOM", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TCON", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TCOP", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDEN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDLY", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDOR", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDRC", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDRL", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TDGT", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TENC", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TEXT", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TFLT", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TIPL", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TIT1", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TIT2", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TIT3", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TKEY", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TLAN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TLEN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TMCL", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TMED", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TMOO", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TOAL", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TOFN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TOLY", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TOPE", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TOWN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPE1", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPE2", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPE3", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPE4", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPOS", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPRO", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TPUB", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TRCK", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TRSN", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TRSO", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSOA", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSOP", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSOT", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSRC", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSSE", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TSST", ID3v24TextFrame.class);
		id3v2FrameRegister.put("TXXX", ID3v24UserDefinedTextFrame.class);
		id3v2FrameRegister.put("WCOM", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WCOP", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WOAF", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WOAR", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WOAS", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WORS", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WPAY", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WPUB", ID3v24URIFrame.class);
		id3v2FrameRegister.put("WXXX", ID3v24UserDefinedURIFrame.class);
	}
}
