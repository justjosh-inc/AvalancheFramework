package core;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static utils.Logger.*;

public class Shader {

	private final int programID;
	private int vertexShaderID, fragmentShaderID, geometryShaderID;

	public Shader() {
		programID = glCreateProgram();
		if (programID == NULL) {
			toConsole(logLevel.ERROR, "Could not create Shader Program", true);
		}
	}

	private static String getShaderName(int shaderType) {
		switch (shaderType) {
		case GL_VERTEX_SHADER:
			return " VERTEX SHADER ";
		case GL_FRAGMENT_SHADER:
			return " FRAGMENT SHADER ";
		case GL_GEOMETRY_SHADER:
			return " GEOMETRY SHADER ";
		default:
			return "UNKNOWN SHADER TYPE: " + shaderType;
		}
	}

	public void createVertexShader(String shaderCode) {
		vertexShaderID = createShader(shaderCode, GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) {
		vertexShaderID = createShader(shaderCode, GL_FRAGMENT_SHADER);
	}
	
	public void createGeometryShader(String shaderCode) {
		vertexShaderID = createShader(shaderCode, GL_GEOMETRY_SHADER);
	}

	public int createShader(String shaderCode, int shaderType) {
		int shaderID = glCreateShader(shaderType);
		if (shaderID == NULL) {
			toConsole(logLevel.WARNING, "Could not create" + getShaderName(shaderType), true);
		}

		glShaderSource(shaderID, shaderCode);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderType, GL_COMPILE_STATUS) == NULL) {
			toConsole(logLevel.WARNING,
					"Could not compile" + getShaderName(shaderType) + " INFO: " + glGetShaderInfoLog(shaderID, 1024),
					false);
		}

		glAttachShader(programID, shaderID);

		return shaderID;
	}

	public void link() {
		glLinkProgram(programID);
		if (glGetProgrami(programID, GL_LINK_STATUS) == NULL) {
			toConsole(logLevel.WARNING, "Could not link program + INFO: " + glGetProgramInfoLog(programID, 1024),
					false);
		}
		
		if (vertexShaderID != NULL) {
			glDetachShader(programID, vertexShaderID);
		}
		
		if (fragmentShaderID != NULL) {
			glDetachShader(programID, fragmentShaderID);
		}
		
		if (geometryShaderID != NULL) {
			glDetachShader(programID, geometryShaderID);
		}
		
		glValidateProgram(programID);
		if (glGetProgrami(programID, GL_VALIDATE_STATUS) == NULL) {
			toConsole(logLevel.WARNING, "Could not Validate program + INFO: " + glGetProgramInfoLog(programID, 1024),
					false);
		}
	}
	
	public void bind() {
		glUseProgram(programID);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public void cleanUP() {
		unbind();
		if (programID != NULL) {
			glDeleteProgram(programID);
		}
	}
}
