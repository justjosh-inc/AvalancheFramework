package avalanche.inputs;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Keybord extends GLFWKeyCallback{

	public static boolean[] keys = new boolean[350];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = (action != GLFW_RELEASE);
	}
	
	public static boolean isKeyPressed(int key) {
		return Keybord.keys[key];
	}

}
