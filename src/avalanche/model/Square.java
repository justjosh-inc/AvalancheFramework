package avalanche.model;

import avalanche.components.Mesh;

public class Square extends BaseModel{ 

	public static float[] vertices = {
		    -0.5f, 0.5f, 0f,
		    -0.5f, -0.5f, 0f,
		    0.5f, -0.5f, 0f,
		    0.5f, 0.5f, 0f
	};
	public static int[] indicies = {
			0,1,3,
			3,1,2
	};
	
	public static float[] textureCoords = {
		    0f, 0f,  // Top-left (now bottom)
		    0f, 1f,  // Bottom-left (now top)
		    1f, 1f,  // Bottom-right (now top)
		    1f, 0f   // Top-right (now bottom)
	};
	
	public Square() {
		this.setVerticies(vertices);
		this.setTextCoords(textureCoords);
		this.setIndicies(indicies);
		this.mesh = loader.loadToVAO(this.getVerticies(),
				this.getTextCoords(),
				this.getIndicies());
	}

}
