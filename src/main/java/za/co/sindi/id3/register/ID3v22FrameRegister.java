/**
 * 
 */
package za.co.sindi.id3.register;

import java.util.HashMap;

import za.co.sindi.id3.frame.ID3v22Frame;
import za.co.sindi.id3.frame.id3v22.ID3v22CommentFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22GeneralEncapsulationObjectFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22PictureFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22TextFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22URIFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22UserDefinedTextFrame;
import za.co.sindi.id3.frame.id3v22.ID3v22UserDefinedURIFrame;

/**
 * @author Bienfait Sindi
 * @since 29 June 2010
 *
 */
public class ID3v22FrameRegister extends ID3v2FrameRegister<ID3v22Frame> {

	/**
	 * 
	 */
	public ID3v22FrameRegister() {
		// TODO Auto-generated constructor stub
		id3v2FrameRegister = new HashMap<String, Class<? extends ID3v22Frame>>();
		
		id3v2FrameRegister.put("COM", ID3v22CommentFrame.class);
		id3v2FrameRegister.put("GEO", ID3v22GeneralEncapsulationObjectFrame.class);
		id3v2FrameRegister.put("PIC", ID3v22PictureFrame.class);
		id3v2FrameRegister.put("TAL", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TBP", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TAL", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TCM", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TCO", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TCR", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TDA", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TDY", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TEN", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TFT", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TIM", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TKE", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TLA", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TLE", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TMT", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TOA", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TOF", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TOL", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TOR", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TOT", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TP1", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TP2", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TP3", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TP4", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TPA", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TPB", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TRC", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TRD", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TRK", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TSI", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TSS", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TT1", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TT2", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TT3", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TXT", ID3v22TextFrame.class);
		id3v2FrameRegister.put("TXX", ID3v22UserDefinedTextFrame.class);
		id3v2FrameRegister.put("TYE", ID3v22TextFrame.class);
		id3v2FrameRegister.put("WAF", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WAR", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WAS", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WCM", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WCP", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WPB", ID3v22URIFrame.class);
		id3v2FrameRegister.put("WXX", ID3v22UserDefinedURIFrame.class);
	}
}
