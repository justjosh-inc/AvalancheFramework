package avalanche.model;

import avalanche.components.Mesh;

public class Square extends BaseModel{

	private static float[] vertices = {
		    -0.5f, 0.5f, 0f,
		    -0.5f, -0.5f, 0f,
		    0.5f, -0.5f, 0f,
		    0.5f, 0.5f, 0f
	};
	private static int[] indicies = {
			0,1,3,
			3,1,2
	};
	
	public Square() {
		this.setVerticies(vertices);
		this.setTextCoords(vertices);
		this.setIndicies(indicies);
		this.mesh = loader.loadToVAO(this.getVerticies(),
				this.getTextCoords(),
				this.getIndicies());
	}

}
