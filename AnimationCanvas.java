import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;

public class AnimationCanvas extends JComponent {
    private int width;
    private int height;

    private ArrayList<DrawingObject> trail;
    private ArrayList<Double> xPositions;
    private ArrayList<Double> yPositions;

    private Path2D.Double path;

    public AnimationCanvas(int w, int h) {
        width = w;
        height = h;

        trail = new ArrayList<DrawingObject>();
        setUpObjects();

        xPositions = new ArrayList<Double>();
        yPositions = new ArrayList<Double>();

        this.setPreferredSize(new Dimension(width, height));
    }

    public void setUpObjects() {
        for (int i = 0; i < 100; i++) {
            trail.add(new Joint(Math.floor(Math.random() * width), Math.floor(Math.random() * height)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.fill(background);

        g2d.setColor(new Color(255, 192, 32));
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //g2d.draw(path);

        for (int i = 0; i < trail.size(); i++) {
            trail.get(i).draw(g2d);
        }
        
    }

    public void animate(double x, double y) {

        // if (xPositions.size() >= 10 || yPositions.size() >= 10) {
        //     xPositions.remove(0);
        //     xPositions.add(x);
        //     yPositions.remove(0);
        //     yPositions.add(y);

            
        // }

        // else {
        //     xPositions.add(x);
        //     yPositions.add(y);
        // }

        path = new Path2D.Double();
        for (int i = 0; i < trail.size(); i++) {
            trail.get(i).moveTo(x, y);
            if (i == 0) {
                path.moveTo(trail.get(i).getX(), trail.get(i).getY());
            }
            else {
                path.lineTo(trail.get(i).getX(), trail.get(i).getY());
            }
        }

    }
}
