package ui;

import model.Patient;
import model.PatientCollection;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// REFERENCE: LabelChanger, SimpleDrawingPlayer
public class RecordWindow extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel addPatientPanel;
    private JPanel listPanel;
    private JPanel searchInformationPanel;
    private JPanel informationPanel;
    private JPanel patientNumberPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JsonWriter writer;
    private JsonReader reader;
    private JLabel image;
    private PatientCollection pc;
    private Patient patient;


    public static void main(String[] args) {
        new RecordWindow();
    }

    // EFFECTS: Create a new JFrame
    public RecordWindow() {
        super("Patient Record");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500,600));

        pc = new PatientCollection();
        initializeButton();

        initializeMainPanel();

        clickButton();
        backButton();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    // MODIFIES: this
    // EFFECTS: create a new panel for main menu
    public void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setSize(new Dimension(0, 0));
        addButtonToPanel(button1,mainPanel);
        addButtonToPanel(button2,mainPanel);
        addButtonToPanel(button3,mainPanel);
        addButtonToPanel(button13,mainPanel);
        addButtonToPanel(button4,mainPanel);
        addButtonToPanel(button5,mainPanel);
        addButtonToPanel(button6,mainPanel);
        add(mainPanel, BorderLayout.SOUTH);
        initializeLabel();
    }

    // MODIFIES: this
    // EFFECTS: create a new panel for add patient
    public void initializeAddPatientPanel() {
        addPatientPanel = new JPanel();
        remove(mainPanel);
        remove(image);
        repaint();
        add(addPatientPanel);
        initializeTextToAddPatientPanel();
        addButtonToPanel(button8,addPatientPanel);
        addButtonToPanel(button7,addPatientPanel);
        addPatientPanel.setLayout(new GridLayout(8,0));
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a main panel and show the panel
    public void backToMainPanelFromAddPatientPanel() {
        mainPanel = new JPanel();
        remove(addPatientPanel);
        repaint();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setSize(new Dimension(0, 0));
        addButtonToPanel(button1,mainPanel);
        addButtonToPanel(button2,mainPanel);
        addButtonToPanel(button3,mainPanel);
        addButtonToPanel(button13,mainPanel);
        addButtonToPanel(button4,mainPanel);
        addButtonToPanel(button5,mainPanel);
        addButtonToPanel(button6,mainPanel);
        add(mainPanel, BorderLayout.SOUTH);
        initializeLabel();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a text field and label to write patient's information on addPatient panel
    public void initializeTextToAddPatientPanel() {
        text1 = new JTextField(8);
        text2 = new JTextField(8);
        text3 = new JTextField(8);
        text4 = new JTextField(8);
        JLabel label1 = new JLabel("name");
        JLabel label2 = new JLabel("age");
        JLabel label3 = new JLabel("symptom");
        JLabel label4 = new JLabel("condition");
        addPatientPanel.add(label1);
        addPatientPanel.add(text1);
        addPatientPanel.add(label2);
        addPatientPanel.add(text2);
        addPatientPanel.add(label3);
        addPatientPanel.add(text3);
        addPatientPanel.add(label4);
        addPatientPanel.add(text4);
    }

    // MODIFIES: this
    // EFFECTS: create a new panel for patient's list
    public void initializeListPanel() {
        listPanel = new JPanel();
        remove(mainPanel);
        remove(image);
        repaint();
        add(listPanel);
        initializeLabelToListPanel();
        addButtonToPanel(button9,listPanel);
        listPanel.setLayout(new GridLayout(6,0));
        revalidate();
    }

    // EFFECTS: create a new label to show the patient in the list on the panel
    public void initializeLabelToListPanel() {
        String patient = String.join(";", pc.getPatient());
        JLabel patientList = new JLabel(patient);
        listPanel.add(patientList);
    }

    // MODIFIES: this
    // EFFECTS: create a main panel and show the panel
    public void backToMainPanelFromListPanel() {
        mainPanel = new JPanel();
        remove(listPanel);
        repaint();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setSize(new Dimension(0, 0));
        addButtonToPanel(button1,mainPanel);
        addButtonToPanel(button2,mainPanel);
        addButtonToPanel(button3,mainPanel);
        addButtonToPanel(button13,mainPanel);
        addButtonToPanel(button4,mainPanel);
        addButtonToPanel(button5,mainPanel);
        addButtonToPanel(button6,mainPanel);
        add(mainPanel, BorderLayout.SOUTH);
        initializeLabel();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a new panel for search information panel
    public void initializeSearchInformationPanel() {
        searchInformationPanel = new JPanel();
        remove(mainPanel);
        remove(image);
        repaint();
        initializeTextToInformationPanel();
        addButtonToPanel(button11, searchInformationPanel);
        addButtonToPanel(button10, searchInformationPanel);
        searchInformationPanel.setLayout(new GridLayout(8,0));
        add(searchInformationPanel);
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a search box to search patient information on SearchInformationPanel
    public void initializeTextToInformationPanel() {
        text5 = new JTextField(8);
        JLabel label = new JLabel("name");
        searchInformationPanel.add(label);
        searchInformationPanel.add(text5);
    }

    // MODIFIES: this
    // EFFECTS: create a main panel and show the panel
    public void backToMainPanelFromInformationPanel() {
        mainPanel = new JPanel();
        remove(searchInformationPanel);
        repaint();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setSize(new Dimension(0, 0));
        addButtonToPanel(button1,mainPanel);
        addButtonToPanel(button2,mainPanel);
        addButtonToPanel(button3,mainPanel);
        addButtonToPanel(button13,mainPanel);
        addButtonToPanel(button4,mainPanel);
        addButtonToPanel(button5,mainPanel);
        addButtonToPanel(button6,mainPanel);
        add(mainPanel, BorderLayout.SOUTH);
        initializeLabel();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a new panel for patient's information
    public void initializeInformationPanel() {
        informationPanel = new JPanel();
        remove(searchInformationPanel);
        repaint();
        initializePatientInformation();
        addButtonToPanel(button12, informationPanel);
        add(informationPanel);
        revalidate();
    }

    // EFFECTS: add patient's specific information on the information panel
    public void initializePatientInformation() {
        JLabel patientInfo = new JLabel(pc.viewSpecificInfo(text5.getText()));
        informationPanel.add(patientInfo);
    }

    // MODIFIES: this
    // EFFECTS: create a information panel and show the panel
    public void backToInformationPanelFromSearchInformationPanel() {
        searchInformationPanel = new JPanel();
        remove(informationPanel);
        repaint();
        initializeTextToInformationPanel();
        addButtonToPanel(button11, searchInformationPanel);
        addButtonToPanel(button10, searchInformationPanel);
        searchInformationPanel.setLayout(new GridLayout(8,0));
        add(searchInformationPanel);
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a new panel for Patient Number
    public void initializePatientNumberPanel() {
        patientNumberPanel = new JPanel();
        remove(mainPanel);
        remove(image);
        repaint();
        add(patientNumberPanel);
        initializeLabelToPatientNumberPanel();
        addButtonToPanel(button14,patientNumberPanel);
        patientNumberPanel.setLayout(new GridLayout(6,0));
        revalidate();
    }

    // EFFECTS: create a label to show the number of patients on PatientNumberPanel
    public void initializeLabelToPatientNumberPanel() {
        JLabel label = new JLabel(String.valueOf(pc.getPatientNumber()));
        patientNumberPanel.add(label);
    }

    // MODIFIES: this
    // EFFECTS: create a main panel and show the panel
    public void backToMainPanelFromPatientNumberPanel() {
        mainPanel = new JPanel();
        remove(patientNumberPanel);
        repaint();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setSize(new Dimension(0, 0));
        addButtonToPanel(button1,mainPanel);
        addButtonToPanel(button2,mainPanel);
        addButtonToPanel(button3,mainPanel);
        addButtonToPanel(button13,mainPanel);
        addButtonToPanel(button4,mainPanel);
        addButtonToPanel(button5,mainPanel);
        addButtonToPanel(button6,mainPanel);
        add(mainPanel, BorderLayout.SOUTH);
        initializeLabel();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: create a new image label
    public void initializeLabel() {
        image = new JLabel();
        image.setIcon(new ImageIcon("./data/picture.png"));
        add(image);
    }

    // MODIFIES: this
    // EFFECTS: create new buttons
    public void initializeButton() {
        button1 = new JButton("Add Patient");
        button2 = new JButton("View Patient's List");
        button3 = new JButton("View Patient's Information");
        button4 = new JButton("Save");
        button5 = new JButton("Load");
        button6 = new JButton("Quit");
        button7 = new JButton("Add");
        button8 = new JButton("Back");
        button9 = new JButton("Back");
        button10 = new JButton("Back");
        button11 = new JButton("Search");
        button12 = new JButton("Back");
        button13 = new JButton("Patient's Number");
        button14 = new JButton("Back");

    }

    // EFFECTS: add Buttons to given Panel
    public void addButtonToPanel(JButton button, JPanel panel) {
        button.setPreferredSize(new Dimension(60,50));
        button.setFont(new Font("Arial",Font.PLAIN,18));
        panel.add(button);
    }

    // EFFECTS: set every button command and add action listener
    public void clickButton() {
        button1.setActionCommand("add");
        button1.addActionListener(this);
        button2.setActionCommand("list");
        button2.addActionListener(this);
        button3.setActionCommand("information");
        button3.addActionListener(this);
        button4.setActionCommand("save");
        button4.addActionListener(this);
        button5.setActionCommand("load");
        button5.addActionListener(this);
        button6.setActionCommand("quit");
        button6.addActionListener(this);
        button7.setActionCommand("addPatient");
        button7.addActionListener(this);
        button11.setActionCommand("search");
        button11.addActionListener(this);
        button13.setActionCommand("number");
        button13.addActionListener(this);
    }

    // EFFECTS: set every button command and add action listener
    public void backButton() {
        button8.setActionCommand("back1");
        button8.addActionListener(this);
        button9.setActionCommand("back2");
        button9.addActionListener(this);
        button10.setActionCommand("back3");
        button10.addActionListener(this);
        button12.setActionCommand("back4");
        button12.addActionListener(this);
        button14.setActionCommand("back5");
        button14.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: save file to given json document
    public void saveFile() throws FileNotFoundException {
        writer = new JsonWriter("./data/PatientCollection.json");
        writer.open();
        writer.write(pc);
        writer.close();
    }

    //EFFECTS: save patient file
    public void savePatientFile() {
        try {
            saveFile();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    //MODIFIES: this
    //EFFECTS: load file from given json document
    public void loadFile() throws IOException {
        reader = new JsonReader("./data/PatientCollection.json");
        pc = reader.read();
    }

    //EFFECTS: load patient file
    public void loadPatientFile() {
        try {
            loadFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // MODIFIES: this
    // EFFECTS: add patient to patient collection
    public void addPatientToPatientCollection() {
        patient = new Patient(text1.getText(), Integer.parseInt(text2.getText()), text3.getText(), text4.getText());
        pc.addPatient(patient);
    }

    // EFFECTS: performed action with given ActionEvent
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            initializeAddPatientPanel();
        } else if (e.getActionCommand().equals("list")) {
            initializeListPanel();
        } else if (e.getActionCommand().equals("information")) {
            initializeSearchInformationPanel();
        } else if (e.getActionCommand().equals("save")) {
            savePatientFile();
        } else if (e.getActionCommand().equals("load")) {
            loadPatientFile();
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else {
            moreActionPerformed(e);
        }
    }

    // EFFECTS: performed action with given ActionEvent
    public void moreActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addPatient")) {
            addPatientToPatientCollection();
        } else if (e.getActionCommand().equals("back1")) {
            backToMainPanelFromAddPatientPanel();
        } else if (e.getActionCommand().equals("back2")) {
            backToMainPanelFromListPanel();
        } else if (e.getActionCommand().equals("back3")) {
            backToMainPanelFromInformationPanel();
        } else if (e.getActionCommand().equals("search")) {
            initializeInformationPanel();
        } else if (e.getActionCommand().equals("back4")) {
            backToInformationPanelFromSearchInformationPanel();
        } else if (e.getActionCommand().equals("number")) {
            initializePatientNumberPanel();
        } else if (e.getActionCommand().equals("back5")) {
            backToMainPanelFromPatientNumberPanel();
        }
    }
}
