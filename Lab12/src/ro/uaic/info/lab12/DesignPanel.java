package ro.uaic.info.lab12;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignPanel extends JPanel {
    private final MainFrame mainFrame;
    private final int W = 800, H = 600;

    public DesignPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
    }

    public void addAtRandomPosition(JComponent component) {
        var random = new Random();

        var componentWidth = component.getPreferredSize().width;
        var componentHeight = component.getPreferredSize().height;

        var posX = Math.max(random.nextInt(W) - componentWidth, 0);
        var posY = Math.max(random.nextInt(H) - componentHeight, 0);

        component.setBounds(posX, posY, componentWidth, componentHeight);

        add(component);
        repaint();
    }
}
