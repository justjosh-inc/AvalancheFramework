package avalanche.engine.utils;

public class Constants {

	// ----- Window Constants -----
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	public static final String WINDOW_TITLE = "Avalance Engine";
	public static final boolean WINDOW_VSYNC = false;
	public static final boolean WINDOW_FULLSCREEN = false;
	public static final boolean WINDOW_RESIZEABLE = false;

	public static final int WINDOW_CENTERED_X = -1;
	public static final int WINDOW_CENTERED_Y = -1;

	// ----- Renderer Constants -----
	public static final float[] BACKGROUND_COLOUR = { 0.2f, 0.4f, 0.8f, 1.0f };

	// ----- Clock Constants -----
	public static final int NO_FPS = 999999999;
	
	// ----- Shader constants -----
	public static final String VERTEX_FILE = "src/avalanche/engine/shader/default.vert";
	public static final String FRAG_FILE = "src/avalanche/engine/shader/default.frag";
}