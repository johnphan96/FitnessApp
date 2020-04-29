package model;
import exceptions.MissingWorkoutException;
import exceptions.NameLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class WorkoutMenuTest {
    private WorkoutMenu testWorkoutMenu;
    private WorkoutProgram testWorkoutProgram;
    private WorkoutProgram testWorkoutProgram2;
    private List<WorkoutProgram> testWorkoutProgramList;

    @BeforeEach
    void runBefore() throws NameLengthException {
        testWorkoutMenu = new WorkoutMenu();
        testWorkoutProgram = new WorkoutProgram("plyometrics");
        testWorkoutProgram2 = new WorkoutProgram("strength");
        testWorkoutProgramList = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWorkoutMenu.getTotalWorkoutPrograms());
        List<WorkoutProgram> expected;
        expected = new ArrayList<>();
        assertTrue(testWorkoutMenu.getWorkoutMenu().equals(expected));

    }

    @Test
    void testGetTotalWorkoutPrograms() {
        assertEquals(0, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(1, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(2, testWorkoutMenu.getTotalWorkoutPrograms());
    }

    @Test
    void testGetWorkoutMenu() {
        List<WorkoutProgram> expected;
        expected = new ArrayList<>();
        assertTrue(testWorkoutMenu.getWorkoutMenu().equals(expected));
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        expected.add(testWorkoutProgram);
        expected.add(testWorkoutProgram);
        assertTrue(testWorkoutMenu.getWorkoutMenu().equals(expected));
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertFalse(testWorkoutMenu.getWorkoutMenu().equals(expected));
    }

    @Test
    void testAddOneWorkoutProgram() {
        assertEquals(0, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(1, testWorkoutMenu.getTotalWorkoutPrograms());
    }

    @Test
    void testAddMultipleWorkoutPrograms() {
        assertEquals(0, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(2, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(5, testWorkoutMenu.getTotalWorkoutPrograms());
    }

    @Test
    void testListWorkoutNames() {
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        List<String> expected;
        expected = new ArrayList<>();
        expected.add("plyometrics");
        expected.add("plyometrics");
        expected.add("plyometrics");
        expected.add("plyometrics");
        expected.add("plyometrics");
        assertTrue(expected.equals(testWorkoutMenu.listWorkoutNames()));
        expected.add("chest");
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertFalse(expected.equals(testWorkoutMenu.listWorkoutNames()));
    }

    @Test
    void testSetWorkoutMenu() {
        testWorkoutProgramList.add(testWorkoutProgram);
        testWorkoutProgramList.add(testWorkoutProgram);
        testWorkoutMenu.setWorkoutMenu(testWorkoutProgramList);
        assertEquals(testWorkoutProgramList, testWorkoutMenu.getWorkoutMenu());
    }

    @Test
    void testRemoveWorkoutProgram() {
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        assertEquals(1, testWorkoutMenu.getTotalWorkoutPrograms());
        testWorkoutMenu.removeWorkoutProgram(testWorkoutProgram);
    }

    @Test
    void testGetWorkoutProgramNoException() {
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram2);
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        WorkoutProgram getWorkout = null;
        try {
            getWorkout = testWorkoutMenu.getWorkoutProgram("plyometrics");
            assertEquals(testWorkoutProgram, getWorkout);
        } catch (MissingWorkoutException e) {
            fail("MissingWorkoutException not expected");
        }
    }

    @Test
    void testGetWorkoutProgramExceptionExpected() {
        testWorkoutMenu.addWorkoutProgram(testWorkoutProgram);
        WorkoutProgram getWorkout = null;
        try {
            getWorkout = testWorkoutMenu.getWorkoutProgram("strength");
            fail("MissingWorkoutException should be thrown");
        } catch (MissingWorkoutException e) {
            // expected
        }
    }
}
