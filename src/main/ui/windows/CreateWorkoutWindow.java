package ui.windows;

import ui.windows.createworkoutbuttons.AddExerciseButton;
import ui.windows.createworkoutbuttons.FinishButton;
import ui.windows.createworkoutbuttons.SetWorkoutNameButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Represents the create workout window
public class CreateWorkoutWindow extends JPanel {
    private JLabel workoutNameLabel;
    private JLabel exerciseNameLabel;
    private JLabel setsLabel;
    private JLabel repsLabel;
    private JTextField workoutNameField;
    private JTextField exerciseNameField;
    private JTextField setsField;
    private JTextField repsField;
    private SetWorkoutNameButton setWorkoutNameButton;
    private AddExerciseButton addExerciseButton;
    private FinishButton finishButton;

    // EFFECTS: makes a create workout window
    public CreateWorkoutWindow() {
        initializeDimension();
        initializeLabels();
        initializeTextFields();
        initializeButtons();
        initializeBorder();
        initializeGridBagLayout();
    }

    // MODIFIES: this
    // EFFECTS: set up GridBagLayout and constraints
    private void initializeGridBagLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        addWorkOutNameComponent(gridBagConstraints);
        addExerciseNameComponent(gridBagConstraints);
        addSetsComponent(gridBagConstraints);
        addRepsComponent(gridBagConstraints);
        addButtonsComponent(gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: add buttons
    private void addButtonsComponent(GridBagConstraints gridBagConstraints) {

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(setWorkoutNameButton, gridBagConstraints);
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(addExerciseButton, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        add(finishButton, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: add reps name label, field
    private void addRepsComponent(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(repsLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(repsField, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: add set name label, field
    private void addSetsComponent(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(setsLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(setsField, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: add exercise name label, field
    private void addExerciseNameComponent(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(exerciseNameLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(exerciseNameField, gridBagConstraints);

    }

    // MODIFIES: this
    // EFFECTS: add workout name label, field, and set button to window
    private void addWorkOutNameComponent(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(workoutNameLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(workoutNameField, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: set up border for window
    private void initializeBorder() {
        Border inner = BorderFactory.createTitledBorder("Create new workout");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    // MODIFIES: this
    // EFFECTS: set up buttons for window
    private void initializeButtons() {
        setWorkoutNameButton = new SetWorkoutNameButton(workoutNameField);
        addExerciseButton = new AddExerciseButton(exerciseNameField, setsField, repsField);
        finishButton = new FinishButton();

    }

    // MODIFIES: this
    // EFFECTS: set up text fields for window
    private void initializeTextFields() {
        workoutNameField = new JTextField(10);
        exerciseNameField = new JTextField(10);
        setsField = new JTextField(10);
        repsField = new JTextField(10);
    }

    // MODIFIES: this
    // EFFECTS: set up dimensions for window
    private void initializeDimension() {
        Dimension dimension = getPreferredSize();
        dimension.width = 350;
        setPreferredSize(dimension);
    }

    // MODIFIES: this
    // EFFECTS: set up labels for window
    private void initializeLabels() {
        workoutNameLabel = new JLabel("Workout Name: ");
        exerciseNameLabel = new JLabel("Exercise Name: ");
        setsLabel = new JLabel("Number of sets: ");
        repsLabel = new JLabel("Number of reps: ");
    }

}
