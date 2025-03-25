package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.ContactGuiManager;
import repository.ContactRepository;

public class ContactAppPlus {
	
	public static void main(String args[]) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ContactGuiManager contactGuiManager = new ContactGuiManager();
				contactGuiManager.initGui();
			}
		});

		// List<Contact> contacts = new ArrayList<>();
		// ContactRepository repo = new ContactRepository();

		// contacts = repo.getAllContacts();

		// for (Contact contact : contacts) {
		// 	System.out.println(contact.getfName());
		// }

	}
}
