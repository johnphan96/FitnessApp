package ui.windows.createworkoutbuttons;

import ui.SoundEffect;
import ui.events.CreateWorkoutEvent;
import ui.listeners.CreateWorkoutEventListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Add button in the CreateWorkoutWindow
public class AddExerciseButton extends JButton {
    private static final String SOUND_EFFECT_PATH = "./data/yeet.wav";
    private SoundEffect soundEffect = new SoundEffect();
    private JTextField exerciseNameField;
    private JTextField setsField;
    private JTextField repsField;
    private static CreateWorkoutEventListener addExerciseListener;

    // EFFECTS: constructs an Add button
    public AddExerciseButton(JTextField exerciseNameField, JTextField setsField, JTextField repsField) {
        super("Add");
        this.exerciseNameField = exerciseNameField;
        this.setsField = setsField;
        this.repsField = repsField;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: add action listener to Add button
    private void addListener() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exerciseName = exerciseNameField.getText();
                String sets = setsField.getText();
                String reps = repsField.getText();
                CreateWorkoutEvent addExerciseEvent = new CreateWorkoutEvent(this, exerciseName, sets, reps);
                if (addExerciseListener != null) {
                    addExerciseListener.createWorkoutEventOccured(addExerciseEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }

    public static void setAddExerciseListener(CreateWorkoutEventListener listener) {
        addExerciseListener = listener;
    }
}
