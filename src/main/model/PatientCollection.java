package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of patients
public class PatientCollection implements Writable {
    private List<Patient> patients;

    // EFFECTS : constructs a patient collection without any patient present
    public PatientCollection() {
        patients = new ArrayList<>();
    }

    /*
     * REQUIRES : there are no patients have same name
     * MODIFIES : this
     * EFFECTS : adds the given patient to the collection.
     */
    public void addPatient(Patient patient) {
        boolean result = false;
        for (Patient p : patients) {
            if (p.getName().equals(patient.getName())) {
                result = true;
            }
        }
        if (!result) {
            patients.add(patient);
        }
    }

    /*
     * REQUIRES : name has non-zero length
     *            age >= 0
     *            newCondition can only be mild or severity
     *            there are no patients have same name
     *            the patient's name is already in the collection
     * MODIFIES : this
     * EFFECTS : update patient condition to newCondition
     */
    public void updatePatientCondition(String name, String newCondition) {
        for (Patient p : patients) {
            if (p.getName().equals(name)) {
                p.updateCondition(newCondition);
            }
        }
    }

    /*
     * EFFECTS : return the number of patient in the collection
     */
    public int getPatientNumber() {
        return patients.size();
    }

    /*
     * REQUIRES : patients.size() > 0
     * EFFECTS : return the list of patients
     */
    public List<String> getPatient() {
        List<String> listOfPatients = new ArrayList<>();
        for (Patient p : patients) {
            listOfPatients.add(p.getName());
        }
        return listOfPatients;
    }

    /*
     * REQUIRES : there are no patients have same name
     * EFFECTS : return the specific information of a patient
     */
    public String viewSpecificInfo(String name) {
        for (Patient p : patients) {
            if (p.getName().equals(name)) {
                return p.toString();
            }
        }
        return "Do not find the patient.";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("patients", patientsToJson());
        return json;
    }

    // EFFECTS: returns things in this PatientCollection as a JSON array
    private JSONArray patientsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Patient t : patients) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
