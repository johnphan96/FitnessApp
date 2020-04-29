package persistence;

import exceptions.NameLengthException;
import model.WorkoutProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    private static final String TEST_FILE1 = "./data/testWorkoutPrograms1.txt";
    private static final String TEST_FILE2 = "./data/testWorkoutPrograms2.txt";
    private Reader testReader;

    @BeforeEach
    void runBefore() {
        testReader = new Reader(new File(TEST_FILE1));
    }

    @Test
    void testParseWorkoutPrograms() {
        try {
            List<WorkoutProgram> workoutPrograms = testReader.readWorkoutPrograms();
            WorkoutProgram program1 = workoutPrograms.get(0);
            assertEquals("strength", program1.getWorkoutName());
            assertEquals("bench", program1.getExerciseNames().get(0));
            assertEquals(5, program1.getExerciseList().get(0).getSets());
            assertEquals(5, program1.getExerciseList().get(0).getReps());
            assertFalse(program1.getExerciseList().get(0).isDone());
            assertEquals("squat", program1.getExerciseNames().get(1));
            assertEquals(5, program1.getExerciseList().get(1).getSets());
            assertEquals(10, program1.getExerciseList().get(1).getReps());
            assertFalse(program1.getExerciseList().get(1).isDone());
            WorkoutProgram program2 = workoutPrograms.get(1);
            assertEquals("plyo", program2.getWorkoutName());
            assertEquals("squat", program2.getExerciseNames().get(0));
            assertEquals(4, program2.getExerciseList().get(0).getSets());
            assertEquals(8, program2.getExerciseList().get(0).getReps());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NameLengthException e) {
            fail("NameLengthException not expected");
        }
    }

    @Test
    void testParseWorkoutProgramsExceptionExpected() {
        testReader = new Reader(new File(TEST_FILE2));
        try {
            List<WorkoutProgram> workoutPrograms = testReader.readWorkoutPrograms();
            fail("NameLengthException expected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NameLengthException e) {
            // expected
        }
    }


    @Test
    void testIOException() {
        try {
            Reader.readWorkoutPrograms();
        } catch (IOException e) {
            // expected
        } catch (NameLengthException e) {
            fail("Unexpected");
        }
    }
}
