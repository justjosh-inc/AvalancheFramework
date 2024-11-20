package avalanche.loader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import avalanche.component.Texture;
import avalanche.utils.Logger;
 
public class TextureLoader {

	private static List<Integer> textures = new ArrayList<Integer>();

	public static int load(String filePath) {
		int width, height;
		ByteBuffer buffer;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer c = stack.mallocInt(1);

			buffer = STBImage.stbi_load(filePath, w, h, c, 4);
			if (buffer == null) {
				Logger.toConsole(Logger.logLevel.ERROR,
						"Image File " + filePath + " not loaded " + STBImage.stbi_failure_reason(), true);
			}

			width = w.get();
			height = h.get();
		}

		int id = glGenTextures();
		textures.add(id);
		
		glBindTexture(GL_TEXTURE_2D, id);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		glGenerateMipmap(GL_TEXTURE_2D);
		STBImage.stbi_image_free(buffer);
		return id;
	}

	public static void cleanUp() {
		for (int texture : textures) {
			glDeleteTextures(texture);
		}
	}
}
