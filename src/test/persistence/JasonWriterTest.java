package persistence;

import model.Patient;
import model.PatientCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JasonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PatientCollection pc = new PatientCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPatientCollection() {
        try {
            PatientCollection pc = new PatientCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientCollection.json");
            writer.open();
            writer.write(pc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatientCollection.json");
            pc = reader.read();

            assertEquals(0, pc.getPatientNumber());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPatientCollection() {
        try {
            PatientCollection pc = new PatientCollection();
            pc.addPatient(new Patient("a", 20,"headache", "mild"));
            pc.addPatient(new Patient("b", 25, "stomachache", "severity"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPatientCollection.json");
            writer.open();
            writer.write(pc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPatientCollection.json");
            pc = reader.read();
            List<String> patients = pc.getPatient();
            assertEquals(2, patients.size());
            assertEquals("a", patients.get(0));
            assertEquals("b", patients.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
