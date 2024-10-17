package test;

import avalanche.engine.core.Renderer;
import avalanche.engine.core.Window;
import avalanche.engine.shader.StaticShader;
import avalanche.engine.utils.Constants;
import avalanche.model.TexturedModel;
import avalanche.model.Triangle;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		window.setSize(1080, 720);
		window.init();
		window.setPosition(Constants.WINDOW_CENTERED_X, Constants.WINDOW_CENTERED_Y);
		
		Renderer renderer = new Renderer();
		TexturedModel model = new TexturedModel("src/avalanche/res/a.png");
		
		StaticShader shader = new StaticShader("src/avalanche/engine/shader/defaultImage.vert","src/avalanche/engine/shader/defaultImage.frag");
		shader.addAttribute(0, "position");
		shader.addAttribute(1, "textCoords");
		
		while (!window.shouldClose()) {
			renderer.clear(Constants.BACKGROUND_COLOUR);
			
			shader.start();
			model.render();
			shader.stop();
			
			window.update();
		}
		
		window.close();
	}

}
