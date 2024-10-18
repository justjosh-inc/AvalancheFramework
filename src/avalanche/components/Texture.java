package avalanche.components;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import avalanche.engine.shader.StaticShader;
import avalanche.engine.utils.Util;

public class Texture extends Component{

	private int ID;
	private int width,height;
	 
	public Texture(String path) {
		super(Component.TYPE_TEXTURE);
		ID = load(path);
	}
	
	private int load(String path) {
		int [] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0,width,height,pixels,0,width);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		int[] data = new int[width * height];
		for (int i = 0;i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		int result = glGenTextures();
		bind();
		glBindTexture(GL_TEXTURE_2D, result);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, Util.storeDataInIntBuffer(data));
		glBindTexture(GL_TEXTURE_2D, 0);
		
		return result;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, ID);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
}
