package avalanche.engine.maths;

import org.joml.Vector3f;


public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f position, float rx, float ry, float rz, float sx,float sy,float sz) {
        Matrix4f matrix = new Matrix4f();
        
        matrix.identity();
        
        matrix.scale(sx,sy,sz);
        
        Matrix4f.translate(position);
        
        Matrix4f.rotate((float)Math.toRadians(rx));
        Matrix4f.rotate((float)Math.toRadians(ry));
        Matrix4f.rotate((float)Math.toRadians(rz));
        
        Matrix4f.scale(sx, sy, sz);
        
        return matrix;
    }
}
