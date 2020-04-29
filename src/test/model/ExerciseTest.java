package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ExerciseTest {
    private Exercise testExercise;
    private Boolean testDone;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("deadlift", 5, 5);
        testDone = true;
    }

    @Test
    void testConstructor() {
        assertEquals("deadlift", testExercise.getExerciseName());
        assertEquals(5, testExercise.getSets());
        assertEquals(5, testExercise.getReps());
        assertFalse(testExercise.isDone());
    }

    @Test
    void testGetExerciseName() {
        assertEquals("deadlift", testExercise.getExerciseName());
    }

    @Test
    void testSetExerciseName() {
        assertEquals("deadlift", testExercise.getExerciseName());
        testExercise.setExerciseName("squat");
        assertEquals("squat", testExercise.getExerciseName());
    }

    @Test
    void setReps() {
        assertEquals(5, testExercise.getReps());
        testExercise.setReps(8);
        assertEquals(8, testExercise.getReps());
    }

    @Test
    void setSets() {
        assertEquals(5, testExercise.getSets());
        testExercise.setSets(10);
        assertEquals(10, testExercise.getSets());
    }

    @Test
    void testIsDone() {
        assertFalse(testExercise.isDone());
    }
    @Test
    void setDone() {
        assertFalse(testExercise.isDone());
        assertTrue(!testExercise.isDone());
        testExercise.setDone(testDone);
        assertTrue(testExercise.isDone());
        assertFalse(!testExercise.isDone());
        testExercise.setDone(!testDone);
        assertFalse(testExercise.isDone());
        assertTrue(!testExercise.isDone());

    }
}
