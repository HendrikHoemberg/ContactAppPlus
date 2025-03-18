package application;

import java.io.IOException;

import gui.GuiBuilder;

public class ContactAppPlus {
	
	public static void main(String args[]) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuiBuilder.initGui();
			}
		});
	}
}
