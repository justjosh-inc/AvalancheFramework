package test;

import avalanche.engine.core.Renderer;
import avalanche.engine.core.Window;
import avalanche.engine.utils.Constants;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		window.init();
		
		Renderer renderer = new Renderer();
		
		while (!window.shouldClose()) {
			renderer.clear(Constants.BACKGROUND_COLOUR);
			
			window.update();
		}
		
		window.close();

	}

}
