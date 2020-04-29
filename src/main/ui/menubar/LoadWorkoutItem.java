package ui.menubar;

import ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a load workout item
public class LoadWorkoutItem extends JMenuItem {

    // EFFECTS: constructs a load workout item
    public LoadWorkoutItem(MainFrame mainFrame) {
        super("Load");
        addListener(mainFrame);
    }

    // MODIFIES: this
    // EFFECTS:  add action listener to load workout item
    private void addListener(MainFrame mainFrame) {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.loadWorkoutPrograms();
                mainFrame.soundEffect.setFile(mainFrame.SOUND_EFFECT_PATH);
                mainFrame.soundEffect.playSound();
            }
        });
    }
}
