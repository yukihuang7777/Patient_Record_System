package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientCollectionTest {
    private PatientCollection testPatientCollection;
    private Patient p1;
    private Patient p2;
    private Patient p3;

    @BeforeEach
    void runBefore() {
        testPatientCollection = new PatientCollection();
        p1 = new Patient("Jenny", 20, "headache", "mild");
        p2 = new Patient("Sherry", 19, "stomachache", "mild");
        p3 = new Patient("Bob", 50, "backache", "severity");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPatientCollection.getPatientNumber());
    }

    @Test
    void testOnePatient() {
        testPatientCollection.addPatient(p1);
        assertEquals(1, testPatientCollection.getPatientNumber());
        assertEquals("Jenny", testPatientCollection.getPatient().get(0));
    }

    @Test
    void testMultiplePatient() {
        testPatientCollection.addPatient(p1);
        testPatientCollection.addPatient(p2);
        testPatientCollection.addPatient(p3);
        assertEquals(3,testPatientCollection.getPatientNumber());
        assertEquals("Jenny", testPatientCollection.getPatient().get(0));
        assertEquals("Sherry", testPatientCollection.getPatient().get(1));
        assertEquals("Bob", testPatientCollection.getPatient().get(2));

        testPatientCollection.addPatient(p3);
        assertEquals(3,testPatientCollection.getPatientNumber());
        assertEquals("Jenny", testPatientCollection.getPatient().get(0));
        assertEquals("Sherry", testPatientCollection.getPatient().get(1));
        assertEquals("Bob", testPatientCollection.getPatient().get(2));
    }

    @Test
    void testUpdatePatientCondition() {
        testPatientCollection.addPatient(p1);
        testPatientCollection.updatePatientCondition("Jenny", "severity");
        assertEquals("severity",p1.getCondition());
    }

    @Test
    void testUpdateMultiplePatientCondition() {
        testPatientCollection.addPatient(p1);
        testPatientCollection.addPatient(p2);
        testPatientCollection.addPatient(p3);

        testPatientCollection.updatePatientCondition("Jenny", "severity");
        assertEquals("severity",p1.getCondition());

        testPatientCollection.updatePatientCondition("Sherry", "severity");
        assertEquals("severity",p2.getCondition());
        testPatientCollection.updatePatientCondition("Sherry", "mild");
        assertEquals("mild",p2.getCondition());

        assertEquals("severity", p3.getCondition());

    }

    @Test
    void testViewSpecificInfo() {
        testPatientCollection.addPatient(p1);
        testPatientCollection.addPatient(p2);
        assertEquals("[ name = Jenny, age = 20, symptom = headache, condition = mild ]",
                testPatientCollection.viewSpecificInfo("Jenny"));
        assertEquals("[ name = Sherry, age = 19, symptom = stomachache, condition = mild ]",
                testPatientCollection.viewSpecificInfo("Sherry"));
        assertEquals("Do not find the patient.", testPatientCollection.viewSpecificInfo("Bob"));

    }
}
