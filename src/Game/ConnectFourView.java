package Game;

import agenteIA.AgenteIA;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class ConnectFourView extends JFrame{
	
	/**
	 * Represents the Red Game.Disc.
	 */
	private ImageIcon redDiscIcon;
	
	/**
	 * Represents the Black Game.Disc.
	 */
	private ImageIcon blackDiscIcon;
	
	/**
	 * Represents a tile of the game board. This is always drawn whether there
	 * is a disc inside or not.
	 */
	private ImageIcon defaultIcon;
	
	/**
	 * Represents the current disc which can be red or black. 
	 */
	private ImageIcon currIcon;
	
	/**
	 * Represents the image drawn over the winning Connect-Four
	 */
	private ImageIcon winFlashTileIcon;
	
	/**
	 * Represents image of the restart button.
	 */
	private ImageIcon restartIcon;
	
	/**
	 * Represents the restart button which the user can click to reset the game.
	 */
	private JButton restartButton;
	
	/**
	 * Represents the font used to create the winning text.
	 */
	private Font font;
	
	/**
	 * Initializes a new instance of COnnectFourView with default values.
	 */
	public ConnectFourView() {
		this.redDiscIcon = new ImageIcon("images/RedDisc.png");
		this.blackDiscIcon = new ImageIcon("images/BlackDisc.png");
		this.defaultIcon = new ImageIcon("images/DefaultGameBoardPiece.png");
		this.winFlashTileIcon = new ImageIcon("images/WinFlashTile.png");
		this.restartIcon = new ImageIcon("images/RestartButton.png");	
		
		this.restartButton = new JButton(this.restartIcon);
	}

	public void addRestartButtonListener(ActionListener l) {
		this.restartButton.addActionListener(l);
	}
	
	public void addPanel(JPanel p) {
		this.add(p);
	}
	
	public JButton getRestartButton() {
		return this.restartButton;
	}
	
	public ImageIcon getRestartIcon() {
		return this.restartIcon;
	}
	
	public ImageIcon getRedDiscIcon() {
		return this.redDiscIcon;
	}
	
	public ImageIcon getBlackDiscIcon() {
		return this.blackDiscIcon;
	}
	
	public ImageIcon getDefaultIcon() {
		return this.defaultIcon;
	}
	
	public ImageIcon getCurrIcon() {
		return this.currIcon;
	}
	
	public void setCurrIcon(ImageIcon icon){
		this.currIcon = icon;
	}
	
	public ImageIcon getWinFlashIcon() {
		return this.winFlashTileIcon;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	public static void main (String args[]) {
		ConnectFourModel model = new ConnectFourModel();
		AgenteIA agenteIA = new AgenteIA(model);
		ConnectFourView view = new ConnectFourView();
		ConnectFourController controller = new ConnectFourController(view, model,agenteIA);
	}
}
