package cz.uhk.fim.main;

import javax.swing.SwingUtilities;

import cz.uhk.fim.gui.MainFrame;

/*
 * Class only to start app
 */
public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				});
			});
		});
	}

}
