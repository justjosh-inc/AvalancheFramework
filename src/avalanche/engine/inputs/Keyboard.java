package avalanche.engine.inputs;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback{

	private static Keyboard instance;
    private boolean keyPressed[] = new boolean[350];

    public Keyboard() {

    }

    public static Keyboard get() {
        if (Keyboard.instance == null) {
        	Keyboard.instance = new Keyboard();
        }

        return Keyboard.instance;
    }

    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
		
	}
}
