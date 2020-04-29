package model;

import exceptions.MissingExerciseException;
import exceptions.NameLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutProgramTest {
    private WorkoutProgram testWorkoutProgram;
    private Exercise testExercise;
    private Boolean testDone;

    @BeforeEach
    void runBefore() {
        try {
            testWorkoutProgram = new WorkoutProgram("plyometrics");
        } catch (NameLengthException e) {
            e.printStackTrace();
        }
        testExercise = new Exercise("deadlift", 5, 5);
        testDone = true;
    }

    @Test
    void testConstructor() {
        assertEquals("plyometrics", testWorkoutProgram.getWorkoutName());
        assertEquals(0, testWorkoutProgram.getTotalExercises());
        assertFalse(testWorkoutProgram.isDone());
        List<Exercise> expected;
        expected = new ArrayList<>();
        assertTrue(expected.equals(testWorkoutProgram.getExerciseList()));
    }

    @Test
    void testGetExerciseList() {
        List<Exercise> expected;
        expected = new ArrayList<>();
        assertTrue(expected.equals(testWorkoutProgram.getExerciseList()));

        testWorkoutProgram.addExercise(testExercise);
        assertFalse(expected.equals(testWorkoutProgram.getExerciseList()));
        expected.add(testExercise);
        assertTrue(expected.equals(testWorkoutProgram.getExerciseList()));

    }

    @Test
    void testAddOneExercise() {
        assertEquals(0, testWorkoutProgram.getTotalExercises());
        testWorkoutProgram.addExercise(testExercise);
        assertEquals(1, testWorkoutProgram.getTotalExercises());

    }

    @Test
    void testAddMultipleExercises() {
        assertEquals(0, testWorkoutProgram.getTotalExercises());
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        assertEquals(5, testWorkoutProgram.getTotalExercises());
    }


    @Test
    void setDone() {
        assertFalse(testWorkoutProgram.isDone());
        assertTrue(!testWorkoutProgram.isDone());
        testWorkoutProgram.setDone(testDone);
        assertTrue(testWorkoutProgram.isDone());
        assertFalse(!testWorkoutProgram.isDone());
        testWorkoutProgram.setDone(!testDone);
        assertFalse(testWorkoutProgram.isDone());
        assertTrue(!testWorkoutProgram.isDone());
    }

    @Test
    void setWorkoutName() {
        assertEquals("plyometrics", testWorkoutProgram.getWorkoutName());
        testWorkoutProgram.setWorkoutName("setter training");
        assertEquals("setter training", testWorkoutProgram.getWorkoutName());
    }

    @Test
    void testGetExerciseNames() {
        List<String> expected;
        expected = new ArrayList<>();
        assertTrue(expected.equals(testWorkoutProgram.getExerciseNames()));
        testWorkoutProgram.addExercise(testExercise);
        assertFalse(expected.equals(testWorkoutProgram.getExerciseNames()));
        expected.add("deadlift");
        assertTrue(expected.equals(testWorkoutProgram.getExerciseNames()));
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);
        testWorkoutProgram.addExercise(testExercise);



        expected.add("deadlift");
        expected.add("deadlift");
        expected.add("deadlift");
        expected.add("deadlift");

        assertTrue(expected.equals(testWorkoutProgram.getExerciseNames()));

        expected.add("bench");
        testWorkoutProgram.addExercise(testExercise);

        assertFalse(expected.equals(testWorkoutProgram.getExerciseNames()));
    }

    @Test
    void testWorkoutNameNoException() {
        try {
            WorkoutProgram testWorkoutName = new WorkoutProgram("strength");
        } catch (NameLengthException e) {
            fail("NameLengthException should not be thrown");
        }
    }

    @Test
    void testWorkoutNameExceptionExpected() {
        try {
            WorkoutProgram testWorkoutName = new WorkoutProgram("");
            fail("NameLengthException should have be thrown");
        } catch (NameLengthException e) {
            // expected
        }
    }

    @Test
    void testRemoveExerciseNoException() {
        testWorkoutProgram.addExercise(testExercise);
        assertEquals(1, testWorkoutProgram.getTotalExercises());
        try {
            testWorkoutProgram.removeExercise("deadlift");
            assertEquals(0, testWorkoutProgram.getTotalExercises());
        } catch (MissingExerciseException e) {
            fail("MissingExerciseException should not have been thrown.");
        }
    }

    @Test
    void testRemoveExerciseExceptionExpected() {
        try {
            testWorkoutProgram.removeExercise("deadlift");
            fail("MissingexerciseException should have been thrown.");
        } catch (MissingExerciseException e) {
            // expected
        }
    }
}