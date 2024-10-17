package test;

import avalanche.audio.Sound;
import avalanche.components.Texture;
import avalanche.engine.core.Renderer;
import avalanche.engine.core.Window;
import avalanche.engine.inputs.Key;
import avalanche.engine.inputs.MouseButton;
import avalanche.engine.shader.StaticShader;
import avalanche.engine.utils.Constants;
import avalanche.model.TexturedModel;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		window.setSize(1080, 720);
		window.setResizeable(true);
		window.init();
		window.setPosition(Constants.WINDOW_CENTERED_X, Constants.WINDOW_CENTERED_Y);
		
		Sound sound = new Sound("src/avalanche/res/b.wav");
		
		Texture texture = new Texture("src/avalanche/res/a.png");
		
		Renderer renderer = new Renderer();
		TexturedModel model = new TexturedModel(texture);
		
		StaticShader shader = new StaticShader("src/avalanche/engine/shader/defaultImage.vert","src/avalanche/engine/shader/defaultImage.frag");
		shader.addAttribute(0, "position");
		shader.addAttribute(1, "textCoords");
		
		
		while (!window.shouldClose()) {
			renderer.clear(Constants.BACKGROUND_COLOUR);
			if (MouseButton.isButtonPressed(Key.MB_LEFT)) {
				sound.play();
			}
			shader.start();
			model.render();
			shader.stop();
			
			window.update();
		}
		
		window.close();
	}

}
