package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private Patient testPatient;

    @BeforeEach
    void runBefore() {
        testPatient = new Patient("Jenny", 20, "headache", "mild");
    }

    @Test
    void testConstructor() {
        assertEquals("Jenny", testPatient.getName());
        assertEquals(20, testPatient.getAge());
        assertEquals("headache", testPatient.getSymptom());
        assertEquals("mild", testPatient.getCondition());
        assertTrue(testPatient.getAge() >= 0);
    }

    @Test
    void testUpdateCondition() {
        testPatient.updateCondition("severity");
        assertEquals("severity", testPatient.getCondition());
    }

    @Test
    void testUpdateMultipleCondition() {
        testPatient.updateCondition("severity");
        testPatient.updateCondition("mild");
        assertEquals("mild", testPatient.getCondition());
    }

    @Test
    void testToString() {
        assertTrue( testPatient.toString().contains("name = Jenny, age = 20, symptom = headache, condition = mild"));
    }
}