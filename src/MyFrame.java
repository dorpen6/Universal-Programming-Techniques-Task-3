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

        // Create panels
        ballPanel = new BallPanel();
        buttonPanel = new Panel();

        // Create buttons
        addBall = new Button("Add Ball");
        shakePanel = new Button("Shake Balls");

        // Add buttons to bottom panel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addBall);
        buttonPanel.add(shakePanel);

        // Main window layout
        setLayout(new BorderLayout());

        add(ballPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Colors
        ballPanel.setBackground(Color.YELLOW);
        buttonPanel.setBackground(Color.GREEN);

        // Add Ball button
        addBall.addActionListener(ballPanel);

        // Shake Balls button
        shakePanel.addActionListener(event -> ballPanel.shake());

        // Window settings
        setSize(500, 500);
        setVisible(true);

        // Close program when window is closed
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