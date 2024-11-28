package avalanche.core;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.system.MemoryUtil.*;
import static avalanche.utils.Logger.*;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import avalanche.utils.Logger;
import avalanche.utils.Logger.logLevel;

public class Shader {

	private final int programID;
	private int vertexShaderID, fragmentShaderID, geometryShaderID;

	private final Map<String, Integer> uniforms;

	public Shader() {
		programID = glCreateProgram();
		if (programID == NULL) {
			toConsole(logLevel.ERROR, "Could not create Shader Program", true);
		}

		uniforms = new HashMap<String, Integer>();
	}

	private int locateUniform(String uniformName) {
		if (uniforms.containsKey(uniformName)) {
			return uniforms.get(uniformName);
		} else {
			int uniformLocation = glGetUniformLocation(programID, uniformName);
			if (uniformLocation < 0) {
				Logger.toConsole(Logger.logLevel.WARNING, "Could not find Uniform " + uniformName, true);
			}
			uniforms.put(uniformName, uniformLocation);
			return uniformLocation;
		}
	}

	public void setUniform(String uniformName, Matrix4f value) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			glUniformMatrix4fv(locateUniform(uniformName), false, value.get(stack.mallocFloat(16)));
		}
	}

	public void setUniform(String uniformName, int value) {
		glUniform1i(locateUniform(uniformName), value);
	}

	public void setUniform(String uniformName,float[] size) {
		glUniform4f(locateUniform(uniformName), size[0],size[1],size[2],size[3]);
	}
	
	public void setUniform(String uniformName,float x,float y,float z,float w) {
		glUniform4f(locateUniform(uniformName), x, y, z, w);
	}

	public void setUniform(String uniformName, boolean value) {
		glUniform1f(locateUniform(uniformName), value ? 1 : 0);
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
		fragmentShaderID = createShader(shaderCode, GL_FRAGMENT_SHADER);
	}

	public void createGeometryShader(String shaderCode) {
		geometryShaderID = createShader(shaderCode, GL_GEOMETRY_SHADER);
	}

	public int createShader(String shaderCode, int shaderType) {
		int shaderID = glCreateShader(shaderType);
		if (shaderID == NULL) {
			toConsole(logLevel.WARNING, "Could not create" + getShaderName(shaderType), true);
		}

		glShaderSource(shaderID, shaderCode);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) { // Fixing the comparison with GL_FALSE
			String errorLog = glGetShaderInfoLog(shaderID);
			toConsole(logLevel.WARNING, "Could not compile" + getShaderName(shaderType) + " INFO: " + errorLog, false);
		}

		glAttachShader(programID, shaderID);

		return shaderID;
	}

	public void link() {
		glLinkProgram(programID);
		if (glGetProgrami(programID, GL_LINK_STATUS) == NULL) {
			toConsole(logLevel.WARNING, "Could not link program  INFO: " + glGetProgramInfoLog(programID, 1024), false);
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
