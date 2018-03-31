package game;

import agenteIA.AgenteIA;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectFourView extends JFrame{
	
	private ImageIcon redDiscIcon;
	private ImageIcon blackDiscIcon;
	private ImageIcon defaultIcon;
	private ImageIcon currIcon;
	private ImageIcon winFlashTileIcon;
	private ImageIcon restartIcon;
	private ImageIcon undoIcon;
	private JButton restartButton;
	private JButton undoButton;
	private Font font;

	public ConnectFourView(String[] undo) throws IOException {
		this.redDiscIcon = new ImageIcon(getClass().getResource("/RedDisc.png"));
		this.blackDiscIcon = new ImageIcon(getClass().getResource("/BlackDisc.png"));
		this.defaultIcon = new ImageIcon(getClass().getResource("/DefaultGameBoardPiece.png"));
		this.winFlashTileIcon =  new ImageIcon(getClass().getResource("/WinFlashTile.png"));
		this.restartIcon = new ImageIcon(getClass().getResource("/RestartButton.png"));
		this.undoIcon = new ImageIcon(getClass().getResource("/UndoButton.png"));
		
		this.restartButton = new JButton(this.restartIcon);
		this.undoButton = new JButton(this.undoIcon);

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
	public static void main (String args[]) throws IOException {
		ConnectFourModel model = new ConnectFourModel();
		AgenteIA agenteIA = new AgenteIA(model);
		ConnectFourView view = new ConnectFourView(args);
		if (args.length != 0 && args[0].equalsIgnoreCase("undo")) {
			ConnectFourController controller = new ConnectFourController(view, model, agenteIA,true);
		}else{
			ConnectFourController controller = new ConnectFourController(view, model, agenteIA,false);
		}
	}
	public void showNotYourTurn() {
		JOptionPane.showMessageDialog(null,"Não é a sua vez!");
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public void addUndoButtonListener(ActionListener l) {
		this.undoButton.addActionListener(l);
	}
}
