package ui.menubar;

import ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents a view workout menu item
public class ViewWorkoutItem extends JMenuItem {

    // EFFECTS: constructs a view workout menu item
    public ViewWorkoutItem(MainFrame mainFrame) {
        super("View Workouts");
        addListener(mainFrame);
    }

    // MODIFIES: this
    // EFFECTS: add action listener to view workout item
    private void addListener(MainFrame mainFrame) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.displayViewWorkoutWindow();
                mainFrame.soundEffect.setFile(mainFrame.SOUND_EFFECT_PATH);
                mainFrame.soundEffect.playSound();
            }
        });
    }
}
