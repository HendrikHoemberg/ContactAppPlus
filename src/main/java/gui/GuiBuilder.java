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

import application.Adress;
import application.Contact;
import application.ContactGroup;
import application.Utility;

//test
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
    private static JTextField viewAddressField;
    private static JTextField viewEmailField;
    
    // Edit panel components
    private static JTextField editFNameField;
    private static JTextField editLNameField;
    private static JTextField editGroupField;
    private static JTextField editPhoneField;
    private static JTextField editAddressField;
    private static JTextField editEmailField;
    private static JButton saveEditButton;
    
    // New contact panel components
    private static JTextField newFNameField;
    private static JTextField newLNameField;
    private static JTextField newGroupField;
    private static JTextField newPhoneField;
    private static JTextField newAddressField;
    private static JTextField newEmailField;
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
        panel.setPreferredSize(new Dimension(600, 350));
        
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
        JLabel addressLabel = new JLabel("Address:");
        JLabel emailAddressLabel = new JLabel("Email:");
        
        viewFNameField = new JTextField(20);
        viewLNameField = new JTextField(20);
        viewGroupField = new JTextField(20);
        viewPhoneField = new JTextField(20);
        viewAddressField = new JTextField(20);
        viewEmailField = new JTextField(20);
        
        // Make fields non-editable
        viewFNameField.setEditable(false);
        viewLNameField.setEditable(false);
        viewGroupField.setEditable(false);
        viewPhoneField.setEditable(false);
        viewAddressField.setEditable(false);
        viewEmailField.setEditable(false);
        
        Dimension fieldSize = new Dimension(200, 25);
        viewFNameField.setPreferredSize(fieldSize);
        viewLNameField.setPreferredSize(fieldSize);
        viewGroupField.setPreferredSize(fieldSize);
        viewPhoneField.setPreferredSize(fieldSize);
        viewAddressField.setPreferredSize(fieldSize);
        viewEmailField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(viewFNameField);
        panel.add(lNameLabel);
        panel.add(viewLNameField);
        panel.add(groupLabel);
        panel.add(viewGroupField);
        panel.add(phoneNumberLabel);
        panel.add(viewPhoneField);
        panel.add(addressLabel);
        panel.add(viewAddressField);
        panel.add(emailAddressLabel);
        panel.add(viewEmailField);
        
        addLayoutConstraints(layout, panel, fNameLabel, viewFNameField, lNameLabel, viewLNameField,
                            groupLabel, viewGroupField, phoneNumberLabel, viewPhoneField,
                            addressLabel, viewAddressField, emailAddressLabel, viewEmailField);
        
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
        JLabel addressLabel = new JLabel("Address:");
        JLabel emailAddressLabel = new JLabel("Email:");
        
        editFNameField = new JTextField(20);
        editLNameField = new JTextField(20);
        editGroupField = new JTextField(20);
        editPhoneField = new JTextField(20);
        editAddressField = new JTextField(20);
        editEmailField = new JTextField(20);
        
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
        editAddressField.setPreferredSize(fieldSize);
        editEmailField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(editFNameField);
        panel.add(lNameLabel);
        panel.add(editLNameField);
        panel.add(groupLabel);
        panel.add(editGroupField);
        panel.add(phoneNumberLabel);
        panel.add(editPhoneField);
        panel.add(addressLabel);
        panel.add(editAddressField);
        panel.add(emailAddressLabel);
        panel.add(editEmailField);
        panel.add(saveEditButton);
        
        addLayoutConstraints(layout, panel, fNameLabel, editFNameField, lNameLabel, editLNameField,
                            groupLabel, editGroupField, phoneNumberLabel, editPhoneField,
                            addressLabel, editAddressField, emailAddressLabel, editEmailField);
        
        // Add constraint for save button
        layout.putConstraint(SpringLayout.NORTH, saveEditButton, 20, SpringLayout.SOUTH, emailAddressLabel);
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
        JLabel addressLabel = new JLabel("Address:");
        JLabel emailAddressLabel = new JLabel("Email:");
        
        newFNameField = new JTextField(20);
        newLNameField = new JTextField(20);
        newGroupField = new JTextField(20);
        newPhoneField = new JTextField(20);
        newAddressField = new JTextField(20);
        newEmailField = new JTextField(20);
        
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
        newAddressField.setPreferredSize(fieldSize);
        newEmailField.setPreferredSize(fieldSize);
        
        panel.add(fNameLabel);
        panel.add(newFNameField);
        panel.add(lNameLabel);
        panel.add(newLNameField);
        panel.add(groupLabel);
        panel.add(newGroupField);
        panel.add(phoneNumberLabel);
        panel.add(newPhoneField);
        panel.add(addressLabel);
        panel.add(newAddressField);
        panel.add(emailAddressLabel);
        panel.add(newEmailField);
        panel.add(createContactButton);
        
        addLayoutConstraints(layout, panel, fNameLabel, newFNameField, lNameLabel, newLNameField,
                            groupLabel, newGroupField, phoneNumberLabel, newPhoneField,
                            addressLabel, newAddressField, emailAddressLabel, newEmailField);
        
        // Add constraint for create button
        layout.putConstraint(SpringLayout.NORTH, createContactButton, 20, SpringLayout.SOUTH, emailAddressLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createContactButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        return panel;
    }
    
    private static void addLayoutConstraints(SpringLayout layout, JPanel panel,
                                           JLabel fNameLabel, JTextField fNameField,
                                           JLabel lNameLabel, JTextField lNameField,
                                           JLabel groupLabel, JTextField groupField,
                                           JLabel phoneNumberLabel, JTextField phoneNumberField,
                                           JLabel addressLabel, JTextField addressField,
                                           JLabel emailAddressLabel, JTextField emailAddressField) {
        int labelOffset = 10; 
        int fieldStartX = 120;  
        int rowSpacing = 20;
        
        layout.putConstraint(SpringLayout.WEST, fNameLabel, labelOffset, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, lNameLabel, labelOffset, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, groupLabel, labelOffset, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberLabel, labelOffset, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, labelOffset, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, emailAddressLabel, labelOffset, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, fNameField, fieldStartX, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, lNameField, fieldStartX, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, groupField, fieldStartX, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberField, fieldStartX, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, addressField, fieldStartX, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, emailAddressField, fieldStartX, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, fNameLabel, rowSpacing, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, fNameField, 0, SpringLayout.NORTH, fNameLabel);

        layout.putConstraint(SpringLayout.NORTH, lNameLabel, rowSpacing, SpringLayout.SOUTH, fNameLabel);
        layout.putConstraint(SpringLayout.NORTH, lNameField, 0, SpringLayout.NORTH, lNameLabel);

        layout.putConstraint(SpringLayout.NORTH, groupLabel, rowSpacing, SpringLayout.SOUTH, lNameLabel);
        layout.putConstraint(SpringLayout.NORTH, groupField, 0, SpringLayout.NORTH, groupLabel);

        layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, rowSpacing, SpringLayout.SOUTH, groupLabel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberField, 0, SpringLayout.NORTH, phoneNumberLabel);

        layout.putConstraint(SpringLayout.NORTH, addressLabel, rowSpacing, SpringLayout.SOUTH, phoneNumberLabel);
        layout.putConstraint(SpringLayout.NORTH, addressField, 0, SpringLayout.NORTH, addressLabel);

        layout.putConstraint(SpringLayout.NORTH, emailAddressLabel, rowSpacing, SpringLayout.SOUTH, addressLabel);
        layout.putConstraint(SpringLayout.NORTH, emailAddressField, 0, SpringLayout.NORTH, emailAddressLabel);
    }
    
    private static void updateViewPanel(Contact contact) {
        if (contact != null) {
            viewFNameField.setText(contact.getfName());
            viewLNameField.setText(contact.getlName());
            viewGroupField.setText(contact.getGroup() != null ? contact.getGroup().toString() : "");
            viewPhoneField.setText(contact.getPhoneNumber());
            viewAddressField.setText(contact.getAddress() != null ? contact.getAddress().toString() : "");
            viewEmailField.setText(contact.getEmailAddress());
        } else {
            clearViewPanel();
        }
    }
    
    private static void updateEditPanel(Contact contact) {
        if (contact != null) {
            editFNameField.setText(contact.getfName());
            editLNameField.setText(contact.getlName());
            editGroupField.setText(contact.getGroup() != null ? contact.getGroup().toString() : "");
            editPhoneField.setText(contact.getPhoneNumber());
            editAddressField.setText(contact.getAddress() != null ? contact.getAddress().toString() : "");
            editEmailField.setText(contact.getEmailAddress());
        } else {
            clearEditPanel();
        }
    }
    
    private static void clearViewPanel() {
        viewFNameField.setText("");
        viewLNameField.setText("");
        viewGroupField.setText("");
        viewPhoneField.setText("");
        viewAddressField.setText("");
        viewEmailField.setText("");
    }
    
    private static void clearEditPanel() {
        editFNameField.setText("");
        editLNameField.setText("");
        editGroupField.setText("");
        editPhoneField.setText("");
        editAddressField.setText("");
        editEmailField.setText("");
    }
    
    private static void clearNewPanel() {
        newFNameField.setText("");
        newLNameField.setText("");
        newGroupField.setText("");
        newPhoneField.setText("");
        newAddressField.setText("");
        newEmailField.setText("");
    }
    
    private static void createNewContact() {
        String fName = newFNameField.getText();
        String lName = newLNameField.getText();
        String phoneNumber = newPhoneField.getText();
        String email = newEmailField.getText();
        
        // Currently not handling group and address properly
        ContactGroup group = null;
        Adress address = null;
        
        Contact newContact = new Contact(fName, lName, group, phoneNumber, address, email);
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
            selectedContact.setTelephoneNumber(editPhoneField.getText());
            selectedContact.setEmailAddress(editEmailField.getText());
            
            // Currently not handling group and address properly
            
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
                contact.getGroup() != null ? contact.getGroup().toString() : "",
                contact.getPhoneNumber(),
                contact.getEmailAddress()
            };
            tableModel.addRow(rowData);
        }
    }
}