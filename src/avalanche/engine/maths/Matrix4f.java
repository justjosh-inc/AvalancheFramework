package avalanche.engine.maths;

import java.nio.FloatBuffer;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import avalanche.engine.utils.Util;

public class Matrix4f extends org.joml.Matrix4f{
	
	public FloatBuffer toFloatBuffer() {
		return Util.storeDataInFloatBuffer(null);
	} // WORK ON
}