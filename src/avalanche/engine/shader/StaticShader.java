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
	
	private int getUniform(String name) {
		int result = glGetUniformLocation(programID, name);
		if (locationCache.containsKey(name)) {
			return locationCache.get(name);
		}
		if (result == -1) {
			Logger.toConsole(Logger.logLevel.ERROR, "Could not find variable " + name + "from the shader");
		}else {
			locationCache.put(name, result);
		}
		
		return result;
	}
	
	public void setUniform1i(String name,int value) {
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name,float value) {
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name,float x,float y) {
		glUniform2f(getUniform(name), x,y);
	}
	
	public void setUniform3f(String name,Vector3f vector) {
		glUniform3f(getUniform(name), vector.x,vector.y,vector.z);
	}
	
	public void setUniformMatrix4f(String name,Matrix4f matrix) {
		glUniformMatrix4fv(getUniform(name),false, Util.getFloatBufferFromMatrix4f(matrix));
	}
	
	public void addAttribute(int index,String variableName) {
		super.bindAttribute(index, variableName);
	}
	
}
