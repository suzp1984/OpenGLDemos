package suzp1984.github.io.opengldemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import suzp1984.github.io.opengldemos.widgets.MyGLSurfaceView;

public class GLES20Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyGLSurfaceView glView = new MyGLSurfaceView(this);
        setContentView(glView);
    }
}
