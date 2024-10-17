package avalanche.engine.core;

import static avalanche.engine.utils.Constants.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import avalanche.engine.inputs.Keyboard;
import avalanche.engine.inputs.MouseButton;
import avalanche.engine.inputs.MouseMovement;

public class Window {

	private static Window instance = null;

	public static final String VERSION = "0.0.3";

	private int     width  =     WINDOW_WIDTH;
	private int     height =     WINDOW_HEIGHT;
	private String  title  =     WINDOW_TITLE;
	private boolean vSync  =     WINDOW_VSYNC;
	private boolean resizeable = WINDOW_RESIZEABLE;
	private boolean Fullscreen = WINDOW_FULLSCREEN;
	private int     posX =       50;
	private int     posY =       50;

	private Keyboard keyboard;
	private MouseButton mouseButton;
	private MouseMovement mouseMovement;
	
	private long data;

	public static Window get() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

	public Window() {
		if (instance == null) {
			instance = this;
		}
	}
	
	public void setPosition(int x, int y) {
		if (data == NULL) {
			throw new RuntimeException(
					"\nFailed to set the window position \nmake sure to use this function after init()");
		}

		this.posX = x;
		this.posY = y;

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		if (x == WINDOW_CENTERED_X) {
			posX = (vidmode.width() - this.width) / 2;
		}

		if (y == WINDOW_CENTERED_Y) {
			posY = (vidmode.height() - this.height) / 2;
		}

		glfwSetWindowPos(data, posX, posY);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setTitle(String title) {
		this.title = title;
		glfwSetWindowTitle(data, title);
	}

	public void setVSync(boolean vSync) {
		this.vSync = vSync;
	}

	public void setResizeable(boolean resizeable) {
		this.resizeable = resizeable;
	}

	public void setFullscreen(boolean fullscreen) {
		this.Fullscreen = fullscreen;
	}

	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		System.out.println("======================\n" 
				+ "VERSIONS:\n" 
				+ "Avalanche - "+ VERSION + "\n" 
				+ "GLFW      - " + Version.getVersion() + "\n" 
				+ "==============================\n");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		if (resizeable) {
			glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		} else {
			glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		}

		data = glfwCreateWindow(width, height, title, NULL, NULL);
		if (data == NULL) {
			throw new RuntimeException("Failed to make Avalance Window");
		}

		glfwSetWindowPos(data, posX, posY);

		if (Fullscreen) {
			glfwSetWindowPos(data, 0, 0);
			GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowSize(data, vidMode.width(), vidMode.height());
		}

		glfwMakeContextCurrent(data);
		
		keyboard = new Keyboard();
		mouseButton = new MouseButton();
		mouseMovement = new MouseMovement();
		
		glfwSetKeyCallback(data, keyboard);
		glfwSetMouseButtonCallback(data, mouseButton);
		glfwSetCursorPosCallback(data, mouseMovement);

		if (vSync) {
			glfwSwapBuffers(data);
		}

		GL.createCapabilities();
		
		setResizeCallback();
		glfwShowWindow(data);
	}
	
	private void setResizeCallback() {
	    glfwSetFramebufferSizeCallback(data, (window, width, height) -> {
	        System.out.println("Resizing to: " + width + "x" + height);
	        
	        this.width = width;
	        this.height = height;
	        glViewport(0, 0, width, height); // Adjust the viewport to the new dimensions
	    });
	}

	
	public void update() {
		glfwSwapBuffers(data);

		glfwPollEvents();
	}

	public void close() {
		keyboard.close();
		mouseButton.close();
		mouseMovement.close();

		glfwDestroyWindow(data);
		glfwSetErrorCallback(null).free();
		glfwTerminate();
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(data);
	}

	public long getData() {
		return data;
	}
}
