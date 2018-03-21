package Game;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Timer;

public class ConnectFourModel {
	
	/**
	 * Represents the gameBoard in which discs are placed.
	 */
	private int gameBoard[][];
	/**
	 * Represents wether or not it's the player's turn to play.
	 */
	private boolean playerTurn;
	/**
	 * Represents the disc which drops when the user clicks a column of the game board.
	 */
	private Disc droppingDisc;
	
	/**
	 * Represents the location where the user clicks.
	 */
	private Point clickPoint;
	
	/**
	 * Represents the location of the mouse cursor.
	 */
	private Point mousePoint;
	
	/**
	 * Represents the location of 4 discs which make up the Connect-Four.
	 */
	private Point connectFour[];
	
	/**
	 * Represents whether or not to begin drawing the win Sequence.
	 */
	private boolean winSequence;
	
	/**
	 * Represents timer for the dropping disc.
	 */
	private Timer timer;
	
	/**
	 * Represents the size of the disc.
	 */
	private final int DISC_SIZE = 75;
	
	/**
	 * Represents the size of the tile.
	 */
	private final int TILE_SIZE = 100;
	
	/**
	 * Represents the distance between the game board and the window.
	 */
	private final int MARGIN = 50;
	
	/**
	 * Represents the font size of the text drawn in the win sequence.
	 */
	private final int FONT_SIZE = 48;
	
	/**
	 * Represents the status of a tile in the game board. In this case: an empty tile.
	 */
	public final int EMPTY = 0;
	
	/**
	 * Represents the status of a tile in the game board. In this case: A red disc.
	 */
	public final int RED = 1;
	
	/**
	 * Represents the status of a tile in the game board. In this case: A black disc.
	 */
	public final int BLACK = 2;
	
	/**
	 * Represents the # of rows in the game board.
	 */
	private final int ROWS = 6;
	
	/**
	 * Represents the # of columns in the game board.
	 */
	private final int COLS = 7;
	
	/**
	 * Represents the speed in which the timer fires for the dropping disc.
	 */
	private final int Y_DISC_VELOCITY = 5;
	
	/**
	 * Represents the color of the current turn.
	 */
	private int currentColor = RED;
	
	/**
	 * Initializes a new instance of Game.ConnectFourModel with default values.
	 */
	public ConnectFourModel() {
		this.gameBoard = new int[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				this.gameBoard[i][j] = EMPTY;
			}
		}
		
		this.connectFour = new Point[4];
		for (int i = 0; i < 4; i++) {
			this.connectFour[i]= new Point(0,0); 
		}
		
		this.droppingDisc = new Disc(0, 0, 0, Y_DISC_VELOCITY);
		this.winSequence = false;
		this.timer = new Timer(0, null);
		this.clickPoint = new Point(0,0);
		this.mousePoint = new Point(0,0);
	}
	
	public int getRows() {
		return this.ROWS;
	}
	
	public int getCols() {
		return this.COLS;
	}
	
	public int getMargin() {
		return this.MARGIN;
	}
	public int getDiscSize() {
		return this.DISC_SIZE;
	}
	
	public int getTileSize() {
		return this.TILE_SIZE;
	}
	
	public int getFontSize() {
		return this.FONT_SIZE;
	}
	
	public int[][] getGameBoard() {
		return this.gameBoard;
	}
	
	public int getCurrentColor() {
		return this.currentColor;
	}
	
	public void setCurrentColor(int color) {
		this.currentColor = color;
	}
	
	public Disc getDroppingDisc() {
		return this.droppingDisc;
	}
	
	public Timer getTimer(){
		return this.timer;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public Point getClickPoint() {
		return this.clickPoint;
	}
	
	public Point getMousePoint() {
		return this.mousePoint;
	}
	
	public boolean getWinSequence() {
		return this.winSequence;
	}
	
	public void setWinSequence(boolean winSequence) {
		this.winSequence = winSequence;
	}
	
	public Point[] getConnectFour() {
		return this.connectFour;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public void changeTurn() {
		playerTurn = !playerTurn;
	}

	public ArrayList<Integer> getColunasJogaveis() {
		ArrayList<Integer> colunasLivres = new ArrayList<>();
		for(int i =0; i<gameBoard[0].length ;i++){
			if(gameBoard[0][i]==0){
				colunasLivres.add(i);
			}
		}
		return colunasLivres;
	}

	public int[][] simulaJogada(Integer coluna) {
		int[][] newGameBoard = new int[ROWS][COLS];
		for(int i=0; i<getGameBoard().length; i++) {
			for (int j = 0; j < getGameBoard()[i].length; j++) {
				newGameBoard[i][j] = getGameBoard()[i][j];
			}
		}
		for (int i = getRows() - 1; i >= 0; i--) {
			if (newGameBoard[i][coluna] == EMPTY) {
				newGameBoard[i][coluna] = 2;
				break;
			}
		}
		return newGameBoard;
	}
}
