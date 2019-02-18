package cz.uhk.fim.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * 
 * @author Hajek Class to load image
 *
 */
public class ImageMaker {
	private Image downhead;
	private Image uphead;
	private Image lefthead;
	private Image righthead;
	private Image body;
	private Image fullbody;

	private Image reddownmouth;
	private Image redupmouth;
	private Image redleftmouth;
	private Image redrightmouth;
	private Image redbody;
	private Image redfullbody;

	private Image bonus;
	private Image malus;
	private Image tilesnake;
	private Image gameover;
	private boolean loadPicture = false;
	private static ImageMaker instance;

	// private constructor but static metod for instance
	private ImageMaker() {
		try {
			/**
			 * read picture
			 */
			downhead = ImageIO.read(new File("images/downhead.png"));
			uphead = ImageIO.read(new File("images/uphead.png"));
			lefthead = ImageIO.read(new File("images/lefthead.png"));
			righthead = ImageIO.read(new File("images/righthead.png"));
			body = ImageIO.read(new File("images/body.png"));
			fullbody = ImageIO.read(new File("images/fullbody.png"));

			reddownmouth = ImageIO.read(new File("images/reddownhead.png"));
			redupmouth = ImageIO.read(new File("images/reduphead.png"));
			redleftmouth = ImageIO.read(new File("images/redlefthead.png"));
			redrightmouth = ImageIO.read(new File("images/redrighthead.png"));
			redbody = ImageIO.read(new File("images/redbody.png"));
			redfullbody = ImageIO.read(new File("images/redfullbody.png"));

			bonus = ImageIO.read(new File("images/bonus.png"));
			malus = ImageIO.read(new File("images/malus.png"));
			tilesnake = ImageIO.read(new File("images/snaketile.png"));
			gameover = ImageIO.read(new File("images/gameover.png"));

			// all picture read successful
			loadPicture = true;
		} catch (IOException e) {
			// some picture read failed .. redundance but for better reading
			// code
			loadPicture = false;
			JOptionPane.showMessageDialog(null, "Chyba, textury nebyli nenačteny! Zkontrolujte složku images", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// for only one read from file
	public static ImageMaker getInstance() {
		if (instance == null) {
			instance = new ImageMaker();
		}
		return instance;
	}

	public Image getDownmouth() {
		return downhead;
	}

	public void setDownmouth(Image downmouth) {
		this.downhead = downmouth;
	}

	public Image getUpmouth() {
		return uphead;
	}

	public void setUpmouth(Image upmouth) {
		this.uphead = upmouth;
	}

	public Image getLeftmouth() {
		return lefthead;
	}

	public void setLeftmouth(Image leftmouth) {
		this.lefthead = leftmouth;
	}

	public Image getRightmouth() {
		return righthead;
	}

	public void setRightmouth(Image rightmouth) {
		this.righthead = rightmouth;
	}

	public Image getBody() {
		return body;
	}

	public void setBody(Image body) {
		this.body = body;
	}

	public Image getBonus() {
		return bonus;
	}

	public void setBonus(Image bonus) {
		this.bonus = bonus;
	}

	public void setMalus(Image malus) {
		this.malus = malus;
	}

	public Image getMalus() {
		return malus;
	}

	public Image getTilesnake() {
		return tilesnake;
	}

	public void setTilesnake(Image tilesnake) {
		this.tilesnake = tilesnake;
	}

	public Image getGameover() {
		return gameover;
	}

	public void setGameover(Image gameover) {
		this.gameover = gameover;
	}

	public boolean isReadyPicture() {
		return loadPicture;
	}

	public void setReadyPicture(boolean readyPicture) {
		this.loadPicture = readyPicture;
	}

	public Image getFullbody() {
		return fullbody;
	}

	public void setFullbody(Image fullbody) {
		this.fullbody = fullbody;
	}

	public Image getReddownmouth() {
		return reddownmouth;
	}

	public void setReddownmouth(Image reddownmouth) {
		this.reddownmouth = reddownmouth;
	}

	public Image getRedupmouth() {
		return redupmouth;
	}

	public void setRedupmouth(Image redupmouth) {
		this.redupmouth = redupmouth;
	}

	public Image getRedleftmouth() {
		return redleftmouth;
	}

	public void setRedleftmouth(Image redleftmouth) {
		this.redleftmouth = redleftmouth;
	}

	public Image getRedrightmouth() {
		return redrightmouth;
	}

	public void setRedrightmouth(Image redrightmouth) {
		this.redrightmouth = redrightmouth;
	}

	public Image getRedbody() {
		return redbody;
	}

	public void setRedbody(Image redbody) {
		this.redbody = redbody;
	}

	public Image getRedfullbody() {
		return redfullbody;
	}

	public void setRedfullbody(Image redfullbody) {
		this.redfullbody = redfullbody;
	}

}
