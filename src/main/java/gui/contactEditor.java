package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import application.Adress;
import application.Contact;

public class contactEditor {
	
	public static JPanel addCreateContactInputPanel(ArrayList<Contact> contactList) {
		JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Contact Details"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        

        JLabel fNameLabel = new JLabel("First Name:");
        JLabel lNameLabel = new JLabel("Last Name:");
        JLabel groupLabel = new JLabel("Group:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel addressLabel = new JLabel("Adress: ");
        JLabel emailAddressLabel = new JLabel("Email:");
        
        JTextField fNameField = new JTextField(20);
        JTextField lNameField = new JTextField(20);
        JTextField groupField = new JTextField(20);
        JTextField phoneNumberField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField emailAddressField = new JTextField(20);

        JButton createContactButton = new JButton("Create Contact");
        createContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Adress adress = null;
            	String group = null;
            	Contact newContact = new Contact(fNameField.getText(), lNameField.getText(), group, phoneNumberField.getText(), adress, emailAddressField.getText());
                contactList.add(newContact);
                GuiBuilder.refreshContactList();
            }
        });
        
        Dimension fieldSize = new Dimension(200, 25);
        fNameField.setPreferredSize(fieldSize);
        lNameField.setPreferredSize(fieldSize);
        groupField.setPreferredSize(fieldSize);
        phoneNumberField.setPreferredSize(fieldSize);
        addressField.setPreferredSize(fieldSize);
        emailAddressField.setPreferredSize(fieldSize);

        panel.add(fNameLabel);
        panel.add(fNameField);
        panel.add(lNameLabel);
        panel.add(lNameField);
        panel.add(groupLabel);
        panel.add(groupField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailAddressLabel);
        panel.add(emailAddressField);
        panel.add(createContactButton);
        
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
        
        layout.putConstraint(SpringLayout.SOUTH, createContactButton, 50, SpringLayout.SOUTH, emailAddressLabel);
        
        
        
        return panel;
    }
	
	public static JPanel addEditContactInputPanel(String contactId) {
		JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Contact Details"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        

        JLabel fNameLabel = new JLabel("First Name:");
        JLabel lNameLabel = new JLabel("Last Name:");
        JLabel groupLabel = new JLabel("Group:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel addressLabel = new JLabel("Adress: ");
        JLabel emailAddressLabel = new JLabel("Email:");
        
        JTextField fNameField = new JTextField(20);
        JTextField lNameField = new JTextField(20);
        JTextField groupField = new JTextField(20);
        JTextField phoneNumberField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField emailAddressField = new JTextField(20);

        Dimension fieldSize = new Dimension(200, 25);
        fNameField.setPreferredSize(fieldSize);
        lNameField.setPreferredSize(fieldSize);
        groupField.setPreferredSize(fieldSize);
        phoneNumberField.setPreferredSize(fieldSize);
        addressField.setPreferredSize(fieldSize);
        emailAddressField.setPreferredSize(fieldSize);

        panel.add(fNameLabel);
        panel.add(fNameField);
        panel.add(lNameLabel);
        panel.add(lNameField);
        panel.add(groupLabel);
        panel.add(groupField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailAddressLabel);
        panel.add(emailAddressField);
        
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
        
        
        return panel;
    }
	
	private static Contact createContact(JList contactList, String fName, String lName, 
			String group, String phoneNumber, Adress adress, String eMail) {
		Contact contact = new Contact(fName, lName, group, phoneNumber, adress, eMail);
		return contact;
	}
	
	
}
