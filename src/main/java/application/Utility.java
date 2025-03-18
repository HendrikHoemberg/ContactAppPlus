package application;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Utility {
	public static Dimension calculateScreenCenter(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		return new Dimension(x, y);
	}
}
