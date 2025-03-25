package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import application.Contact;
import application.Utility;
import gui.ContactTableManager.ContactTableSelectionListener;

public class ContactGuiManager implements ContactTableSelectionListener {
    private ContactTableManager tableManager;
    
    // Swing components
    private JFrame mainFrame;
    private JTabbedPane tabbedPane;
    
    // Panels
    private JPanel tabPanel = new JPanel(new BorderLayout());
    private JPanel viewPanel;
    private JPanel editPanel;
    private JPanel newPanel;
    
    // View panel components
    private JTextField[] viewFields;
    private JPanel viewAddressPanel;
    private JButton viewAddressToggleButton;
    
    // Edit panel components
    private JTextField[] editFields;
    private JButton saveEditButton;
    private JPanel editAddressPanel;
    private JButton editAddressToggleButton;
    
    // New contact panel components
    private JTextField[] newFields;
    private JButton createContactButton;
    private JPanel newAddressPanel;
    private JButton newAddressToggleButton;

    // Address-related fields indices
    private static final int[] ADDRESS_FIELD_INDICES = {5, 6, 7, 8, 9, 10};

    public void initGui() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Initialize table manager
        tableManager = new ContactTableManager();
        tableManager.setSelectionListener(this);
        
        mainFrame = new JFrame("ContactAppPlus");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 800);
        mainFrame.setResizable(true);
        Dimension screenCenter = Utility.calculateScreenCenter(mainFrame);
        mainFrame.setLocation(screenCenter.width, screenCenter.height);
        
        mainFrame.setLayout(new BorderLayout());
        
        // Create the table panel (upper half)
        JPanel tablePanel = tableManager.createTablePanel();
        
        // Create the tabbed panel (lower half)
        JPanel tabbedPanel = createTabbedPanel();
        
        // Add panels to the main frame
        mainFrame.add(tablePanel, BorderLayout.CENTER);
        mainFrame.add(tabbedPanel, BorderLayout.SOUTH);
        
        mainFrame.setVisible(true);
    }
    
    private JPanel createTabbedPanel() {
        JPanel panel = tabPanel;
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
    
    private JPanel createViewPanel() {
        return createDetailPanel("Contact Details", true, false);
    }
    
    private JPanel createEditPanel() {
        JPanel panel = createDetailPanel("Edit Contact", false, false);
        
        saveEditButton = new JButton("Save Changes");
        saveEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContactChanges();
            }
        });
        
        SpringLayout layout = (SpringLayout)panel.getLayout();
        layout.putConstraint(SpringLayout.NORTH, saveEditButton, 20, SpringLayout.SOUTH, editAddressPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, saveEditButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        panel.add(saveEditButton);
        
        return panel;
    }
    
    private JPanel createNewPanel() {
        JPanel panel = createDetailPanel("Create New Contact", false, true);
        
        createContactButton = new JButton("Create Contact");
        createContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewContact();
            }
        });
        
        SpringLayout layout = (SpringLayout)panel.getLayout();
        layout.putConstraint(SpringLayout.NORTH, createContactButton, 20, SpringLayout.SOUTH, newAddressPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createContactButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        panel.add(createContactButton);
        
        return panel;
    }
    
    private JPanel createDetailPanel(String title, boolean isReadOnly, boolean isNewPanel) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        String[] labelTexts = {
            "First Name:", "Last Name:", "Group:", "Phone Number:", "Email:",
            "Street:", "House Nr:", "ZIP:", "City:", "State:", "Country:"
        };
        
        JLabel[] labels = new JLabel[labelTexts.length];
        JTextField[] fields = new JTextField[labelTexts.length];
        
        for (int i = 0; i < labelTexts.length; i++) {
            labels[i] = new JLabel(labelTexts[i]);
            fields[i] = new JTextField(20);
            
            Dimension fieldSize = new Dimension(200, 25);
            fields[i].setPreferredSize(fieldSize);
            
            if (isReadOnly) {
                fields[i].setEditable(false);
            }
            
            panel.add(labels[i]);
            panel.add(fields[i]);
        }
        
        // Create address section with collapsible panel
        JPanel addressSectionPanel = createAddressSection(labels, fields, isReadOnly, title);
        panel.add(addressSectionPanel);
        
        if (title.contains("View")) {
            viewFields = fields;
            viewAddressPanel = addressSectionPanel;
        } else if (title.contains("Edit")) {
            editFields = fields;
            editAddressPanel = addressSectionPanel;
        } else {
            newFields = fields;
            newAddressPanel = addressSectionPanel;
        }
        
        addDetailedLayoutConstraints(layout, panel, labels, fields, addressSectionPanel);
        
        return panel;
    }
    
    private JPanel createAddressSection(JLabel[] labels, JTextField[] fields, boolean isReadOnly, String title) {
        JPanel addressSectionPanel = new JPanel(new BorderLayout());
        
        // Address label and toggle button panel
        JPanel addressHeaderPanel = new JPanel(new BorderLayout());
        JLabel addressLabel = new JLabel("Address");
        addressHeaderPanel.add(addressLabel, BorderLayout.WEST);
        
        // Create a card layout panel to handle expand/collapse
        JPanel addressContentPanel = new JPanel(new CardLayout());
        JPanel collapsedPanel = new JPanel(); // Empty panel for collapsed state
        JPanel expandedPanel = new JPanel();
        expandedPanel.setPreferredSize(new Dimension(600, 200));
        
        // Expanded panel layout
        SpringLayout expandedLayout = new SpringLayout();
        expandedPanel.setLayout(expandedLayout);
        
        // Add address-related labels and fields to expanded panel
        for (int index : ADDRESS_FIELD_INDICES) {
            expandedPanel.add(labels[index]);
            expandedPanel.add(fields[index]);
            
            if (isReadOnly) {
                labels[index].setEnabled(false);
                fields[index].setEditable(false);
            }
        }
        
        // Add layout constraints for address panel
        addAddressLayoutConstraints(expandedLayout, expandedPanel, 
            labels[ADDRESS_FIELD_INDICES[0]], 
            labels[ADDRESS_FIELD_INDICES[1]], 
            labels[ADDRESS_FIELD_INDICES[2]], 
            labels[ADDRESS_FIELD_INDICES[3]], 
            labels[ADDRESS_FIELD_INDICES[4]], 
            labels[ADDRESS_FIELD_INDICES[5]],
            fields[ADDRESS_FIELD_INDICES[0]], 
            fields[ADDRESS_FIELD_INDICES[1]], 
            fields[ADDRESS_FIELD_INDICES[2]], 
            fields[ADDRESS_FIELD_INDICES[3]], 
            fields[ADDRESS_FIELD_INDICES[4]], 
            fields[ADDRESS_FIELD_INDICES[5]]
        );
        
        // Add panels to card layout
        addressContentPanel.add(collapsedPanel, "Collapsed");
        addressContentPanel.add(expandedPanel, "Expanded");
        
        JButton addressToggleButton = new JButton("Show");
        addressToggleButton.addActionListener(new ActionListener() {
            private boolean isExpanded = false;
            private CardLayout cardLayout = (CardLayout) addressContentPanel.getLayout();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                isExpanded = !isExpanded;
                
                if (isExpanded) {
                    cardLayout.show(addressContentPanel, "Expanded");
                    addressToggleButton.setText("Hide");
                    tabPanel.setPreferredSize(new Dimension(600, 550));
                } else {
                    cardLayout.show(addressContentPanel, "Collapsed");
                    addressToggleButton.setText("Show");
                    tabPanel.setPreferredSize(new Dimension(600, 350));
                }
            }
        });
        addressHeaderPanel.add(addressToggleButton, BorderLayout.EAST);
        
        addressSectionPanel.add(addressHeaderPanel, BorderLayout.NORTH);
        addressSectionPanel.add(addressContentPanel, BorderLayout.CENTER);
        
        // Set toggle button for appropriate tab
        if (title.contains("View")) {
            viewAddressToggleButton = addressToggleButton;
        } else if (title.contains("Edit")) {
            editAddressToggleButton = addressToggleButton;
        } else {
            newAddressToggleButton = addressToggleButton;
        }
        
        return addressSectionPanel;
    }
    
    private void addAddressLayoutConstraints(SpringLayout layout, JPanel panel, 
            JLabel streetLabel, JLabel houseNrLabel, 
            JLabel zipLabel, JLabel cityLabel, 
            JLabel stateLabel, JLabel countryLabel,
            JTextField streetField, JTextField houseNrField, 
            JTextField zipField, JTextField cityField, 
            JTextField stateField, JTextField countryField) {
    	
		int labelOffset = 10; 
		int fieldStartX = 120;  
		int rowSpacing = 20;
		
		// Street
		layout.putConstraint(SpringLayout.WEST, streetLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, streetField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, streetLabel, rowSpacing, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.NORTH, streetField, 0, SpringLayout.NORTH, streetLabel);
		
		// House Nr
		layout.putConstraint(SpringLayout.WEST, houseNrLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, houseNrField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, houseNrLabel, rowSpacing, SpringLayout.SOUTH, streetLabel);
		layout.putConstraint(SpringLayout.NORTH, houseNrField, 0, SpringLayout.NORTH, houseNrLabel);
		
		// ZIP
		layout.putConstraint(SpringLayout.WEST, zipLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, zipField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, zipLabel, rowSpacing, SpringLayout.SOUTH, houseNrLabel);
		layout.putConstraint(SpringLayout.NORTH, zipField, 0, SpringLayout.NORTH, zipLabel);
		
		// City
		layout.putConstraint(SpringLayout.WEST, cityLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, cityField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, cityLabel, rowSpacing, SpringLayout.SOUTH, zipLabel);
		layout.putConstraint(SpringLayout.NORTH, cityField, 0, SpringLayout.NORTH, cityLabel);
		
		// State
		layout.putConstraint(SpringLayout.WEST, stateLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, stateField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, stateLabel, rowSpacing, SpringLayout.SOUTH, cityLabel);
		layout.putConstraint(SpringLayout.NORTH, stateField, 0, SpringLayout.NORTH, stateLabel);
		
		// Country
		layout.putConstraint(SpringLayout.WEST, countryLabel, labelOffset, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, countryField, fieldStartX, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, countryLabel, rowSpacing, SpringLayout.SOUTH, stateLabel);
		layout.putConstraint(SpringLayout.NORTH, countryField, 0, SpringLayout.NORTH, countryLabel);
	}
    
    private void addDetailedLayoutConstraints(SpringLayout layout, JPanel panel, 
                                              JLabel[] labels, JTextField[] fields, 
                                              JPanel addressSectionPanel) {
        int labelOffset = 10; 
        int fieldStartX = 120;  
        int rowSpacing = 20;
        
        // Set constraints for first four labels/fields (non-address)
        for (int i = 0; i < 5; i++) {
            layout.putConstraint(SpringLayout.WEST, labels[i], labelOffset, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.WEST, fields[i], fieldStartX, SpringLayout.WEST, panel);
        }
        
        // First row
        layout.putConstraint(SpringLayout.NORTH, labels[0], rowSpacing, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, fields[0], 0, SpringLayout.NORTH, labels[0]);
        
        // Subsequent rows before address
        for (int i = 1; i < 5; i++) {
            layout.putConstraint(SpringLayout.NORTH, labels[i], rowSpacing, SpringLayout.SOUTH, labels[i-1]);
            layout.putConstraint(SpringLayout.NORTH, fields[i], 0, SpringLayout.NORTH, labels[i]);
        }
        
        // Address section positioning
        layout.putConstraint(SpringLayout.NORTH, addressSectionPanel, rowSpacing, SpringLayout.SOUTH, labels[4]);
        layout.putConstraint(SpringLayout.WEST, addressSectionPanel, 0, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.EAST, addressSectionPanel, 0, SpringLayout.EAST, panel);
    }
    
    // Implement the ContactTableSelectionListener interface
    @Override
    public void onContactSelected(Contact contact) {
        if (contact != null) {
            updateViewPanel(contact);
            updateEditPanel(contact);
            tabbedPane.setSelectedIndex(0); // Switch to view tab
        }
    }
    
    private void updateViewPanel(Contact contact) {
        updateFields(viewFields, contact);
    }
    
    private void updateEditPanel(Contact contact) {
        updateFields(editFields, contact);
    }
    
    private void updateFields(JTextField[] fields, Contact contact) {
        if (contact != null && fields != null) {
            fields[0].setText(contact.getfName());
            fields[1].setText(contact.getlName());
            fields[2].setText(contact.getGroup() != null ? contact.getGroup() : "");
            fields[3].setText(contact.getPhoneNumber());
            fields[4].setText(contact.getEmailAddress());
            fields[5].setText(contact.getStreet() != null ? contact.getStreet() : "");
            fields[6].setText(contact.getHouseNr() != null ? contact.getHouseNr() : "");
            fields[7].setText(contact.getZip() != null ? contact.getZip() : "");
            fields[8].setText(contact.getCity() != null ? contact.getCity() : "");
            fields[9].setText(contact.getState() != null ? contact.getState() : "");
            fields[10].setText(contact.getCountry() != null ? contact.getCountry() : "");
        } else {
            clearFields(fields);
        }
    }
    
    private void clearFields(JTextField[] fields) {
        if (fields != null) {
            for (JTextField field : fields) {
                field.setText("");
            }
        }
    }
    
    private void createNewContact() {
        // Validate and create a new contact from newFields
        String fName = newFields[0].getText();
        String lName = newFields[1].getText();
        String phoneNumber = newFields[3].getText();
        String email = newFields[4].getText();
        String group = newFields[2].getText();
        
        // Create a new contact with basic info
        Contact newContact = new Contact(fName, lName, group, phoneNumber, null, email);
        
        // Set the address components directly
        newContact.setStreet(newFields[5].getText());
        newContact.setHouseNr(newFields[6].getText());
        newContact.setZip(newFields[7].getText());
        newContact.setCity(newFields[8].getText());
        newContact.setState(newFields[9].getText());
        newContact.setCountry(newFields[10].getText());
        
        // Add contact to table manager
        tableManager.addContact(newContact);
        
        // Clear the new contact panel
        clearFields(newFields);
    }
    
    private void saveContactChanges() {
        Contact selectedContact = tableManager.getSelectedContact();
        if (selectedContact != null) {
            int selectedIndex = tableManager.getContactTable().getSelectedRow();
            
            selectedContact.setfName(editFields[0].getText());
            selectedContact.setlName(editFields[1].getText());
            selectedContact.setGroup(editFields[2].getText());
            selectedContact.setTelephoneNumber(editFields[3].getText());
            selectedContact.setEmailAddress(editFields[4].getText());
            
            // Update address components
            selectedContact.setStreet(editFields[5].getText());
            selectedContact.setHouseNr(editFields[6].getText());
            selectedContact.setZip(editFields[7].getText());
            selectedContact.setCity(editFields[8].getText());
            selectedContact.setState(editFields[9].getText());
            selectedContact.setCountry(editFields[10].getText());
            
            // Refresh contact list and reselect the row
            tableManager.refreshContactList();
            tableManager.getContactTable().setRowSelectionInterval(selectedIndex, selectedIndex);
        }
    }
}