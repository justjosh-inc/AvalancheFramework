package avalanche.models;

import avalanche.component.Mesh;
import avalanche.loader.MeshLoader;

public class Square extends Mesh{

	private static final float[] normals = {};

	private static final float[] vertices = { -0.5f, -0.5f, 0.0f, 
			0.5f, -0.5f, 0.0f, 
			0.5f, 0.5f, 0.0f, 

			-0.5f, -0.5f, 0.0f, 
			0.5f, 0.5f, 0.0f, 
			-0.5f, 0.5f, 0.0f
	};

	private static final float[] textureCoords = {
			0.0f, 1.0f,
			1.0f, 1.0f, 
			1.0f, 0.0f, 

			0.0f, 1.0f, 
			1.0f, 0.0f,
			0.0f, 0.0f 
	};

	private static final int[] indices = {
			0, 1, 2,
			3, 4, 5 
	};
	
	public Square() {
		super(MeshLoader.load(vertices, normals, textureCoords, indices).getID(),vertices,normals,textureCoords,indices);
	}

}
