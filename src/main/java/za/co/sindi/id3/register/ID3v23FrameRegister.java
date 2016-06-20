/**
 * 
 */
package za.co.sindi.id3.register;

import java.util.HashMap;

import za.co.sindi.id3.frame.ID3v23Frame;
import za.co.sindi.id3.frame.id3v23.ID3v23CommentFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23GeneralEncapsulationObjectFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23PictureFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23PrivateFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23TextFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23URIFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23UserDefinedTextFrame;
import za.co.sindi.id3.frame.id3v23.ID3v23UserDefinedURIFrame;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 */
public class ID3v23FrameRegister extends ID3v2FrameRegister<ID3v23Frame> {

	/**
	 * 
	 */
	public ID3v23FrameRegister() {
		// TODO Auto-generated constructor stub
		id3v2FrameRegister = new HashMap<String, Class<? extends ID3v23Frame>>();
		
		id3v2FrameRegister.put("APIC", ID3v23PictureFrame.class);
		id3v2FrameRegister.put("COMM", ID3v23CommentFrame.class);
		id3v2FrameRegister.put("GEOB", ID3v23GeneralEncapsulationObjectFrame.class);
		id3v2FrameRegister.put("PRIV", ID3v23PrivateFrame.class);
		id3v2FrameRegister.put("TALB", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TBPM", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TCOM", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TCON", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TCOP", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TDAT", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TDLY", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TENC", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TEXT", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TFLT", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TIME", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TIT1", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TIT2", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TIT3", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TKEY", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TLAN", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TLEN", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TMED", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TOAL", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TOFN", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TOLY", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TOPE", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TORY", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TOWN", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPE1", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPE2", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPE3", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPE4", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPOS", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TPUB", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TRCK", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TRDA", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TRSN", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TRSO", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TSIZ", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TSRC", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TSSE", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TYER", ID3v23TextFrame.class);
		id3v2FrameRegister.put("TXXX", ID3v23UserDefinedTextFrame.class);
		id3v2FrameRegister.put("WCOM", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WCOP", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WOAF", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WOAR", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WOAS", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WORS", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WPAY", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WPUB", ID3v23URIFrame.class);
		id3v2FrameRegister.put("WXXX", ID3v23UserDefinedURIFrame.class);
	}	
}
