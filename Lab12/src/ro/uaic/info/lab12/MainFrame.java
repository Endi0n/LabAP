package ro.uaic.info.lab12;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    final ControlPanel controlPanel;
    final DesignPanel designPanel;

    public MainFrame() {
        super("Dynamic Swing Designer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);

        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);

        pack();
    }
}
