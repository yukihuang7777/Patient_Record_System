package persistence;

import model.Patient;
import model.PatientCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PatientCollection pc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPatientCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPatientCollection.json");
        try {
            PatientCollection pc = reader.read();
            assertEquals(0, pc.getPatientNumber());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPatientCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPatientCollection.json");
        try {
            PatientCollection pc = reader.read();
            List<String> patients = pc.getPatient();
            assertEquals(2, pc.getPatientNumber());
            assertEquals("a", patients.get(0));
            assertEquals("b", patients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
