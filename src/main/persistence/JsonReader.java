package persistence;

import model.Patient;
import model.PatientCollection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// REFERENCE: JsonSerializationDemo
// Represents a reader that reads PatientCollection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads PatientCollection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PatientCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePatientCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PatientCollection from JSON object and returns it
    private PatientCollection parsePatientCollection(JSONObject jsonObject) {
        PatientCollection pc = new PatientCollection();
        addPatients(pc, jsonObject);
        return pc;
    }

    // MODIFIES: pc
    // EFFECTS: parses thingies from JSON object and adds them to PatientCollection
    private void addPatients(PatientCollection pc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(pc, nextPatient);
        }
    }

    // MODIFIES: pc
    // EFFECTS: parses thingy from JSON object and adds it to PatientCollection
    private void addPatient(PatientCollection pc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String symptom = jsonObject.getString("symptom");
        String condition = jsonObject.getString("condition");
        Patient patient = new Patient(name,age,symptom,condition);
        pc.addPatient(patient);
    }
}
