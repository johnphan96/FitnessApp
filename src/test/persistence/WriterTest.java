package persistence;

import exceptions.NameLengthException;
import model.Exercise;
import model.WorkoutMenu;
import model.WorkoutProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_FILE = "./data/testWorkoutPrograms.txt";
    private Writer testWriter;
    private WorkoutMenu testWorkoutMenu;
    private WorkoutProgram testWorkoutProgram1;
    private WorkoutProgram testWorkoutProgram2;
    private Exercise testExercise1a;
    private Exercise testExercise1b;
    private Exercise testExercise2;
    private Reader testReader;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testReader = new Reader(new File(TEST_FILE));
        testWorkoutMenu = new WorkoutMenu();
        try {
            testWorkoutProgram1 = new WorkoutProgram("testprogram1");
        } catch (NameLengthException e) {
            e.printStackTrace();
        }
        try {
            testWorkoutProgram2 = new WorkoutProgram("testprogram2");
        } catch (NameLengthException e) {
            e.printStackTrace();
        }
        testExercise1a = new Exercise("exercise1a", 5,5);
        testExercise1b = new Exercise("exercise1b", 5, 10);
        testExercise2 = new Exercise("exercise2", 8, 8);
        testWorkoutProgram1.addExercise(testExercise1a);
        testWorkoutProgram1.addExercise(testExercise1b);
        testWorkoutProgram2.addExercise(testExercise2);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram1);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram2);
    }

    @Test
    void testWriteWorkoutMenu() {
        // save workout programs currently in workout menu to file
        testWriter.write(testWorkoutMenu);
        testWriter.close();

        // read the file back and verify workout programs have expected attributes
        try {
            List<WorkoutProgram> workoutPrograms = testReader.readWorkoutPrograms();

            WorkoutProgram program1 = workoutPrograms.get(0);
            assertEquals("testprogram1", program1.getWorkoutName());
            assertEquals("exercise1a", program1.getExerciseNames().get(0));
            assertEquals(5, program1.getExerciseList().get(0).getSets());
            assertEquals(5, program1.getExerciseList().get(0).getReps());
            assertFalse(program1.getExerciseList().get(0).isDone());
            assertEquals("exercise1b", program1.getExerciseNames().get(1));
            assertEquals(5, program1.getExerciseList().get(1).getSets());
            assertEquals(10, program1.getExerciseList().get(1).getReps());
            assertFalse(program1.getExerciseList().get(1).isDone());

            WorkoutProgram program2 = workoutPrograms.get(1);
            assertEquals("testprogram2", program2.getWorkoutName());
            assertEquals("exercise2", program2.getExerciseNames().get(0));
            assertEquals(8, program2.getExerciseList().get(0).getSets());
            assertEquals(8, program2.getExerciseList().get(0).getReps());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NameLengthException e) {
            fail("NameLengthException should not have been thrown");
        }
    }
}
