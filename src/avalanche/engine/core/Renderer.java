package avalanche.engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import avalanche.components.Mesh;


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
	
	public void render(Mesh model) {
	    glBindVertexArray(model.getVaoID());
	    glEnableVertexAttribArray(0);
	    
	    // Use glDrawElements to render using the indices
	    glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
	    
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	}
}
