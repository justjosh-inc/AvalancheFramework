package avalanche.engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;


public class Renderer {
	
	private static  Renderer instance = null;
	
	public static Renderer get() {
		if (instance == null) {
			instance = new Renderer();
		}
		
		return instance;
	}

	public void clear(float r,float g,float b) {
		glClearColor(r,g,b,1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void clear(float[] rgb) {
		glClearColor(rgb[0],rgb[1],rgb[2],1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
}
