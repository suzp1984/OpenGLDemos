package suzp1984.github.io.opengldemos.widgets;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import suzp1984.github.io.opengldemos.globject.Square;
import suzp1984.github.io.opengldemos.globject.Triangle;

/**
 * Created by jacobsu on 6/12/16.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRender mRender;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 3200;
    private float mPreviousX;
    private float mPreviousY;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        mRender = new MyGLRender();

        setRenderer(mRender);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y = mPreviousY;

                if (y > getHeight() / 2) {
                    dx = dx * -1;
                }

                if (x < getWidth() / 2) {
                    dy = dy * -1;
                }
                mRender.setAngle(mRender.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public static class MyGLRender implements GLSurfaceView.Renderer {

        private Triangle mTriangle;
        private Square mSquare;

        private final float[] mMVPMatrix = new float[16];
        private final float[] mProjectionMatrix = new float[16];
        private final float[] mViewMatrix = new float[16];

        private float[] mRotationMatrix = new float[16];

        public volatile float mAngle;

        public float getAngle() {
            return mAngle;
        }

        public void setAngle(float angle) {
            mAngle = angle;
        }

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            mTriangle = new Triangle();
            mSquare = new Square();
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int w, int h) {
            GLES20.glViewport(0, 0, w, h);

            float ratio = (float) w / h;

            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            float[] scratch = new float[16];

            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            // Set the camera position (View Matrix)
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

            // Calculate the projection and view transformation
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

            // create a rotation transformation for the triangle
//            long time = SystemClock.uptimeMillis() % 4000L;
//            float angle = 0.090f * ((int) time);

            Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

            mTriangle.draw(scratch);
        }
    }
}
