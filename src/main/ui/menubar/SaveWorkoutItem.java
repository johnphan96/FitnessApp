package ui.menubar;

import ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a save workout item
public class SaveWorkoutItem extends JMenuItem {

    // EFFECTS: constructs a save workout item
    public SaveWorkoutItem(MainFrame mainFrame) {
        super("Save");
        addListener(mainFrame);
    }

    // MODIFIES: this
    // EFFECTS: add action listener to save workout item
    private void addListener(MainFrame mainFrame) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.saveWorkoutPrograms();
                mainFrame.soundEffect.setFile(mainFrame.SOUND_EFFECT_PATH);
                mainFrame.soundEffect.playSound();
            }
        });
    }
}
