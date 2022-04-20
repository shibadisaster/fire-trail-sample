import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimationFrame {
    JFrame frame;
    AnimationCanvas canvas;

    private Point cursor;

    public void setUpGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Fiwe Twail :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new AnimationCanvas(1366, 768);
        frame.add(canvas, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();

        javax.swing.Timer timer = new javax.swing.Timer(10, new ActionListener() { //from http://www.fredosaurus.com/notes-java/other/10time/20timer.html
            public void actionPerformed(ActionEvent e) {
                cursor = MouseInfo.getPointerInfo().getLocation(); //from https://stackoverflow.com/questions/43063768/get-mouse-position-constantly-in-java-relative-to-jframe

                canvas.animate(cursor.getX() - frame.getLocation().getX(), cursor.getY() - frame.getLocation().getY());
                canvas.repaint();
            }
        });

        timer.start();
    }
}
