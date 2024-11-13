package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Utils {

	public static FloatBuffer storedDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public static String getStringFromFile(String filePath) {
		StringBuffer content = new StringBuffer();
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content.toString();
	}
}
