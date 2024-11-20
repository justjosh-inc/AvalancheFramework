package avalanche.test;

import avalanche.component.Mesh;
import avalanche.component.Texture;
import avalanche.core.Renderer;
import avalanche.core.Shader;
import avalanche.core.Window;
import avalanche.loader.MeshLoader;
import avalanche.loader.TextureLoader;
import avalanche.utils.Utils;

public class Main {

	public static void main(String[] args) {
		Window window = Window.get();
		window.setPosition(Window.CENTER, Window.CENTER);
		window.init();

		float[] normals = {};

		// Define vertices for the square (two triangles)
		float[] vertices = { -0.5f, -0.5f, 0.0f, // Vertex 1 (bottom-left)
				0.5f, -0.5f, 0.0f, // Vertex 2 (bottom-right)
				0.5f, 0.5f, 0.0f, // Vertex 3 (top-right)

				-0.5f, -0.5f, 0.0f, // Vertex 1 (bottom-left)
				0.5f, 0.5f, 0.0f, // Vertex 3 (top-right)
				-0.5f, 0.5f, 0.0f // Vertex 4 (top-left)
		};

		// Define texture coordinates for the square
		float[] textureCoords = {
				// First triangle (bottom-left triangle)
				0.0f, 1.0f, // Bottom-left corner of texture (flipped)
				1.0f, 1.0f, // Bottom-right corner of texture (flipped)
				1.0f, 0.0f, // Top-right corner of texture (flipped)

				// Second triangle (top-left triangle)
				0.0f, 1.0f, // Bottom-left corner of texture (flipped)
				1.0f, 0.0f, // Top-right corner of texture (flipped)
				0.0f, 0.0f // Top-left corner of texture (flipped)
		};

		// Define the indices for the two triangles
		int[] indices = { 0, 1, 2, // Triangle 1
				3, 4, 5 // Triangle 2
		};

		Renderer renderer = Renderer.get();

		Shader shader = new Shader();
		shader.createVertexShader(Utils.getStringFromFile("src/main/resources/shaders/default.vert"));
		shader.createFragmentShader(Utils.getStringFromFile("src/main/resources/shaders/default.frag"));
		shader.link();
 
		Mesh mesh = MeshLoader.load(vertices, normals, textureCoords, indices);
		Texture temp = new Texture(TextureLoader.load("src/main/resources/textures/temp-image.png"));
		Texture icon = new Texture(TextureLoader.load("src/main/resources/icons/main-small-circle.png"));
		
		mesh.setShader(shader);
		mesh.setTexture(temp);
		
		int i = 0;
		
		while (!window.shouldClose()) {
			renderer.clear(20, 40, 80, 255);

			i++;
			
			if (i >= 100) {
				mesh.setTexture(icon);
			}else {
				mesh.setTexture(temp);
			}
			
			if (i > 200) {
				i = 0;
			}
			
			
			renderer.render(mesh);
			mesh.setTexture(icon);

			window.update();
		}
		window.close();
	}

}
