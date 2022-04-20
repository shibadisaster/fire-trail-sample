import java.awt.*;

public interface DrawingObject {
    public void draw(Graphics2D g2d);
    public void moveTo(double x, double y);
    public double getX();
    public double getY();
}
