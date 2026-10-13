import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BallPanel extends Panel implements Runnable, ActionListener {

    private List<Ball> ballList;

    public BallPanel() {
        ballList = new ArrayList<>();
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        for (Ball ball : ballList) {
            ball.draw(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Ball ball = new Ball(getWidth() / 2, getHeight() / 2) {
        };

        ballList.add(ball);
    }
}