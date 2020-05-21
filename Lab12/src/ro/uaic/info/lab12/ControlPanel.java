package ro.uaic.info.lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class ControlPanel extends JPanel {
    private final MainFrame mainFrame;

    private final JLabel classNameLabel = new JLabel("Class name:");
    private final JTextField classNameTextField = new JTextField(30);

    private final JLabel textLabel = new JLabel("Text:");
    private final JTextField textField = new JTextField(30);

    private final JButton createComponentButton = new JButton("Create");

    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());

        add(classNameLabel);
        add(classNameTextField);

        add(textLabel);
        add(textField);

        createComponentButton.addActionListener(this::createComponent);
        add(createComponentButton);
    }

    private void createComponent(ActionEvent e) {
        try {
            var className = classNameTextField.getText();
            var clazz = Class.forName(className);
            var component = (JComponent) clazz.getConstructor().newInstance();

            try {
                var setText = clazz.getMethod("setText", String.class);
                setText.invoke(component, textField.getText());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
            }

            mainFrame.designPanel.addAtRandomPosition(component);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException instantiationException) {
            instantiationException.printStackTrace();
        }
    }
}
