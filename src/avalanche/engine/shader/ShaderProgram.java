package avalanche.engine.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;
import static avalanche.engine.utils.Logger.*;

public abstract class ShaderProgram {
	protected int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
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
