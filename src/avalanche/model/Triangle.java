package avalanche.model;

public class Triangle extends BaseModel{

	private static float[] vertices = {
			0.0f,  0.5f, 0.0f,
		   -0.5f, -0.5f, 0.0f,
			0.5f, -0.5f, 0.0f 
	};
	private static int[] indicies = {
			0,1,2
	};
	
	public Triangle() {
		this.setVerticies(vertices);
		this.setTextCoords(vertices);
		this.setIndicies(indicies);
		this.mesh = loader.loadToVAO(this.getVerticies(),
				this.getTextCoords(),
				this.getIndicies());
	}
}
