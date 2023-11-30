# Patient Record System

## Proposal

A patient record application for *healthcare professionals in hospitals*. 
It allows you to *create a personal information for all patients* who visit the hospital. 
Possible features include information about *patients who have already seen the hospital*; 
*recording patients who are visiting the hospital for the first time*; 
*showing a roster of patient's information*.
I am interested in this program because with this system, healthcare professionals can quickly understand
**the severity of each patient's situation** and develop **the most reasonable plan**.


## User Stories

- As a user, I want to be able to add a new patient to the hospital's patient record system 
and specify name, age and symptom
- As a user, I want to be able to view the list of patients in the hospital
- As a user, I want to be able to mark the severity of the patient's condition
- As a user, I want to be able to see the number of patients in the system
- As a user, I want to be able to view the specific information of a patient
- As a user, I want to be able to save my PatientCollection list to file if I so choose
- As a user, I want to be able to load my PatientCollection list from file if I so choose


# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple patients to patient
collection Y" by clicking "Add Patient" button, and enter the information of patient, then clicking "Save" button
to add the patient.
- You can generate the second required action related to the user story "view the list of patients" by clicking
"View Patient's List" button, so that you can see all patient's name that already added in the patient collection.
- You can locate my visual component by the background of mainPanel(the manu).
- You can save the state of my application by clicking the "Save" button.
- You can reload the state of my application by clicking the "Load" button.


# Phase 4: Task 2
Wed Nov 29 13:02:05 PST 2023
Patient hello is added.

Wed Nov 29 13:02:07 PST 2023
Show patient's list.

Wed Nov 29 13:02:12 PST 2023
Show patient's specific information.

Wed Nov 29 13:02:16 PST 2023
Show the number of patients.

Wed Nov 29 13:02:18 PST 2023
The File is saved.

Wed Nov 29 13:02:30 PST 2023
Patient world is added.

Wed Nov 29 13:02:31 PST 2023
Show patient's list.

Wed Nov 29 13:02:36 PST 2023
Show patient's specific information.

Wed Nov 29 13:02:39 PST 2023
Show patient's specific information.

Wed Nov 29 13:02:42 PST 2023
Show the number of patients.

Wed Nov 29 13:02:44 PST 2023
The File is saved.

Wed Nov 29 13:02:45 PST 2023
The File is loaded.

# Phase 4: Task 3
If I have more time, I plan to refactor the RecordWindow, split the contents into different classes, and write an 
interface for similar parts. This makes it easier to check for code problems and add new features when needed.