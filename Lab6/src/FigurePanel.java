import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

public class FigurePanel extends JPanel {
    final MainFrame frame;
    JList<String> figureList;

    public FigurePanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        figureList = new JList<>(new String[]{"Polygon", "Circle"});
        figureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        figureList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    JList source = (JList)event.getSource();
                    String selected = source.getSelectedValue().toString();

                    switch (selected) {
                        case "Polygon":
                            frame.configPanel.selectFigure(Figures.Polygon);
                        case "Circle":
                            frame.configPanel.selectFigure(Figures.Circle);
                    }
                }
            }
        });
        add(figureList);
    }

    private void changeFigure(ActionEvent e) {

    }
}
