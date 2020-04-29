package ui.menubar;

import ui.MainFrame;

import javax.swing.*;

// Represents the menu bar
public class MenuBar extends JMenuBar {
    private JMenu menu;
    private CreateWorkoutItem createWorkoutItem;
    private ViewWorkoutItem viewWorkoutItem;
    private SaveWorkoutItem saveWorkoutItem;
    private LoadWorkoutItem loadWorkoutItem;
    private MainFrame mainFrame;

    // EFFECTS: makes a new menu bar
    public MenuBar(MainFrame mainFrame) {
        setBorder(BorderFactory.createEtchedBorder());
        this.mainFrame = mainFrame;
        this.menu = new JMenu("Menu");
        this.createWorkoutItem = new CreateWorkoutItem(mainFrame);
        this.viewWorkoutItem = new ViewWorkoutItem(mainFrame);
        this.saveWorkoutItem = new SaveWorkoutItem(mainFrame);
        this.loadWorkoutItem = new LoadWorkoutItem(mainFrame);

        menu.add(createWorkoutItem);
        menu.add(viewWorkoutItem);
        menu.add(saveWorkoutItem);
        menu.add(loadWorkoutItem);
        
        add(menu);
    }


}
