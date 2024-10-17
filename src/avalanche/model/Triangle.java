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
	
	private static float[] textureCoords = {
		    0.5f, 1f,
		    0f, 0f,  
		    1f, 0f
	};
	
	public Triangle() {
		this.setVerticies(vertices);
		this.setTextCoords(textureCoords);
		this.setIndicies(indicies);
		this.mesh = loader.loadToVAO(this.getVerticies(),
				this.getTextCoords(),
				this.getIndicies());
	}
}
