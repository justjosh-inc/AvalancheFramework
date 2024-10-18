package test;

import org.joml.Vector3f;

import avalanche.components.Texture;
import avalanche.components.Transform;
import avalanche.engine.maths.Maths;
import avalanche.engine.maths.Matrix4f;
import avalanche.engine.shader.StaticShader;
import avalanche.entity.Entity;
import avalanche.model.Square;

public class Player extends Entity{

	Texture texture = new Texture("src/avalanche/res/a.png");
	Transform transform = new Transform(new Vector3f(),0f,0f,0f,0f);
	Square model = new Square();
	
	StaticShader shader = new StaticShader();
	
	public Player() {
		this.addComponent(texture);
		this.addComponent(transform);
		
		shader.addAttribute(0, "position");
		//shader.addAttribute(1, "textCoords");
		//shader.loadMatrix(shader.getUniform("transformationMatrix"),transform.matrix);
	}
	
	public void update() {
		transform.increasePosition(10, 0, 0);
		String pos = "" + transform.matrix.toString();
		System.out.println(pos);
	}
	
	public void render() {
		model.render();
	}
}
