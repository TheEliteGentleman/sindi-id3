/**
 * 
 */
package za.co.sindi.id3.factory;

import za.co.sindi.id3.builder.ID3v22Builder;
import za.co.sindi.id3.builder.ID3v23Builder;
import za.co.sindi.id3.builder.ID3v24Builder;
import za.co.sindi.id3.builder.ID3v2Builder;
import za.co.sindi.id3.frame.ID3v2Frame;
import za.co.sindi.id3.tag.ID3v2Tag;

/**
 * @author Bienfait Sindi
 * @since 25 June 2010
 *
 */
public class ID3v2BuilderFactoryImpl implements ID3v2BuilderFactory {

	/**
	 * Default constructor.
	 */
	public ID3v2BuilderFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see com.neurologic.id3.factory.ID3v2BuilderFactory#createID3v2Builder(int)
	 */
	@Override
	public ID3v2Builder<? extends ID3v2Tag<? extends ID3v2Frame>> createID3v2Builder(int majorNumber) {
		// TODO Auto-generated method stub
		switch (majorNumber) {
			case 2:
				return new ID3v22Builder();
			
			case 3: 
				return new ID3v23Builder();
			
			case 4: 
				return new ID3v24Builder();
				
			default: return null;
		}
	}
}
