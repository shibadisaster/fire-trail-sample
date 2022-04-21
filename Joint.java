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

    private int temperature;
    private int adjustedTemperature;

    private Color color;

    public Joint(double x, double y) {
        xpos = x;
        ypos = y;

        xvel = Math.floor(Math.random() * randomness * 2) - randomness;
        yvel = Math.floor(Math.random() * randomness * 2) - randomness;
    }

    public void draw(Graphics2D g2d) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //alpha values based on inverse-square law

        Ellipse2D.Double joint4 = new Ellipse2D.Double(xpos - size * 2, ypos - size * 2, size * 4, size * 4);
        color = new Color(r, g, b, 16);
        g2d.setColor(color);
        g2d.fill(joint4);

        Ellipse2D.Double joint3 = new Ellipse2D.Double(xpos - size * 1.5, ypos - size * 1.5, size * 3, size * 3);
        color = new Color(r, g, b, 28);
        g2d.setColor(color);
        g2d.fill(joint3);

        Ellipse2D.Double joint2 = new Ellipse2D.Double(xpos - size, ypos - size, size * 2, size * 2);
        color = new Color(r, g, b, 64);
        g2d.setColor(color);
        g2d.fill(joint2);

        Ellipse2D.Double joint1 = new Ellipse2D.Double(xpos - size / 2, ypos - size / 2, size, size);
        color = new Color(r, g, b, 255);
        g2d.setColor(color);
        g2d.fill(joint1);

        // g2d.drawString( "" + temperature, (int) xpos,  (int) ypos - 10);
    }

    public void moveTo(double x, double y) {
        //xvel = (x - (size / 2)) - xpos + (Math.floor(Math.random() * randomness * 2) - randomness);
        //yvel = (y - (size / 2)) - ypos + (Math.floor(Math.random() * randomness * 2) - randomness);
        if (xpos > 1366 || ypos > 768 || xpos < 0 || ypos < 0) {
            xpos = 683 + Math.random() * 20 - 10;
            ypos = 384 + Math.random() * 20 - 10;
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


        //heat coloring algorithm from https://tannerhelland.com/2012/09/18/convert-temperature-rgb-algorithm-code.html
        temperature = (int) (200 * Math.sqrt(xvel * xvel + yvel * yvel) + Math.random() * 1000);
        adjustedTemperature = (int) temperature / 100;
        if (adjustedTemperature > 150) { adjustedTemperature = 150; }

        // r = (int) (255 + Math.floor(Math.random() * 0));
        // g = (int) (64 + Math.floor(Math.random() * 191));
        // b = (int) (0 + Math.floor(Math.random() * 64));

        // if (adjustedTemperature <= 66) {
        //     r = 255;
        // }
        // else {
        //     r = adjustedTemperature - 60;
        //     r = (int) (329.698727446 * Math.pow(r, -0.1332047592));
        //     if (r < 0) r = 0;
        //     if (r > 255) r = 255;
        // }

        // if (adjustedTemperature <= 66) {
        //     g = adjustedTemperature;
        //     g = (int) (99.4708025861 * Math.log(g) - 161.1195681661);
        //     if (g < 0) g = 0;
        //     if (g > 255) g = 255;
        // }
        // else {
        //     g = adjustedTemperature - 60;
	    //     g = (int) (288.1221695283 * Math.pow(g, -0.0755148492));
	    //     if (g < 0) g = 0;
        //     if (g > 255) g = 255;
        // }

        // if (adjustedTemperature >= 66) {
        //     b = 255;
        // }
        // else {
        //     if (adjustedTemperature <= 19) {
        //         b = 0;
        //     }
        //     else {
        //         b = adjustedTemperature - 10;
        //         b = (int) (138.5177312231 * Math.log(b) - 305.0447927307);
        //         if (b < 0) b = 0;
        //         if (b > 255) b = 255;
        //     }
        // }

        r = (int) (-0.000042697360013586407889198127 * Math.pow(adjustedTemperature, 3) + -0.004423346137566552642439443588 * Math.pow(adjustedTemperature, 2) + 2.648790949059564692191770518548 * adjustedTemperature + 22.312567277335521254144623526372);
        g = (int) (-0.000038180229016251326717382858 * Math.pow(adjustedTemperature, 3) + 0.019859522701747105849490182550 * Math.pow(adjustedTemperature, 2) + -1.254243382842633591778280788276 * adjustedTemperature + 14.198028172137270530583919025958);
        b = (int) (0.000126167682228283279397343386 * Math.pow(adjustedTemperature, 3) + -0.041747653068680251609023912351 * Math.pow(adjustedTemperature, 2) + 2.739377592537613370637927800999 * adjustedTemperature + 131.756384461319896672648610547185);

        if (r < 0) { r = 0; }
        if (r > 255) { r = 255; }
        if (g < 0) { g = 0; }
        if (g > 255) { g = 255; }
        if (b < 0) { b = 0; }
        if (b > 255) { b = 255; }

        //whats this mess at least it looks cool
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }
}