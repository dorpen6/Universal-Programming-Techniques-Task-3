import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BallPanel extends Panel implements Runnable, ActionListener {

    private List<Ball> ballList;

    public BallPanel() {

        // Create an empty list for balls
        ballList = new ArrayList<>();

        // Thread used to repaint the panel
        Thread panelThread = new Thread(this);
        panelThread.start();
    }

    @Override
    public void paint(Graphics graphics) {

        // Draw every ball
        for (Ball ball : ballList) {
            ball.draw(graphics);
        }
    }

    @Override
    public void run() {

        while (true) {

            repaint();

            try {

                // About 30 FPS
                Thread.sleep(33);

            } catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // Center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Create a new ball
        Ball newBall = new Ball(centerX, centerY) {

            @Override
            public boolean collidingX() {

                boolean touchesLeftSide =
                        x + vx - 5 < 0;

                boolean touchesRightSide =
                        x + vx + 5 > getWidth();

                return touchesLeftSide || touchesRightSide;
            }

            @Override
            public boolean collidingY() {

                boolean touchesTop =
                        y + vy - 5 < 0;

                boolean touchesBottom =
                        y + vy + 5 > getHeight();

                return touchesTop || touchesBottom;
            }
        };

        // Add the new ball to the list
        ballList.add(newBall);
    }

    public void shake() {

        // Use the same shared monitor as every Ball
        synchronized (Ball.MONITOR) {

            // Wake all waiting balls
            Ball.MONITOR.notifyAll();
        }
    }
}