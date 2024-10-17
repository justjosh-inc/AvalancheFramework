package avalanche.engine.shader;

import static avalanche.engine.utils.Constants.*;

public class StaticShader extends ShaderProgram{
	
	public StaticShader() {
		super(VERTEX_FILE,FRAG_FILE);	
	}
	
	public StaticShader(String vertexFile, String fragmentFile) {
		super(vertexFile, fragmentFile);
	}
	
	public void addAttribute(int index,String variableName) {
		super.bindAttribute(index, variableName);
	}
	
}
