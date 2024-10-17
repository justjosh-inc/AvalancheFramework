package avalanche.engine.inputs;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseMovement extends GLFWCursorPosCallback{

    private static double xpos, ypos;  
    private static double lastX, lastY; 
    private static boolean firstMouseMove = true; 
	
    @Override
	public void invoke(long window, double xPos, double yPos) {
		this.xpos = xPos;
		this.ypos = yPos;
		
		if (firstMouseMove) {
            lastX = xpos;
            lastY = ypos;
            firstMouseMove = false;
        }
	}

    public static double getX() {
        return xpos;
    }

    public static double getY() {
        return ypos;
    }

    public static double getDeltaX() {
        double deltaX = xpos - lastX;
        lastX = xpos;
        return deltaX;
    }

    public static double getDeltaY() {
        double deltaY = ypos - lastY;
        lastY = ypos;
        return deltaY;
    }

    public void close() {
        this.free();
    }
}
