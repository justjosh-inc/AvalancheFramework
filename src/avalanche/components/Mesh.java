package avalanche.components;

public class Mesh extends Component{

	private int vaoID;
	private int vertexCount;
	
	public Mesh(int vaoID,int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
