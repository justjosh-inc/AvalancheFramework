package test;

import component.Mesh;
import core.Renderer;
import core.Shader;
import core.Window;
import loader.MeshLoader;
import utils.Utils;

public class Main {

	public static void main(String[] args) {
		Window window = Window.get();
		window.init();

		float[] vertices = { 
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f,0.5f, 0f };
		
		float[] normals = {};
		float[] textCoords = {};
		int[] indices = {};

		Renderer renderer = Renderer.get();

		Shader shader = new Shader();
		shader.createVertexShader(Utils.getStringFromFile("src/main/resources/shaders/default.vert"));
		shader.createFragmentShader(Utils.getStringFromFile("src/main/resources/shaders/default.frag"));
		shader.link();
		
		Mesh mesh = MeshLoader.load(vertices, normals, textCoords, indices);
		
		while (!window.shouldClose()) {
			renderer.clear(20, 40, 80, 255);
			
			shader.bind();
			renderer.render(mesh);
			shader.unbind();
			
			window.update();
		}

		window.close();
	}

}
