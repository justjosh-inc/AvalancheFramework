package avalanche.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import avalanche.component.Mesh;

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
		if (mesh.getShader() != null) {
			mesh.getShader().bind();
		}

		glBindVertexArray(mesh.getID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);

		if (mesh.getShader() != null) {
			mesh.getShader().setUniform("baseColour", mesh.getMaterial().getBaseColor());
		}
		if (mesh.getTexture() != null) {
			if (mesh.getShader() != null) {
				mesh.getShader().setUniform("textureSampler", 0);
				mesh.getShader().setUniform("has_transparency", mesh.getTexture().hasTransparency());
			}

			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, mesh.getTexture().getID());
		}

		if (mesh.getIndices().length <= 0) {
			glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
		} else {
			glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
		}

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);

		if (mesh.getShader() != null) {
			mesh.getShader().unbind();
		}
	}
}
