package ui.menubar;

import ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a create workout menu item
public class CreateWorkoutItem extends JMenuItem {

    public CreateWorkoutItem(MainFrame mainFrame) {
        super("Create Workout");
        addListener(mainFrame);
    }

    // MODIFIES: this
    // EFFECTS:  add action listener to create workout item
    private void addListener(MainFrame mainFrame) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.displayCreateWorkoutWindow();
                mainFrame.soundEffect.setFile(mainFrame.SOUND_EFFECT_PATH);
                mainFrame.soundEffect.playSound();
            }
        });
    }
}
