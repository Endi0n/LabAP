import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    JFileChooser fileChooser = new JFileChooser();

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

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Chose file");

        fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
    }

    private void load(ActionEvent e) {
        try {
            var ret = fileChooser.showOpenDialog(new JPanel());
            if (ret != JFileChooser.APPROVE_OPTION)
                return;

            frame.drawingPanel.image = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
            frame.drawingPanel.graphics = frame.drawingPanel.image.createGraphics();
            frame.drawingPanel.repaint();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void save(ActionEvent e) {
        try {
            var ret = fileChooser.showSaveDialog(new JPanel());
            if (ret != JFileChooser.APPROVE_OPTION)
                return;

            var fileName = fileChooser.getSelectedFile().getAbsolutePath();
            var fileSplit = fileName.split(".");

            var fileExtension = (fileSplit.length > 0) ? fileSplit[fileSplit.length - 1] : "";

            if (!fileExtension.toLowerCase().equals("png"))
                fileName += ".png";

            ImageIO.write(frame.drawingPanel.image, "PNG", new File(fileName));
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