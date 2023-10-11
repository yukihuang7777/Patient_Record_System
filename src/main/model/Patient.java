package model;

// Represents a patient having name, age, symptom and severity
public class Patient {
    private String name;        // patient name
    private int age;            // patient age
    private String symptom;     // the symptom of the patient
    private String condition;   // the severity of patient's condition

    /*
     * REQUIRES : name and symptom have a non-zero length
     *            age >= 0
     *            condition can only be mild or severity
     * EFFECTS : set the patient's name, age, symptom and condition
     */
    public Patient(String name, int age, String symptom, String condition) {
        this.name = name;
        this.age = age;
        this.symptom = symptom;
        this.condition = condition;
    }

    /*
     * REQUIRES : newCondition can only be mild or severity
     * MODIFIES : this
     * EFFECTS :  update condition with newCondition
     */
    public void updateCondition(String newCondition) {
        condition = newCondition;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getCondition() {
        return condition;
    }


    public String toString() {
        return "[ name = " + name + ", age = " + age + ", symptom = "
                + symptom + ", condition = " + condition + " ]";
    }
}
