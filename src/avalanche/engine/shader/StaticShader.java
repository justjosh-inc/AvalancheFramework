package avalanche.engine.shader;

import static avalanche.engine.utils.Constants.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import avalanche.engine.utils.Logger;
import avalanche.engine.utils.Util;


public class StaticShader extends ShaderProgram{
	private Map<String,Integer> locationCache = new HashMap<String,Integer>();
	
	public StaticShader() {
		super(VERTEX_FILE,FRAG_FILE);	
	}
	
	public StaticShader(String vertexFile, String fragmentFile) {
		super(vertexFile, fragmentFile);
	}
	
	public void addAttribute(int location,String name) {
		this.bindAttribute(location, name);
	}

	
}
