package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.GuiBuilder;

public class ContactAppPlus {
	
	public static void main(String args[]) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuiBuilder.initGui();
			}
		});

		List<Contact> = new ArrayList<>();
	}
}
