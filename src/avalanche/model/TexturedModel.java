package avalanche.model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import avalanche.components.Texture;

public class TexturedModel extends Square{

	private Texture texture;
	
	public TexturedModel(String imagePath) {
		texture = new Texture(imagePath);
	}
	
	public void render() {
	    glBindVertexArray(mesh.getVaoID());
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);
	    
	    glActiveTexture(GL_TEXTURE0);
	    glBindTexture(GL_TEXTURE_2D, texture.getID());
	    
	    // Use glDrawElements to render using the indices
	    glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
	    
	    glDisableVertexAttribArray(0);
	    glDisableVertexAttribArray(1);
	    glBindVertexArray(0);
	}
}
