package cz.uhk.fim.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import cz.uhk.fim.model.GameBoard;

/**
 * 
 * @author Hájek 
 * Main frame
 */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;

	public MainFrame() {
		createWindow();
		setResizable(false);
		setLocationRelativeTo(null);
		addGameBoard();
	}

	/**
	 * Create frame
	 */
	private void createWindow() {
		setLayout(new BorderLayout());
		setSize(new Dimension(780, 590));
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
	}

	private void addGameBoard() {
		gameBoard = new GameBoard();
		add(gameBoard, BorderLayout.CENTER);
	}
}
