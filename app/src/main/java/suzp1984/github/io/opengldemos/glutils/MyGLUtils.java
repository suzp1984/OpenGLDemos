package suzp1984.github.io.opengldemos.glutils;

import android.opengl.GLES20;

/**
 * Created by jacobsu on 6/13/16.
 */
public class MyGLUtils {
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
