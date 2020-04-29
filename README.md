# Fitness Trainer

## Introduction

The Volleyball Fitness App is a personalized fitness trainer for volleyball players. The app provides pre-set workout
programs for players to develop their technical volleyball skills as well as strength and conditioning workouts. 
Furthermore, users can opt to **create their own workout**  or **customize provided workouts** to make the 
workout their own. The app is for volleyball players at the beginner to intermediate level that are seeking to improve
their game while they are not on the court. Players of all ages will find the app helpful in providing guidance and 
keeping them on track to improve their game as well as physical conditioning.

This project was of particular interest to me because volleyball is my favorite hobby. Although there are many fitness
apps out there, most of them are focused on dieting/calorie counting, lifestyle, and not many are focused on specific
sports. As such, I wanted to take this opportunity to incorporate something that I enjoy as a hobby and integrate it
into a personal project.

Volleyball Fitness features:
- Choose from pre-set technique or strength workouts
- Track your progress to help you stay consistent
- Log your weights and achievements to see your own improvements!


## User Stories

Phase 1: General workout app layout
- As a user, I want to be able to create a new workout program and add it to a menu of programs
- As a user, I want to be able to add new exercises to workout program with number of sets and reps
- As a user, I want to be able to remove a workout program
- As a user, I want to be able to select a workout and view a list of exercises


Phase 2:
- As a user, I want my workouts plans to file
- As a user, I want to be able to load my workout plan from file

Phase 3:
# Instructions for Grader
- You can generate the first required event by clicking on the "Menu" at the top of the app and select "Create Workout"
- Enter a workout name (a string) into the field and click "Set"
- Enter exercise name (string), sets (int), and reps (int) and then click "Add Exercise". Can add as many as you want.
- You can generate the second required event by clicking the "Finish" button, this will add the new workout program
you just made to the workout menu. To check the workout program was created, select "Menu" -> "View Workout". You
should see the name of the workout program just below "Select workout:". Click on the workout and select "View"
- You can trigger my audio component by clicking on any Menu item, or any button on the Create new workout window
- You can save the state of my application by selecting "Menu" -> "Save"
- You can reload the state of my application by selecting "Menu" -> "Load"
- You can view all workout programs by selecting "Menu" -> "View Workout". This is useful to check after you create a 
new workout program as well as checking that the workout programs were loaded correctly.
- This "View Workout" option also implements the rest of the user stories defined in phase 1 of the project above.

- I believe I should get full marks for this phase of the project 
- Rubric 1: 55/55 because I satisfied all the user stories from phase 1 and the user can generate 2 events related to 
the X's - create a new workout from a menu item, then click buttons to add to the workout. 
- Even though I did not get full marks for phase 1, I changed the 3rd user story that was deemed to be too similar to 
the 2nd user story to be a different user story altogether.
- Rubric 2: 30/30 because I incorporated an audio cue when a user clicks on the menu items or buttons
- Rubric 3: 15/15 because the Save/Load buttons work and the user can save/load the state of the app to/from file

Phase 4: Task 2
- I chose to test and design a robust class
- WorkoutMenu class in the model package was changed
- The method getWorkoutProgram() was changed to throw a MissingWorkoutException 
- The method is tested in the WorkoutMenuTest class by the 2 tests at the very bottom

Phase 4: Task 3
Problems identified:
 - Poor cohesion in the MenuBar class. Currently contains JMenuItems, which is fine. But also adding ActionListeners
 to these JMenuItems which is not responsibility of MenuBar which is just supposed to contain MenuItems
 
 - Poor cohesion in the CreateWorkoutWindow. Currently contains JButtons, but is also setting up ActionListeners for 
 these buttons which is not responsibility of CreateWorkoutWindow. Should create separate button classes with their
 own action listeners within the button class
 
 Changes made:
 - Refactored JMenuItems out of MenuBar class into their own classes. For example, the CreateWorkoutItem class now
 represents the createWorkoutItem that is in MenuBar with its own ActionListener set up within CreateWorkoutItem class
 
 - Refactored the JButtons out of CreateWorkoutWindow into their own classes. For example, AddExerciseButton is now
 its own class with its action listener defined and added within its own class


Citation of resources:
CPSC 210 examples
https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s

