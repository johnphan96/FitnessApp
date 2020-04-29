package ui.windows;

import model.WorkoutMenu;
import model.WorkoutProgram;
import ui.SoundEffect;
import ui.events.ViewWorkoutEvent;
import ui.listeners.ViewWorkoutEventListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents a view workout window
public class ViewWorkoutWindow extends JPanel {
    private JLabel selectWorkoutLabel;
    private JList workoutList;
    private JButton viewWorkoutButton;
    private JButton deleteWorkoutButton;
    private JButton editWorkoutButton;
    private ViewWorkoutEventListener viewWorkoutEventListener;
    private ViewWorkoutEventListener deleteWorkoutListener;
    private ViewWorkoutEventListener editWorkoutListener;
    private static final String SOUND_EFFECT_PATH = "./data/yeet.wav";
    private SoundEffect soundEffect = new SoundEffect();

    // EFFECTS: makes a view workout window
    // EFFECTS: makes a delete workout window
    public ViewWorkoutWindow(WorkoutMenu workoutMenu) {
        initializeDimension();
        initializeBorder();
        initializeComponents(workoutMenu);
        initializeGridBagLayout();

    }

    // EFFECTS: initialize the components of the window
    private void initializeComponents(WorkoutMenu workoutMenu) {
        // LABELS //
        selectWorkoutLabel = new JLabel("Select workout: ");

        initializeButtons();

        // LISTS //
        workoutList = new JList();
        workoutList.setPreferredSize(new Dimension(150, 600));
        workoutList.setBorder(BorderFactory.createEtchedBorder());
        makeWorkoutListModel(workoutMenu);

    }

    // EFFECTS: creates buttons and assign listeners to buttons
    private void initializeButtons() {
        viewWorkoutButton = new JButton("View");
        deleteWorkoutButton = new JButton("Delete");
        editWorkoutButton = new JButton("Edit");
        addViewWorkoutListener();
        addDeleteWorkoutListener();
        addEditWorkoutListener();
    }



    /////////////////////////////// ADDING LISTENERS //////////////////////////////////////////

    // EFFECTS: add listener and action performer to editWorkoutButton
    private void addEditWorkoutListener() {
        editWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String workoutName = (String)workoutList.getSelectedValue();
                ViewWorkoutEvent viewWorkoutEvent = new ViewWorkoutEvent(this, workoutName, 3);
                if (editWorkoutListener != null) {
                    editWorkoutListener.viewWorkoutEventOccured(viewWorkoutEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }

    // EFFECTS: add listener and action performer to viewWorkoutButton
    private void addViewWorkoutListener() {
        viewWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String workoutName = (String)workoutList.getSelectedValue();
                ViewWorkoutEvent viewWorkoutEvent = new ViewWorkoutEvent(this, workoutName, 1);
                if (viewWorkoutEventListener != null) {
                    viewWorkoutEventListener.viewWorkoutEventOccured(viewWorkoutEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }

    // EFFECTS: add listener and action performer to viewWorkoutButton
    private void addDeleteWorkoutListener() {
        deleteWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String workoutName = (String)workoutList.getSelectedValue();
                ViewWorkoutEvent viewWorkoutEvent = new ViewWorkoutEvent(this, workoutName, 2);
                if (deleteWorkoutListener != null) {
                    deleteWorkoutListener.viewWorkoutEventOccured(viewWorkoutEvent);
                }
                soundEffect.setFile(SOUND_EFFECT_PATH);
                soundEffect.playSound();
            }
        });
    }
    /////////////////////////////// ADDING LISTENERS //////////////////////////////////////////

    private void makeWorkoutListModel(WorkoutMenu workoutMenu) {
        DefaultListModel workoutListModel = new DefaultListModel();
        List<WorkoutProgram> workoutPrograms = workoutMenu.getWorkoutMenu();
        for (WorkoutProgram workoutProgram: workoutPrograms) {
            String workoutName = workoutProgram.getWorkoutName();
            workoutListModel.addElement(workoutName);
        }
        workoutList.setModel(workoutListModel);
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
        addComponentsToGrid(gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: add components to gridBayLayout
    private void addComponentsToGrid(GridBagConstraints gridBagConstraints) {
        add(selectWorkoutLabel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        add(workoutList, gridBagConstraints);
        addButtonsToGrid(gridBagConstraints);
    }

    private void addButtonsToGrid(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(viewWorkoutButton, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;

        add(deleteWorkoutButton, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;

        add(editWorkoutButton, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: set up border for window
    private void initializeBorder() {
        Border inner = BorderFactory.createTitledBorder("View workout");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    // MODIFIES: this
    // EFFECTS: set up dimensions for window
    private void initializeDimension() {
        Dimension dimension = getPreferredSize();
        dimension.width = 350;
        setPreferredSize(dimension);
    }

    public void setViewWorkoutEventListener(ViewWorkoutEventListener listener) {
        this.viewWorkoutEventListener = listener;
    }

    public ViewWorkoutEventListener getDeleteWorkoutListener() {
        return deleteWorkoutListener;
    }

    public void setDeleteWorkoutListener(ViewWorkoutEventListener deleteWorkoutListener) {
        this.deleteWorkoutListener = deleteWorkoutListener;
    }

    public ViewWorkoutEventListener getEditWorkoutListener() {
        return editWorkoutListener;
    }

    public void setEditWorkoutListener(ViewWorkoutEventListener editWorkoutListener) {
        this.editWorkoutListener = editWorkoutListener;
    }
}
