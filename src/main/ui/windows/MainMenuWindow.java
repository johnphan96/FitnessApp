package ui.windows;

import javax.swing.*;
import java.awt.*;

// Represents the main menu frame of GUI
public class MainMenuWindow extends JPanel {
    private JTextArea textArea;

    // EFFECTS: makes a main menu window
    public MainMenuWindow() {
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.append("Welcome to Fitness Trainer!\n");

    }

    // EFFECTS: appends new text to textArea
    public void appendText(String text) {
        textArea.append(text);
    }
}
