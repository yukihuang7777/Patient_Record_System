package ui;

import model.Patient;
import model.PatientCollection;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Patient record application
public class RecordApp {
    private static final String JSON_STORE = "./data/PatientCollection.json";
    private PatientCollection collection;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS : construct collection runs the record application
    public RecordApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        collection = new PatientCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRecord();
    }

    // MODIFIES : this
    // EFFECTS : process user input
    // REFERENCE : TellerApp
    private void runRecord() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES : this
    // EFFECTS : processes user command
    // REFERENCE : TellerApp
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddPatient();
        } else if (command.equals("u")) {
            doUpdatePatientCondition();
        } else if (command.equals("n")) {
            doGetPatientNumber();
        } else if (command.equals("p")) {
            doGetPatient();
        } else if (command.equals("v")) {
            doViewSpecificInfo();
        } else if (command.equals("s")) {
            savePatientCollection();
        } else if (command.equals("l")) {
            loadPatientCollection();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES : this
    // EFFECTS : initializes accounts
    // REFERENCE : TellerApp
    private void init() {
        collection = new PatientCollection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS : displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add patient");
        System.out.println("\tu -> update patient condition");
        System.out.println("\tn -> patient number");
        System.out.println("\tp -> patient's name list");
        System.out.println("\tv -> view patient's specific information");
        System.out.println("\ts -> save patient collection to file");
        System.out.println("\tl -> load patient collection from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES : this
    // EFFECTS : add a new patient
    private void doAddPatient() {
        System.out.println("Enter the Patient's name:");
        String name = input.next();
        System.out.println("Enter the Patient's age:");
        int age = input.nextInt();
        System.out.println("Enter the Patient's symptom:");
        String symptom = input.next();
        System.out.println("Enter the Patient's condition:");
        String condition = input.next();

        collection.addPatient(new Patient(name, age, symptom, condition));
        System.out.println("success!");
        EventLog.getInstance().logEvent(new Event("Patient " + name + " is added."));

    }

    // REQUIRES : the patient's name is already in the collection
    // MODIFIES : this
    // EFFECTS : if condition is in correct type, update the patient condition;
    //           else update again.
    private void doUpdatePatientCondition() {
        System.out.println("Enter the patient's name:");
        String name = input.next();
        System.out.println("\nEnter the new condition from:");
        System.out.println("\tmild");
        System.out.println("\tseverity");
        String condition = input.next();

        if (condition.equals("mild") || condition.equals("severity")) {
            collection.updatePatientCondition(name,condition);
            System.out.println("updated success");
        } else {
            System.out.println("updated false: wrong condition - please update again");
            doUpdatePatientCondition();
        }
        EventLog.getInstance().logEvent(new Event("Patient's information updated."));

    }

    // EFFECTS : print the number of patient
    private void doGetPatientNumber() {
        System.out.println("The number of patient in collection is " + collection.getPatientNumber() + ".");
        EventLog.getInstance().logEvent(new Event("Show the number of patients."));
    }

    // EFFECTS : print the list of patient's name
    private void doGetPatient() {
        System.out.println("The list of patients: " + collection.getPatient());
        EventLog.getInstance().logEvent(new Event("Show patient's list."));

    }

    // EFFECTS : print the specific information of the patient
    private void doViewSpecificInfo() {
        System.out.println("Enter the patient's name: ");
        String name = input.next();

        System.out.println(collection.viewSpecificInfo(name));
        EventLog.getInstance().logEvent(new Event("Show patient's specific information."));
    }

    // EFFECTS: saves the PatientCollection to file
    private void savePatientCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            System.out.println("Saved collection to " + JSON_STORE);
            EventLog.getInstance().logEvent(new Event("The file is saved."));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads PatientCollection from file
    private void loadPatientCollection() {
        try {
            collection = jsonReader.read();
            System.out.println("Loaded collection from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        EventLog.getInstance().logEvent(new Event("The file is loaded."));
    }

}


















