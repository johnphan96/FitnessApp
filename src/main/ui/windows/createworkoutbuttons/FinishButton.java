package ui.windows.createworkoutbuttons;

import ui.SoundEffect;
import ui.events.CreateWorkoutEvent;
import ui.listeners.CreateWorkoutEventListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Finish button in CreateWorkoutWindow
public class FinishButton extends JButton {
    private static final String SOUND_EFFECT_PATH = "./data/yeet.wav";
    private SoundEffect soundEffect = new SoundEffect();
    private static CreateWorkoutEventListener finishListener;

    // EFFECTS: constructs a Finish button
    public FinishButton() {
        super("Finish");
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: adds action listener to the Finish button
    private void addListener() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWorkoutEvent finishEvent = new CreateWorkoutEvent(this);
                if (finishListener != null) {
                    finishListener.createWorkoutEventOccured(finishEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }

    public static void setFinishListener(CreateWorkoutEventListener listener) {
        finishListener = listener;
    }
}
