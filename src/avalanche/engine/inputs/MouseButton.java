package avalanche.engine.inputs;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MouseButton extends GLFWMouseButtonCallback{

	public static boolean[] buttons = new boolean[8];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = (action != GLFW_RELEASE);
	}

	public static boolean isButtonPressed(int buttonCode) {
		return buttons[buttonCode];
	}
	
	public void close() {
		this.free();
	}
}