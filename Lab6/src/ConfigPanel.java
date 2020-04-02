import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    Figures selectedFigure;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        selectedFigure = Figures.Polygon;

        sidesLabel = new JLabel("Number of sides:");
        add(sidesLabel);

        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);
        add(sidesField);
    }

    public void selectFigure(Figures figure) {
        selectedFigure = figure;
    }
}
