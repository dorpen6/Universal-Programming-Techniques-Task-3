import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame {

    private BallPanel ballPanel;
    private Panel buttonPanel;
    private Button addBall;
    private Button shakePanel;

    public MyFrame() {
        ballPanel = new BallPanel();
        addBall = new Button("Add Ball");
        shakePanel = new Button("Shake Balls");
        buttonPanel = new Panel();

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addBall);
        buttonPanel.add(shakePanel);

        setLayout(new BorderLayout());
        add(ballPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        ballPanel.setBackground(Color.YELLOW);
        buttonPanel.setBackground(Color.GREEN);

        addBall.addActionListener(ballPanel);

        setSize(500, 500);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}