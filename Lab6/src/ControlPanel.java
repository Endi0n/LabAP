import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        loadBtn.addActionListener(this::load);
        add(loadBtn);

        saveBtn.addActionListener(this::save);
        add(saveBtn);

        resetBtn.addActionListener(this::reset);
        add(resetBtn);

        exitBtn.addActionListener(this::exit);
        add(exitBtn);
    }

    private void load(ActionEvent e) {
        try {
            frame.drawingPanel.image = ImageIO.read(new File("./test.png"));
            frame.drawingPanel.graphics = frame.drawingPanel.image.createGraphics();
            frame.drawingPanel.repaint();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.drawingPanel.image, "PNG", new File("./test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent e) {
        frame.drawingPanel.graphics.setColor(Color.WHITE);
        frame.drawingPanel.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
        frame.drawingPanel.repaint();
    }

    private void exit(ActionEvent e) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}