package ui;

import exceptions.MissingWorkoutException;
import exceptions.NameLengthException;
import model.Exercise;
import model.WorkoutMenu;
import model.WorkoutProgram;
import persistence.Reader;
import persistence.Writer;
import ui.events.CreateWorkoutEvent;
import ui.events.ViewWorkoutEvent;
import ui.listeners.CreateWorkoutEventListener;
import ui.listeners.ViewWorkoutEventListener;
import ui.menubar.MenuBar;
import ui.windows.CreateWorkoutWindow;
import ui.windows.MainMenuWindow;
import ui.windows.ViewWorkoutWindow;
import ui.windows.createworkoutbuttons.AddExerciseButton;
import ui.windows.createworkoutbuttons.FinishButton;
import ui.windows.createworkoutbuttons.SetWorkoutNameButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// GUI mainframe for Fitness Trainer App
public class MainFrame extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private static final String WORKOUTPROGRAMS_TXT = "./data/workoutprograms.txt";
    private WorkoutMenu workoutMenu = new WorkoutMenu();
    private WorkoutProgram workoutProgram = null;
    private MainMenuWindow mainMenuWindow;
    private CreateWorkoutWindow createWorkoutWindow = null;
    private ViewWorkoutWindow viewWorkoutWindow = null;
    public static final String SOUND_EFFECT_PATH = "./data/yeet.wav";
    public SoundEffect soundEffect = new SoundEffect();



    // EFFECTS: runs the GUI for fitness trainer app
    public MainFrame() {
        super("Fitness Trainer App");
        setJMenuBar(initializeMenuBar());

        initializeGraphics();
    }

    ////////////// METHODS TO DISPLAY WINDOWS////////////////////////

    // EFFECTS: brings up the view workout window
    public void displayViewWorkoutWindow() {
        if (!(viewWorkoutWindow == null)) {
            remove(viewWorkoutWindow);
        }
        viewWorkoutWindow = new ViewWorkoutWindow(workoutMenu);
        initializeViewButtonListener();
        initializeDeleteButtonListener();
        initializeEditButtonListener();
        if (!(createWorkoutWindow == null)) {
            remove(createWorkoutWindow);
        }
        add(viewWorkoutWindow, BorderLayout.WEST);
        validate();
    }


    // EFFECTS: brings up the create workout window
    public void displayCreateWorkoutWindow() {
        if (!(createWorkoutWindow == null)) {
            remove(createWorkoutWindow);
        }
        createWorkoutWindow = new CreateWorkoutWindow();
        initializeSetButtonListener();
        initializeAddExerciseListener();
        initializeFinishListener();
        if (!(viewWorkoutWindow == null)) {
            remove(viewWorkoutWindow);
        }
        add(createWorkoutWindow, BorderLayout.WEST);
        validate();
    }

    // EFFECTS: displays main menu
    private void displayMainMenu() {
        add(mainMenuWindow, BorderLayout.CENTER);
        validate();
    }

    ////////////// METHODS TO DISPLAY WINDOWS////////////////////////


    //////////////////////////// BUTTON LISTENERS /////////////////////////////////////////

    // MODIFIES: createWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when finish button clicked from workout window
    private void initializeFinishListener() {
        FinishButton.setFinishListener(new CreateWorkoutEventListener() {
            public void createWorkoutEventOccured(CreateWorkoutEvent event) {
                if (workoutProgram == null) {
                    mainMenuWindow.appendText("No new workout created.\n");
                } else if (!(workoutMenu.listWorkoutNames().contains(workoutProgram.getWorkoutName()))) {
                    finishAddNewWorkout();
                } else {
                    finishEditWorkout();
                }
                workoutProgram = null;
                remove(createWorkoutWindow);
                displayMainMenu();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: finish editing workoutProgram and display workout in detail
    private void finishEditWorkout() {
        mainMenuWindow.appendText("Finished editing!\n");
        mainMenuWindow.appendText(workoutProgram.getWorkoutName() + "\n");
        for (Exercise exercise : workoutProgram.getExerciseList()) {
            String exerciseName = exercise.getExerciseName();
            int sets = exercise.getSets();
            int reps = exercise.getReps();
            mainMenuWindow.appendText(exerciseName + ": sets: " + sets + ": reps: " + reps + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds workoutProgram to workoutMenu and display workout in detail
    private void finishAddNewWorkout() {
        workoutMenu.addWorkoutProgram(workoutProgram);
        mainMenuWindow.appendText("New workout created!\n");
        mainMenuWindow.appendText(workoutProgram.getWorkoutName() + "\n");
        for (Exercise exercise : workoutProgram.getExerciseList()) {
            String exerciseName = exercise.getExerciseName();
            int sets = exercise.getSets();
            int reps = exercise.getReps();
            mainMenuWindow.appendText(exerciseName + ": sets: " + sets + ": reps: " + reps + "\n");
        }
    }

    // MODIFIES: createWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when add exercise button clicked from workout window
    private void initializeAddExerciseListener() {
        AddExerciseButton.setAddExerciseListener(new CreateWorkoutEventListener() {
            public void createWorkoutEventOccured(CreateWorkoutEvent event) {
                String exerciseName = event.getExerciseName();
                int sets = Integer.parseInt(event.getReps());
                int reps = Integer.parseInt(event.getReps());
                Exercise newExercise = new Exercise(exerciseName, sets, reps);
                if (!(workoutProgram == null)) {
                    workoutProgram.addExercise(newExercise);
                    mainMenuWindow.appendText("New exercise added to: " + workoutProgram.getWorkoutName() + "\n");
                    mainMenuWindow.appendText(newExercise.getExerciseName()
                            + ": sets: " + newExercise.getSets()
                            + ": reps: " + newExercise.getReps() + "\n");
                } else {
                    mainMenuWindow.appendText("Please set workout name before adding new exercises\n");
                }
            }
        });
    }

    // MODIFIES: createWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when set button clicked from workout window
    private void initializeSetButtonListener() {
        SetWorkoutNameButton.setWorkoutNameListener(new CreateWorkoutEventListener() {
            public void createWorkoutEventOccured(CreateWorkoutEvent event) {
                String workoutName = event.getWorkoutName();
                if (!workoutMenu.listWorkoutNames().contains(workoutName)) {
                    try {
                        workoutProgram = new WorkoutProgram(workoutName);
                        mainMenuWindow.appendText("Work out name set to: " + workoutProgram.getWorkoutName() + "\n");
                    } catch (NameLengthException e) {
                        mainMenuWindow.appendText("Name invalid. Please enter a valid name. \n");
                    }
                } else {
                    mainMenuWindow.appendText("A workout with that name already exists, please choose a new name.\n");
                }

            }
        });
    }

    // MODIFIES: viewWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when view button clicked from view workout window
    private void initializeViewButtonListener() {
        viewWorkoutWindow.setViewWorkoutEventListener(new ViewWorkoutEventListener() {
            public void viewWorkoutEventOccured(ViewWorkoutEvent event) {
                String viewWorkoutName = event.getViewWorkoutName();
                WorkoutProgram viewWorkoutProgram = null;
                try {
                    viewWorkoutProgram = workoutMenu.getWorkoutProgram(viewWorkoutName);
                } catch (MissingWorkoutException e) {
                    e.printStackTrace();
                }
                mainMenuWindow.appendText("Viewing workout: " + viewWorkoutProgram.getWorkoutName() + "\n");
                for (Exercise exercise : viewWorkoutProgram.getExerciseList()) {
                    String exerciseName = exercise.getExerciseName();
                    int sets = exercise.getSets();
                    int reps = exercise.getReps();
                    mainMenuWindow.appendText(exerciseName + ": sets: " + sets + ": reps: " + reps + "\n");
                }
            }
        });
    }

    // MODIFIES: viewWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when delete button clicked from view workout window
    private void initializeDeleteButtonListener() {
        viewWorkoutWindow.setDeleteWorkoutListener(new ViewWorkoutEventListener() {
            public void viewWorkoutEventOccured(ViewWorkoutEvent event) {
                String deleteWorkoutName = event.getDeleteWorkoutName();
                WorkoutProgram deleteWorkoutProgram = null;
                try {
                    deleteWorkoutProgram = workoutMenu.getWorkoutProgram(deleteWorkoutName);
                } catch (MissingWorkoutException e) {
                    e.printStackTrace();
                }
                mainMenuWindow.appendText("Removing workout: " + deleteWorkoutProgram.getWorkoutName() + "\n");
                workoutMenu.removeWorkoutProgram(deleteWorkoutProgram);
                mainMenuWindow.appendText(deleteWorkoutProgram.getWorkoutName() + " was successfully removed!" + "\n");
                remove(viewWorkoutWindow);
                displayViewWorkoutWindow();
            }
        });
    }

    // MODIFIES: viewWorkoutWindow
    // EFFECTS: adds listener and handles actions performed when delete button clicked from view workout window
    private void initializeEditButtonListener() {
        viewWorkoutWindow.setEditWorkoutListener(new ViewWorkoutEventListener() {
            public void viewWorkoutEventOccured(ViewWorkoutEvent event) {
                String editWorkoutName = event.getEditWorkoutName();
                try {
                    workoutProgram = workoutMenu.getWorkoutProgram(editWorkoutName);
                } catch (MissingWorkoutException e) {
                    e.printStackTrace();
                }
                mainMenuWindow.appendText("Editing " + workoutProgram.getWorkoutName() + "\n");
                displayCreateWorkoutWindow();
            }
        });
    }

    //////////////////////////// BUTTON LISTENERS /////////////////////////////////////////

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where the fitness trainer app will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeMainMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: draws the menu bar
    private MenuBar initializeMenuBar() {
        MenuBar menuBar = new MenuBar(this);
        return menuBar;
    }

    private void initializeMainMenu() {
        workoutMenu = new WorkoutMenu();
        mainMenuWindow = new MainMenuWindow();
        add(mainMenuWindow, BorderLayout.CENTER);
        validate();
    }

    // EFFECTS: saves state of workout programs to file
    public void saveWorkoutPrograms() {
        try {
            Writer writer = new Writer(new File(WORKOUTPROGRAMS_TXT));
            writer.write(workoutMenu);
            writer.close();
            mainMenuWindow.appendText("Workouts saved to file " + WORKOUTPROGRAMS_TXT + "\n");
        } catch (FileNotFoundException e) {
            mainMenuWindow.appendText("Unable to save workouts to " + WORKOUTPROGRAMS_TXT + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout programs from WORKOUTPROGRAMS_TXT, if that file exists;
    // otherwise initializes empty workout menu
    public void loadWorkoutPrograms() {
        try {
            Reader reader = new Reader(new File(WORKOUTPROGRAMS_TXT));
            List<WorkoutProgram> workoutPrograms = reader.readWorkoutPrograms();
            workoutMenu = new WorkoutMenu();
            workoutMenu.setWorkoutMenu(workoutPrograms);
            mainMenuWindow.appendText("Workouts loaded!\n");
        } catch (IOException e) {
            mainMenuWindow.appendText("Can't load file.\n");
        } catch (NameLengthException e) {
            mainMenuWindow.appendText("File contained a workout with empty name. Can't load file");
        }
    }



}
