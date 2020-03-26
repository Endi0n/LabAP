import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    ConfigPanel configPanel;
    DrawingPanel drawingPanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.NORTH);

        configPanel = new ConfigPanel(this);
        add(configPanel, BorderLayout.SOUTH);

        drawingPanel = new DrawingPanel(this);
        add(drawingPanel, BorderLayout.CENTER);

        pack();
    }
}