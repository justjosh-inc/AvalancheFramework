package avalanche.model;

import org.joml.Matrix4f;

import avalanche.components.Texture;
import avalanche.engine.maths.Maths;

public class TexturedModel extends Square{

	private Texture texture;
	
	public TexturedModel(Texture texture) {
		this.texture = texture;
	}
	
	public void render() {
	    texture.render();
	}
}
