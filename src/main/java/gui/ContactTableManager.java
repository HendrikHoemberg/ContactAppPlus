package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import application.Contact;

public class ContactTableManager {
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private ArrayList<Contact> contactList;
    private ContactTableSelectionListener selectionListener;

    public ContactTableManager() {
        this.contactList = new ArrayList<>();
        initializeTable();
    }

    private void initializeTable() {
        String[] columnNames = {"First Name", "Last Name", "Group", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        contactTable = new JTable(tableModel);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Contact List"));
        
        // Add selection listener
        contactTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Contact selectedContact = getSelectedContact();
                    if (selectedContact != null && selectionListener != null) {
                        selectionListener.onContactSelected(selectedContact);
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(contactTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    public void setSelectionListener(ContactTableSelectionListener listener) {
        this.selectionListener = listener;
    }

    public Contact getSelectedContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < contactList.size()) {
            return contactList.get(selectedRow);
        }
        return null;
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        refreshContactList();
    }

    public void refreshContactList() {
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

    public JTable getContactTable() {
        return contactTable;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    // Interface for contact selection callback
    public interface ContactTableSelectionListener {
        void onContactSelected(Contact contact);
    }
}