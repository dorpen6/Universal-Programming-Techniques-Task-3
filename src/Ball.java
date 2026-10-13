import java.awt.Graphics;

public abstract class Ball implements Runnable {

    // One shared object for ALL balls
    public static final Object MONITOR = new Object();

    protected int x;
    protected int y;

    protected int vx;
    protected int vy;

    protected int speed;

    protected Thread thread;

    public Ball(int x, int y) {

        // Starting position
        this.x = x;
        this.y = y;

        // Random movement direction
        vx = (int) (Math.random() * 7) - 3;
        vy = (int) (Math.random() * 7) - 3;

        // Random speed
        speed = 20 + (int) (Math.random() * 11);

        // Create and start the thread
        thread = new Thread(this);
        thread.start();
    }

    public void draw(Graphics graphics) {

        graphics.fillOval(
                x - 5,
                y - 5,
                10,
                10
        );
    }

    public abstract boolean collidingX();

    public abstract boolean collidingY();

    @Override
    public void run() {

        while (true) {

            // Move the ball
            x = x + vx;
            y = y + vy;

            try {

                // Collision with left or right wall
                if (collidingX()) {

                    // Reverse horizontal direction
                    vx = vx * -1;

                    // Move slightly back inside the panel
                    x = x + vx;

                    // Wait using the shared MONITOR
                    synchronized (MONITOR) {
                        MONITOR.wait();
                    }
                }

                // Collision with top or bottom wall
                if (collidingY()) {

                    // Reverse vertical direction
                    vy = vy * -1;

                    // Move slightly back inside the panel
                    y = y + vy;

                    // Wait using the shared MONITOR
                    synchronized (MONITOR) {
                        MONITOR.wait();
                    }
                }

                // Small pause before next movement
                Thread.sleep(speed);

            } catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }
    }
}