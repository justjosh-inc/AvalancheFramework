package core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.opengl.GL;

public class Window {

	// basic variables
	private int x, y, width, height;
	private String title;
	private boolean visible, fullscreen, vsync, resizeable, boarderless;

	private static Window instance = null;
	private long pointer;

	// static window positions
	public static final int CENTER = -1;

	public static final int TOP = -1;
	public static final int BOTTOM = -1;

	public static final int LEFT = -1;
	public static final int RIGHT = -1;

	private Window() {
		this.x = 0;
		this.y = 35;
		this.width = 800;
		this.height = 800;
		this.title = "Avalanche Engine";
		this.visible = true;
		this.fullscreen = false;
		this.vsync = true;
		this.resizeable = false;
		this.boarderless = false;
	}

	public static Window get() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

	// SETTERS
	// SETTERS
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;

		if (pointer != NULL) {
			glfwSetWindowSize(pointer, this.width, this.height);
		}
	}

	public void setTitle(String title) {
		this.title = title;

		if (pointer != NULL) {
			glfwSetWindowTitle(pointer, this.title);
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (x == CENTER) {
			this.x = (monitorSize.width - this.width) / 2;
		} else if (x == LEFT) {
			this.x = 0;
		} else if (x == RIGHT) {
			this.x = (monitorSize.width - this.width);
		}

		if (y == CENTER) {
			this.y = (monitorSize.height - this.height) / 2;
		} else if (y == TOP) {
			this.y = 35;
		} else if (y == BOTTOM) {
			this.y = (monitorSize.height - this.height);
		}

		if (pointer != NULL) {
			glfwSetWindowPos(pointer, this.x, this.y);
		}
	}

	// MAIN FUNCTIONS
	public void init() {
		if (!glfwInit()) {
			// log ERROR
			System.exit(-1);
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		pointer = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
		if (pointer == NULL) {
			//log ERROR
			System.exit(-1);
		}
		
		this.setPosition(this.x, this.y);
		
		
		glfwMakeContextCurrent(pointer);
		GL.createCapabilities();
		
		glfwShowWindow(pointer);
	}
	
	public void update() {
		glfwSwapBuffers(pointer);
		glfwPollEvents();
	}
	
	public void close() {
		glfwDestroyWindow(pointer);
		glfwTerminate();
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(pointer);
	}
}
