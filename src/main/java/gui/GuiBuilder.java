package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import application.Contact;
import application.Utility;

public class GuiBuilder {
    private static JTable contactTable;
    private static DefaultTableModel tableModel;
    private static ArrayList<Contact> contactList = new ArrayList<>();
    private static JTabbedPane tabbedPane;
    private static JPanel viewPanel;
    private static JPanel editPanel;
    private static JPanel newPanel;
    
    // View panel components
    private static JTextField viewFNameField;
    private static JTextField viewLNameField;
    private static JTextField viewGroupField;
    private static JTextField viewPhoneField;
    private static JTextField viewEmailField;
    private static JTextField viewStreetField;
    private static JTextField viewHouseNrField;
    private static JTextField viewZipField;
    private static JTextField viewCityField;
    private static JTextField viewStateField;
    private static JTextField viewCountryField;
    
    // Edit panel components
    private static JTextField editFNameField;
    private static JTextField editLNameField;
    private static JTextField editGroupField;
    private static JTextField editPhoneField;
    private static JTextField editEmailField;
    private static JTextField editStreetField;
    private static JTextField editHouseNrField;
    private static JTextField editZipField;
    private static JTextField editCityField;
    private static JTextField editStateField;
    private static JTextField editCountryField;
    private static JButton saveEditButton;
    
    // New contact panel components
    private static JTextField newFNameField;
    private static JTextField newLNameField;
    private static JTextField newGroupField;
    private static JTextField newPhoneField;
    private static JTextField newEmailField;
    private static JTextField newStreetField;
    private static JTextField newHouseNrField;
    private static JTextField newZipField;
    private static JTextField newCityField;
    private static JTextField newStateField;
    private static JTextField newCountryField;
    private static JButton createContactButton;
    
    public static void initGui() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame mainFrame = new JFrame("ContactAppPlus");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 800);
        mainFrame.setResizable(true);
        Dimension screenCenter = Utility.calculateScreenCenter(mainFrame);
        mainFrame.setLocation(screenCenter.width, screenCenter.height);
        
        mainFrame.setLayout(new BorderLayout());
        
        // Create the table panel (upper half)
        JPanel tablePanel = createTablePanel();
        
        // Create the tabbed panel (lower half)
        JPanel tabbedPanel = createTabbedPanel();
        
        // Add panels to the main frame
        mainFrame.add(tablePanel, BorderLayout.CENTER);
        mainFrame.add(tabbedPanel, BorderLayout.SOUTH);
        
        mainFrame.setVisible(true);
    }
    
    private static JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Contact List"));
        
        String[] columnNames = {"First Name", "Last Name", "Group", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        contactTable = new JTable(tableModel);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add selection listener to update the view panel when a row is selected
        contactTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Contact selectedContact = getSelectedContact();
                    if (selectedContact != null) {
                        updateViewPanel(selectedContact);
                        updateEditPanel(selectedContact);
                        tabbedPane.setSelectedIndex(0); // Switch to view tab
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(contactTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private static JPanel createTabbedPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 500)); // Increased height to accommodate more fields
        
        tabbedPane = new JTabbedPane();
        
        // Create the three tabs
        viewPanel = createViewPanel();
        editPanel = createEditPanel();
        newPanel = createNewPanel();
        
        tabbedPane.addTab("View Contact", viewPanel);
        tabbedPane.addTab("Edit Contact", editPanel);
        tabbedPane.addTab("New Contact", newPanel);
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private static JPanel createViewPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Contact Details"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        JLabel fNameLabel = new JLabel("First Name:");
        JLabel lNameLabel = new JLabel("Last Name:");
        JLabel groupLabel = new JLabel("Group:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailAddressLabel = new JLabel("Email:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel houseNrLabel = new JLabel("House Nr:");
        JLabel zipLabel = new JLabel("ZIP:");
        JLabel cityLabel = new JLabel("City:");
        JLabel stateLabel = new JLabel("State:");
        JLabel countryLabel = new JLabel("Country:");
        
        viewFNameField = new JTextField(20);
        viewLNameField = new JTextField(20);
        viewGroupField = new JTextField(20);
        viewPhoneField = new JTextField(20);
        viewEmailField = new JTextField(20);
        viewStreetField = new JTextField(20);
        viewHouseNrField = new JTextField(20);
        viewZipField = new JTextField(20);
        viewCityField = new JTextField(20);
        viewStateField = new JTextField(20);
        viewCountryField = new JTextField(20);
        
        // Make fields non-editable
        viewFNameField.setEditable(false);
        viewLNameField.setEditable(false);
        viewGroupField.setEditable(false);
        viewPhoneField.setEditable(false);
        viewEmailField.setEditable(false);
        viewStreetField.setEditable(false);
        viewHouseNrField.setEditable(false);
        viewZipField.setEditable(false);
        viewCityField.setEditable(false);
        viewStateField.setEditable(false);
        viewCountryField.setEditable(false);
        
        Dimension fieldSize = new Dimension(200, 25);
        viewFNameField.setPreferredSize(fieldSize);
        viewLNameField.setPreferredSize(fieldSize);
        viewGroupField.setPreferredSize(fieldSize);
        viewPhoneField.setPreferredSize(fieldSize);
        viewEmailField.setPreferredSize(fieldSize);
        viewStreetField.setPreferredSize(fieldSize);
        viewHouseNrField.setPreferredSize(fieldSize);
        viewZipField.setPreferredSize(fieldSize);
        viewCityField.setPreferredSize(fieldSize);
        viewStateField.setPreferredSize(fieldSize);
        viewCountryField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(viewFNameField);
        panel.add(lNameLabel);
        panel.add(viewLNameField);
        panel.add(groupLabel);
        panel.add(viewGroupField);
        panel.add(phoneNumberLabel);
        panel.add(viewPhoneField);
        panel.add(emailAddressLabel);
        panel.add(viewEmailField);
        panel.add(streetLabel);
        panel.add(viewStreetField);
        panel.add(houseNrLabel);
        panel.add(viewHouseNrField);
        panel.add(zipLabel);
        panel.add(viewZipField);
        panel.add(cityLabel);
        panel.add(viewCityField);
        panel.add(stateLabel);
        panel.add(viewStateField);
        panel.add(countryLabel);
        panel.add(viewCountryField);
        
        addDetailedLayoutConstraints(layout, panel);
        
        return panel;
    }
    
    private static JPanel createEditPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Edit Contact"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        JLabel fNameLabel = new JLabel("First Name:");
        JLabel lNameLabel = new JLabel("Last Name:");
        JLabel groupLabel = new JLabel("Group:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailAddressLabel = new JLabel("Email:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel houseNrLabel = new JLabel("House Nr:");
        JLabel zipLabel = new JLabel("ZIP:");
        JLabel cityLabel = new JLabel("City:");
        JLabel stateLabel = new JLabel("State:");
        JLabel countryLabel = new JLabel("Country:");
        
        editFNameField = new JTextField(20);
        editLNameField = new JTextField(20);
        editGroupField = new JTextField(20);
        editPhoneField = new JTextField(20);
        editEmailField = new JTextField(20);
        editStreetField = new JTextField(20);
        editHouseNrField = new JTextField(20);
        editZipField = new JTextField(20);
        editCityField = new JTextField(20);
        editStateField = new JTextField(20);
        editCountryField = new JTextField(20);
        
        saveEditButton = new JButton("Save Changes");
        saveEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContactChanges();
            }
        });
        
        Dimension fieldSize = new Dimension(200, 25);
        editFNameField.setPreferredSize(fieldSize);
        editLNameField.setPreferredSize(fieldSize);
        editGroupField.setPreferredSize(fieldSize);
        editPhoneField.setPreferredSize(fieldSize);
        editEmailField.setPreferredSize(fieldSize);
        editStreetField.setPreferredSize(fieldSize);
        editHouseNrField.setPreferredSize(fieldSize);
        editZipField.setPreferredSize(fieldSize);
        editCityField.setPreferredSize(fieldSize);
        editStateField.setPreferredSize(fieldSize);
        editCountryField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(editFNameField);
        panel.add(lNameLabel);
        panel.add(editLNameField);
        panel.add(groupLabel);
        panel.add(editGroupField);
        panel.add(phoneNumberLabel);
        panel.add(editPhoneField);
        panel.add(emailAddressLabel);
        panel.add(editEmailField);
        panel.add(streetLabel);
        panel.add(editStreetField);
        panel.add(houseNrLabel);
        panel.add(editHouseNrField);
        panel.add(zipLabel);
        panel.add(editZipField);
        panel.add(cityLabel);
        panel.add(editCityField);
        panel.add(stateLabel);
        panel.add(editStateField);
        panel.add(countryLabel);
        panel.add(editCountryField);
        panel.add(saveEditButton);
        
        addDetailedLayoutConstraints(layout, panel);
        
        // Add constraint for save button
        layout.putConstraint(SpringLayout.NORTH, saveEditButton, 20, SpringLayout.SOUTH, countryLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, saveEditButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        return panel;
    }
    
    private static JPanel createNewPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Create New Contact"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        JLabel fNameLabel = new JLabel("First Name:");
        JLabel lNameLabel = new JLabel("Last Name:");
        JLabel groupLabel = new JLabel("Group:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailAddressLabel = new JLabel("Email:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel houseNrLabel = new JLabel("House Nr:");
        JLabel zipLabel = new JLabel("ZIP:");
        JLabel cityLabel = new JLabel("City:");
        JLabel stateLabel = new JLabel("State:");
        JLabel countryLabel = new JLabel("Country:");
        
        newFNameField = new JTextField(20);
        newLNameField = new JTextField(20);
        newGroupField = new JTextField(20);
        newPhoneField = new JTextField(20);
        newEmailField = new JTextField(20);
        newStreetField = new JTextField(20);
        newHouseNrField = new JTextField(20);
        newZipField = new JTextField(20);
        newCityField = new JTextField(20);
        newStateField = new JTextField(20);
        newCountryField = new JTextField(20);
        
        createContactButton = new JButton("Create Contact");
        createContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewContact();
            }
        });
        
        Dimension fieldSize = new Dimension(200, 25);
        newFNameField.setPreferredSize(fieldSize);
        newLNameField.setPreferredSize(fieldSize);
        newGroupField.setPreferredSize(fieldSize);
        newPhoneField.setPreferredSize(fieldSize);
        newEmailField.setPreferredSize(fieldSize);
        newStreetField.setPreferredSize(fieldSize);
        newHouseNrField.setPreferredSize(fieldSize);
        newZipField.setPreferredSize(fieldSize);
        newCityField.setPreferredSize(fieldSize);
        newStateField.setPreferredSize(fieldSize);
        newCountryField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(newFNameField);
        panel.add(lNameLabel);
        panel.add(newLNameField);
        panel.add(groupLabel);
        panel.add(newGroupField);
        panel.add(phoneNumberLabel);
        panel.add(newPhoneField);
        panel.add(emailAddressLabel);
        panel.add(newEmailField);
        panel.add(streetLabel);
        panel.add(newStreetField);
        panel.add(houseNrLabel);
        panel.add(newHouseNrField);
        panel.add(zipLabel);
        panel.add(newZipField);
        panel.add(cityLabel);
        panel.add(newCityField);
        panel.add(stateLabel);
        panel.add(newStateField);
        panel.add(countryLabel);
        panel.add(newCountryField);
        panel.add(createContactButton);
        
        addDetailedLayoutConstraints(layout, panel);
        
        // Add constraint for create button
        layout.putConstraint(SpringLayout.NORTH, createContactButton, 20, SpringLayout.SOUTH, countryLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createContactButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        return panel;
    }
    
    private static void addDetailedLayoutConstraints(SpringLayout layout, JPanel panel) {
        // Get all labels
        JLabel[] labels = new JLabel[11];
        JTextField[] fields = new JTextField[11];
        
        // Find all labels and fields in the panel
        int labelIndex = 0;
        int fieldIndex = 0;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JLabel) {
                labels[labelIndex++] = (JLabel) panel.getComponent(i);
            } else if (panel.getComponent(i) instanceof JTextField) {
                fields[fieldIndex++] = (JTextField) panel.getComponent(i);
            }
        }
        
        // Sort labels to ensure proper order
        // (This is a simplified approach, in a real application we would have a more reliable way to access components)
        
        int labelOffset = 10; 
        int fieldStartX = 120;  
        int rowSpacing = 20;
        
        // Set constraints for all labels (left alignment)
        for (int i = 0; i < labels.length; i++) {
            layout.putConstraint(SpringLayout.WEST, labels[i], labelOffset, SpringLayout.WEST, panel);
        }
        
        // Set constraints for all fields (left alignment)
        for (int i = 0; i < fields.length; i++) {
            layout.putConstraint(SpringLayout.WEST, fields[i], fieldStartX, SpringLayout.WEST, panel);
        }
        
        // Set vertical positioning
        // First row
        layout.putConstraint(SpringLayout.NORTH, labels[0], rowSpacing, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, fields[0], 0, SpringLayout.NORTH, labels[0]);
        
        // Subsequent rows
        for (int i = 1; i < labels.length; i++) {
            layout.putConstraint(SpringLayout.NORTH, labels[i], rowSpacing, SpringLayout.SOUTH, labels[i-1]);
            layout.putConstraint(SpringLayout.NORTH, fields[i], 0, SpringLayout.NORTH, labels[i]);
        }
    }
    
    private static void updateViewPanel(Contact contact) {
        if (contact != null) {
            viewFNameField.setText(contact.getfName());
            viewLNameField.setText(contact.getlName());
            viewGroupField.setText(contact.getGroup() != null ? contact.getGroup() : "");
            viewPhoneField.setText(contact.getPhoneNumber());
            viewEmailField.setText(contact.getEmailAddress());
            viewStreetField.setText(contact.getStreet() != null ? contact.getStreet() : "");
            viewHouseNrField.setText(contact.getHouseNr() != null ? contact.getHouseNr() : "");
            viewZipField.setText(contact.getZip() != null ? contact.getZip() : "");
            viewCityField.setText(contact.getCity() != null ? contact.getCity() : "");
            viewStateField.setText(contact.getState() != null ? contact.getState() : "");
            viewCountryField.setText(contact.getCountry() != null ? contact.getCountry() : "");
        } else {
            clearViewPanel();
        }
    }
    
    private static void updateEditPanel(Contact contact) {
        if (contact != null) {
            editFNameField.setText(contact.getfName());
            editLNameField.setText(contact.getlName());
            editGroupField.setText(contact.getGroup() != null ? contact.getGroup() : "");
            editPhoneField.setText(contact.getPhoneNumber());
            editEmailField.setText(contact.getEmailAddress());
            editStreetField.setText(contact.getStreet() != null ? contact.getStreet() : "");
            editHouseNrField.setText(contact.getHouseNr() != null ? contact.getHouseNr() : "");
            editZipField.setText(contact.getZip() != null ? contact.getZip() : "");
            editCityField.setText(contact.getCity() != null ? contact.getCity() : "");
            editStateField.setText(contact.getState() != null ? contact.getState() : "");
            editCountryField.setText(contact.getCountry() != null ? contact.getCountry() : "");
        } else {
            clearEditPanel();
        }
    }
    
    private static void clearViewPanel() {
        viewFNameField.setText("");
        viewLNameField.setText("");
        viewGroupField.setText("");
        viewPhoneField.setText("");
        viewEmailField.setText("");
        viewStreetField.setText("");
        viewHouseNrField.setText("");
        viewZipField.setText("");
        viewCityField.setText("");
        viewStateField.setText("");
        viewCountryField.setText("");
    }
    
    private static void clearEditPanel() {
        editFNameField.setText("");
        editLNameField.setText("");
        editGroupField.setText("");
        editPhoneField.setText("");
        editEmailField.setText("");
        editStreetField.setText("");
        editHouseNrField.setText("");
        editZipField.setText("");
        editCityField.setText("");
        editStateField.setText("");
        editCountryField.setText("");
    }
    
    private static void clearNewPanel() {
        newFNameField.setText("");
        newLNameField.setText("");
        newGroupField.setText("");
        newPhoneField.setText("");
        newEmailField.setText("");
        newStreetField.setText("");
        newHouseNrField.setText("");
        newZipField.setText("");
        newCityField.setText("");
        newStateField.setText("");
        newCountryField.setText("");
    }
    
    private static void createNewContact() {
        String fName = newFNameField.getText();
        String lName = newLNameField.getText();
        String phoneNumber = newPhoneField.getText();
        String email = newEmailField.getText();
        String group = newGroupField.getText();
        
        // Create a new contact with basic info
        Contact newContact = new Contact(fName, lName, group, phoneNumber, null, email);
        
        // Set the address components directly
        newContact.setStreet(newStreetField.getText());
        newContact.setHouseNr(newHouseNrField.getText());
        newContact.setZip(newZipField.getText());
        newContact.setCity(newCityField.getText());
        newContact.setState(newStateField.getText());
        newContact.setCountry(newCountryField.getText());
        
        contactList.add(newContact);
        
        refreshContactList();
        clearNewPanel();
    }
    
    private static void saveContactChanges() {
        Contact selectedContact = getSelectedContact();
        if (selectedContact != null) {
            int selectedIndex = contactTable.getSelectedRow();
            
            selectedContact.setfName(editFNameField.getText());
            selectedContact.setlName(editLNameField.getText());
            selectedContact.setGroup(editGroupField.getText());
            selectedContact.setTelephoneNumber(editPhoneField.getText());
            selectedContact.setEmailAddress(editEmailField.getText());
            
            // Update address components
            selectedContact.setStreet(editStreetField.getText());
            selectedContact.setHouseNr(editHouseNrField.getText());
            selectedContact.setZip(editZipField.getText());
            selectedContact.setCity(editCityField.getText());
            selectedContact.setState(editStateField.getText());
            selectedContact.setCountry(editCountryField.getText());
            
            refreshContactList();
            contactTable.setRowSelectionInterval(selectedIndex, selectedIndex);
        }
    }
    
    public static Contact getSelectedContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < contactList.size()) {
            return contactList.get(selectedRow);
        }
        return null;
    }
    
    public static void refreshContactList() {
        tableModel.setRowCount(0);
        
        for (Contact contact : contactList) {
            Object[] rowData = {
                contact.getfName(),
                contact.getlName(),
                contact.getGroup() != null ? contact.getGroup() : "",
                contact.getPhoneNumber(),
                contact.getEmailAddress()
            };
            tableModel.addRow(rowData);
        }
    }
}