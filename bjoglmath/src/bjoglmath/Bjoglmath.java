package bjoglmath;

/**
 *
 * @author DesktopStian
 */
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

public class Bjoglmath implements GLEventListener {

    private double theta = 0;
    private double s = 0;
    private double c = 0;
    private float x = 0;
    private float y = 0;
    private boolean skiftEn;
    private boolean skiftTo;

    public static void main(String[] args) {

        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new Bjoglmath());

        Frame frame = new Frame("AWT Window Test");
        frame.setSize(300, 300);
        frame.add(canvas);
        frame.setVisible(true);

        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Animator animator;
        animator = new Animator(canvas);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        drawable.getGL().setSwapInterval(1);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        update();
        render(drawable);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {

    }

    private void update() {
        theta += 0.01;
        s = Math.sin(theta);
        c = Math.cos(theta);
        if (skiftEn) {
            x += 0.01;
            skiftEn = !skiftEn;
        } else {
            x += 0.01;
            skiftEn = !skiftEn;
        }
        if (x >= 1) {
            x = 0;
        }

    }

    private void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GL2 glb = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1, 0, y);
        gl.glVertex2d(-c, -c);
        gl.glColor3f(0, 1, 1);
        gl.glVertex2d(0, c);
        gl.glColor3f(y, 0, x);
        gl.glVertex2d(s, -s);
        gl.glEnd();
        glb.glBegin(GL.GL_POINTS);
        glb.glColor3f(1, 0, 0);
        glb.glVertex3d(-c + 1, -c + 1, -c + 1);
        glb.glVertex3d(0, 0, c + 1);
        glb.glVertex3d(0, c, c + 1);
        glb.glVertex3d(c + 1, 0, 0);
        glb.glEnd();

    }

}
