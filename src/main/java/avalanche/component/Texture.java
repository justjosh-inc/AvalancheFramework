package avalanche.component;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

	// NOT IN  USE YET	
	public static enum filterType{
		DEFAULT,
		BLURY
	}
	
	private int ID;
	
	public Texture(int iD) {
		ID = iD;
		
	} 
	
	public int getID() {
		return ID;
	}
	
}
