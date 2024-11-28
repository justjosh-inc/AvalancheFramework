package avalanche.component;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;

public class Texture {

	// NOT IN  USE YET	
	public static enum filterType{
		DEFAULT,
		BLURY
	}
	
	private int ID;
	private boolean transparency = false;
	
	public Texture(int iD) {
		ID = iD;
		
	} 
	
	public boolean hasTransparency() {
		return transparency;
	}

	public void setTransparency(boolean transparency) {
		this.transparency = transparency;
	}

	public int getID() {
		return ID;
	}

}
