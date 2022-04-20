import java.awt.*;
import java.awt.geom.*;

public class Joint implements DrawingObject {
    private double xpos;
    private double ypos;
    private double xvel;
    private double yvel;
    private double size = 2 + Math.random() * 6;
    private double randomness = 10;
    private int r;
    private int g;
    private int b;

    private Color color;

    public Joint(double x, double y) {
        xpos = x;
        ypos = y;

        xvel = Math.floor(Math.random() * randomness * 2) - randomness;
        yvel = Math.floor(Math.random() * randomness * 2) - randomness;
        
        r = (int) (255 + Math.floor(Math.random() * 0));
        g = (int) (64 + Math.floor(Math.random() * 191));
        b = (int) (0 + Math.floor(Math.random() * 64));
    }

    public void draw(Graphics2D g2d) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Ellipse2D.Double joint1 = new Ellipse2D.Double(xpos - size * 1.5, ypos - size * 1.5, size * 3, size * 3);
        color = new Color(r, g, b, 30);
        g2d.setColor(color);
        g2d.fill(joint1);

        Ellipse2D.Double joint2 = new Ellipse2D.Double(xpos - size, ypos - size, size * 2, size * 2);
        color = new Color(r, g, b, 80);
        g2d.setColor(color);
        g2d.fill(joint2);

        Ellipse2D.Double joint3 = new Ellipse2D.Double(xpos - size / 2, ypos - size / 2, size, size);
        color = new Color(r, g, b, 255);
        g2d.setColor(color);
        g2d.fill(joint3);
    }

    public void moveTo(double x, double y) {
        //xvel = (x - (size / 2)) - xpos + (Math.floor(Math.random() * randomness * 2) - randomness);
        //yvel = (y - (size / 2)) - ypos + (Math.floor(Math.random() * randomness * 2) - randomness);
        if (xpos > 1366) {
            xpos = 0;

            xpos = 683;
            ypos = 384;
        }

        if (ypos > 768) {
            ypos = 0;

            xpos = 683;
            ypos = 384;
        }

        if (xpos < 0) {
            xpos = 1366;

            xpos = 683;
            ypos = 384;
        }

        if (ypos < 0) {
            ypos = 768;

            xpos = 683;
            ypos = 384;
        }

        xvel += Math.random() * 1 - 0.5;
        yvel += Math.random() * 1 - 0.5;

        xvel += (x - xpos) / (800 + size * 100);
        yvel += (y - ypos) / (800 + size * 100);

        xvel = xvel * 0.99;
        yvel = yvel * 0.99;
        // yvel += 0.01;

        xpos = xpos + xvel;
        ypos = ypos + yvel;
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }
}