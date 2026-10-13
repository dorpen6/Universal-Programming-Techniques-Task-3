import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

    private BallPanel ballPanel;
    private Panel buttonPanel;

    private Button addBall;
    private Button shakePanel;

    public MyFrame() {

        // Create the panels
        ballPanel = new BallPanel();
        buttonPanel = new Panel();

        // Create the buttons
        addBall = new Button("Add Ball");
        shakePanel = new Button("Shake Balls");

        // Set layout for the button panel
        buttonPanel.setLayout(new FlowLayout());

        // Add buttons to the button panel
        buttonPanel.add(addBall);
        buttonPanel.add(shakePanel);

        // Set layout for the main window
        setLayout(new BorderLayout());

        // Add panels to the window
        add(ballPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set colors
        ballPanel.setBackground(Color.YELLOW);
        buttonPanel.setBackground(Color.GREEN);

        // Add Ball button
        addBall.addActionListener(ballPanel);

        // Shake Balls button
        shakePanel.addActionListener(event -> ballPanel.shake());

        // Window settings
        setSize(500, 500);
        setVisible(true);

        // Close the program when the window is closed
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}