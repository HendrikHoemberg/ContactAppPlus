package application;

import java.io.IOException;

import gui.ContactGuiManager;

public class ContactAppPlus {
	
	public static void main(String args[]) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ContactGuiManager contactGuiManager = new ContactGuiManager();
				contactGuiManager.initGui();
			}
		});
	}
}
