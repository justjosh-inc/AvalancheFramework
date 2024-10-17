package test;

import avalanche.components.Mesh;
import avalanche.engine.core.Renderer;
import avalanche.engine.core.Window;
import avalanche.engine.shader.StaticShader;
import avalanche.engine.time.Clock;
import avalanche.engine.utils.Constants;
import avalanche.engine.utils.Loader;

public class Main {
	
	public static Window window;
	public static Renderer renderer;
	public static Clock clock;
	public static Loader loader;
	public static Mesh mesh;
	public static StaticShader shader;

	private static float[] vertices = {
		    -0.5f, 0.5f, 0f,
		    -0.5f, -0.5f, 0f,
		    0.5f, -0.5f, 0f,
		    0.5f, 0.5f, 0f
	};
	private static int[] indicies = {
			0,1,3,
			3,1,2
	};
	
	public static void main(String[] args) {
		Window window = new Window();
		window.setResizeable(false);
		window.setResizeable(true);
		window.init();
		window.setPosition(Constants.WINDOW_CENTERED_X, Constants.WINDOW_CENTERED_Y);

		clock = new Clock();
		
		renderer = new Renderer();
		loader = new Loader();
		mesh = loader.loadToVAO(vertices,indicies);
		
		Renderer renderer = new Renderer();
		
		shader = new StaticShader();
		shader.addAttribute(0, "position");
		
		while (!window.shouldClose()) {
			clock.tick(Constants.NO_FPS);
			renderer.clear(Constants.BACKGROUND_COLOUR);
			shader.start();
			renderer.render(mesh);
			shader.stop();
			window.update();
		}
		shader.cleanUp();
		loader.cleanUp();
		window.close();

	}

}
