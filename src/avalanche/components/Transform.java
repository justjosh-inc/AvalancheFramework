package avalanche.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import avalanche.engine.maths.Maths;

public class Transform extends Component{

	public Vector3f position;
	public float rotX,rotY,rotZ;
	public float scale;
	
	public Matrix4f matrix;
	
	public Transform(Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(Component.TYPE_TRANSFORM);
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.matrix = new Matrix4f();
	}
	
	public void increasePosition(float dx,float dy,float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
		this.matrix.transform(dx, dy, dz, 0, null);
	}
	
	public void increaseRotation(float dx,float dy,float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	

	
}
