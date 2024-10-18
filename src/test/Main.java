package test;

import static avalanche.engine.utils.Constants.*;

import avalanche.components.Texture;
import avalanche.engine.core.Renderer;
import avalanche.engine.core.Window;
import avalanche.engine.shader.StaticShader;
import avalanche.entity.EntityManager;
import avalanche.model.Square;
import avalanche.model.TexturedModel;
import avalanche.model.Triangle;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		window.init();
		window.setPosition(WINDOW_CENTERED_X, WINDOW_CENTERED_Y);
		
		Renderer renderer = new Renderer();

		EntityManager manager = new EntityManager();
		
		Player player = new Player(); 
		manager.addEntity(player);
		
		
		while(!window.shouldClose()) {
			renderer.clear(BACKGROUND_COLOUR);
			
			manager.update();
			manager.render();
			
			window.update();
		}
		manager.cleanUp();
		window.close();
	}

}
