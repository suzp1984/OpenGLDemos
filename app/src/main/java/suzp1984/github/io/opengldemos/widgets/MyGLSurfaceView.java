package suzp1984.github.io.opengldemos.widgets;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import suzp1984.github.io.opengldemos.globject.Square;
import suzp1984.github.io.opengldemos.globject.Triangle;

/**
 * Created by jacobsu on 6/12/16.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRender mRender;
    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        mRender = new MyGLRender();

        setRenderer(mRender);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    public static class MyGLRender implements GLSurfaceView.Renderer {

        private Triangle mTriangle;
        private Square mSquare;

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            mTriangle = new Triangle();
            mSquare = new Square();
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int w, int h) {
            GLES20.glViewport(0, 0, w, h);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            mTriangle.draw();
        }
    }
}
