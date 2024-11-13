package core;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import component.Mesh;

public class Renderer {

	private static Renderer instance = null;

	private Renderer() {
	}

	public static Renderer get() {
		if (instance == null) {
			instance = new Renderer();
		}

		return instance;
	}

	public void clear(int r, int g, int b, int a) {
		float floatR = r / 255.0f;
		float floatG = g / 255.0f;
		float floatB = b / 255.0f;
		float floatA = a / 255.0f;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(floatR, floatG, floatB, floatA);
	}

	public void render(Mesh mesh) {
		glBindVertexArray(mesh.getID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
	}
}
