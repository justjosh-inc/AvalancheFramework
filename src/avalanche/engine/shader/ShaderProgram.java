package avalanche.engine.shader;

import static avalanche.engine.utils.Logger.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import avalanche.engine.maths.Matrix4f;
import avalanche.engine.utils.Logger;
import avalanche.engine.utils.Logger.logLevel; 

public abstract class ShaderProgram { 

	protected int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private Map<String,Integer> locationCache = new HashMap<String,Integer>();
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile,String fragmentFile){
		vertexShaderID = loadShader(vertexFile,GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile,GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		
		validate(vertexShaderID, "VERTEX");
		validate(fragmentShaderID, "FRAGMENT");
		
		glLinkProgram(programID);
		glValidateProgram(programID); 
		if (programID == 0) {
		    toConsole(logLevel.WARNING, "Failed to compile shader program");
		}
	}
	
	public boolean validate(int shaderID,String type) {
		boolean result = true;
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			int len = glGetShaderi(shaderID,GL_INFO_LOG_LENGTH);
			toConsole(logLevel.WARNING, "Failed to compile " + type + " Shader");
			toConsole(logLevel.WARNING,glGetShaderInfoLog(shaderID,len));
			result = false;
		}
		return result;
	}
	
	public int getUniform(String name) {
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
	
	public void loadFloat(int location,float value) {
		glUniform1f(location,value);
	}
	
	public void loadVector(int location, Vector3f vector) {
		glUniform3f(location,vector.x,vector.y,vector.z);
	}
	
	public void loadBoolean(int location, boolean value) {
		float toLoad = 0;
		if (value) {
			toLoad = 1;
		}
		
		glUniform1f(location, toLoad);
	}
	
	public void loadMatrix(int location, Matrix4f matrix) {
		FloatBuffer matrixFloatBuffer = matrix.toFloatBuffer();
		glUniformMatrix4fv(location, false, matrixFloatBuffer);
		
	}
	
	public void start(){
		glUseProgram(programID);
	}
	
	public void stop(){
		glUseProgram(0);
	}
	
	public void cleanUp(){
		stop();
		
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		glDeleteProgram(programID);
	}

	
	protected void bindAttribute(int attribute, String variableName){
		glBindAttribLocation(programID, attribute, variableName);
	}
	
	private static int loadShader(String file, int type){
		StringBuilder shaderSource = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		return shaderID;
	}

}
