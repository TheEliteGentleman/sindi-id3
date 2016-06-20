/**
 * 
 */
package za.co.sindi.id3.register;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.apache.log4j.Logger;

import za.co.sindi.id3.frame.ID3v2Frame;

/**
 * @author Bienfait Sindi
 * @since 28 June 2010
 *
 * @see ID3v23FrameRegister
 */
public abstract class ID3v2FrameRegister<T extends ID3v2Frame> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	protected Map<String, Class<? extends T>> id3v2FrameRegister;
	
	public final boolean containsFrame(String frameID) {
		if (id3v2FrameRegister != null) {
			return id3v2FrameRegister.containsKey(frameID);
		}
		
		return false;
	}
	
	public final T getDeclaredID3v2Frame(String frameID) {
		if (containsFrame(frameID)) {	
			try {
				Class<? extends T> clazz = id3v2FrameRegister.get(frameID);
				Constructor<? extends T> constructor = clazz.getConstructor(String.class);
				return constructor.newInstance(frameID);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		
		return null;
	}
}
