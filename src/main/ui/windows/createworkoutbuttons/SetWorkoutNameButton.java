package ui.windows.createworkoutbuttons;

import ui.SoundEffect;
import ui.events.CreateWorkoutEvent;
import ui.listeners.CreateWorkoutEventListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Set button in CreateWorkoutWindow
public class SetWorkoutNameButton extends JButton {
    private static final String SOUND_EFFECT_PATH = "./data/yeet.wav";
    private SoundEffect soundEffect = new SoundEffect();
    private static CreateWorkoutEventListener workoutNameListener;
    private JTextField workoutNameField;

    // EFFECTS: constructs a Set button
    public SetWorkoutNameButton(JTextField workoutNameField) {
        super("Set");
        this.workoutNameField = workoutNameField;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: adds action listener to Set button
    private void addListener() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String workoutName = workoutNameField.getText();
                CreateWorkoutEvent setWorkoutNameEvent = new CreateWorkoutEvent(this, workoutName);
                if (workoutNameListener != null) {
                    workoutNameListener.createWorkoutEventOccured(setWorkoutNameEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }

    public static void setWorkoutNameListener(CreateWorkoutEventListener listener) {
        workoutNameListener = listener;
    }
}
