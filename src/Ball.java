import java.awt.Graphics;

public abstract class Ball implements Runnable {

    protected int x;
    protected int y;

    protected int vx;
    protected int vy;

    protected int speed;

    protected Thread thread;

    public Ball(int x, int y) {

        // Save starting position
        this.x = x;
        this.y = y;

        // Random horizontal movement from -3 to 3
        vx = (int) (Math.random() * 7) - 3;

        // Random vertical movement from -3 to 3
        vy = (int) (Math.random() * 7) - 3;

        // Random delay between 20 and 30 milliseconds
        speed = 20 + (int) (Math.random() * 11);

        // Create a thread for this ball
        thread = new Thread(this);

        // Start moving the ball
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

    public void wakeUp() {

        synchronized (thread) {

            // Wake waiting threads
            thread.notifyAll();
        }
    }

    @Override
    public void run() {

        while (true) {

            // Move horizontally
            x = x + vx;

            // Move vertically
            y = y + vy;

            try {

                // Check left/right wall
                if (collidingX()) {

                    // Reverse horizontal direction
                    vx = vx * -1;

                    // Move one step in the new direction
                    x = x + vx;

                    synchronized (thread) {

                        // Stop this ball until it is awakened
                        thread.wait();
                    }
                }

                // Check top/bottom wall
                if (collidingY()) {

                    // Reverse vertical direction
                    vy = vy * -1;

                    // Move one step in the new direction
                    y = y + vy;

                    synchronized (thread) {

                        // Stop this ball until it is awakened
                        thread.wait();
                    }
                }

                // Wait before moving again
                Thread.sleep(speed);

            } catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }
    }
}