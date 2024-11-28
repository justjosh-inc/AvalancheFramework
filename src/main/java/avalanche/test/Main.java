package avalanche.test;

import avalanche.component.Material;
import avalanche.component.Texture;
import avalanche.core.Renderer;
import avalanche.core.Shader;
import avalanche.core.Window;
import avalanche.inputs.Key;
import avalanche.inputs.Keybord;
import avalanche.loader.TextureLoader;
import avalanche.models.Square;
import avalanche.utils.Utils;

public class Main {

	public static void main(String[] args) {
		Window window = Window.get();
		window.setPosition(Window.CENTER, Window.CENTER);
		window.init();

		Renderer renderer = Renderer.get();

		Shader shader = new Shader();
		shader.createVertexShader(Utils.getStringFromFile("src/main/resources/shaders/default.vert"));
		shader.createFragmentShader(Utils.getStringFromFile("src/main/resources/shaders/default.frag"));
		shader.link();
 
		Square mesh = new Square();
		Texture temp = new Texture(TextureLoader.load("src/main/resources/textures/temp-image.png"));
		temp.setTransparency(true);
		
		Texture icon = new Texture(TextureLoader.load("src/main/resources/icons/main-small-circle.png"));
		icon.setTransparency(true);
		
		
		mesh.setShader(shader);
		mesh.setTexture(temp);
		
		
		while (!window.shouldClose()) {
			renderer.clear(20, 40, 80, 255);
			
			renderer.render(mesh);
			
			if (Keybord.keys[Key.K_D]) {
				mesh.setTexture(icon);
			}else {
				mesh.setTexture(temp);
			}

			window.update();
		}
		window.close();
	}

}
