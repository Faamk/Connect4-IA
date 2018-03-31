package game;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.*;

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
    private game.Disc droppingDisc;

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

    private int[] lastPlays = {0,0};

    /**
     * Initializes a new instance of game.ConnectFourModel with default values.
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
            this.connectFour[i] = new Point(0, 0);
        }

        this.droppingDisc = new Disc(0, 0, 0, Y_DISC_VELOCITY);
        this.winSequence = false;
        this.timer = new Timer(0, null);
        this.clickPoint = new Point(0, 0);
        this.mousePoint = new Point(0, 0);
        perguntaPrimeiro();

    }


    public void perguntaPrimeiro() {
        boolean firstToPlay = JOptionPane.showConfirmDialog(null,"Você gostaria de ser o primeiro a jogar?","Seleção de jogador inicial",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
        setPlayerTurn(firstToPlay);

    }
    public ConnectFourModel(int[][] board) {
        this.gameBoard =board;
        this.connectFour = new Point[4];
        for (int i = 0; i < 4; i++) {
            this.connectFour[i] = new Point(0, 0);
        }

        this.droppingDisc = new Disc(0, 0, 0, Y_DISC_VELOCITY);
        this.winSequence = false;
        this.timer = new Timer(0, null);
        this.clickPoint = new Point(0, 0);
        this.mousePoint = new Point(0, 0);
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

    public Timer getTimer() {
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

    public ArrayList<Integer> getColunasJogaveis(int[][] newBoard) {
        ArrayList<Integer> colunasLivres = new ArrayList<>();
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (newBoard[0][i] == 0) {
                colunasLivres.add(i);
            }
        }
        return colunasLivres;
    }

    public ArrayList<Integer> getColunasJogaveis() {
        ArrayList<Integer> colunasLivres = new ArrayList<>();
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (gameBoard[0][i] == 0) {
                colunasLivres.add(i);
            }
        }
        return colunasLivres;
    }


    public boolean tabuleiroCheio() {
        for (int i = 0; i < getGameBoard().length; i++) {
            for (int j = 0; j < getGameBoard()[i].length; j++) {
                if (getGameBoard()[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] repeteTabuleiro(){
        int[][] newGameBoard = new int[ROWS][COLS];
        for (int i = 0; i < getGameBoard().length; i++) {
            for (int j = 0; j < getGameBoard()[i].length; j++) {
                newGameBoard[i][j] = getGameBoard()[i][j];
            }
        }
        return newGameBoard;
    }

    public ConnectFourModel simulaJogada(Integer coluna, Integer numPlayer) {
        int[][] newGameBoard = new int[ROWS][COLS];
        for (int i = 0; i < getGameBoard().length; i++) {
            for (int j = 0; j < getGameBoard()[i].length; j++) {
                newGameBoard[i][j] = getGameBoard()[i][j];
            }
        }
        for (int i = getRows() - 1; i >= 0; i--) {
            if (newGameBoard[i][coluna] == EMPTY) {
                newGameBoard[i][coluna] = numPlayer;
                break;
            }
        }
        return new ConnectFourModel(newGameBoard);
    }

    public int checkHorizontalPieces(int playerNumber, int[][] newTabuleiro) {
        int max = 0;
        for (int row = getRows() - 1; row >= 0; row--) {
            for (int col = 0; col < getCols() - 3; col++) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = col + 1; i < col + 3; i++) {
                        if (startPoint == newTabuleiro[row][i]) {
                            tempMax++;
                        } else if (newTabuleiro[row][i] != 0) {
                            tempMax = -1;
                        }
                    }

                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
            for (int col = getCols() - 1; col >= 3; col--) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = col - 1; i > col - 3; i--) {
                        if (startPoint == newTabuleiro[row][i]) {
                            tempMax++;
                        } else if (newTabuleiro[row][i] != 0) {
                            tempMax = -1;
                        }
                    }

                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
        }
        return max;
    }


    public int checkVerticalPieces(int playerNumber, int[][] newTabuleiro) {
        int max = 0;
        for (int col = getCols() - 1; col >= 0; col--) {
            for (int row = 0; row < getRows() - 3; row++) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = row + 1; i < row + 3; i++) {
                        if (startPoint == newTabuleiro[i][col]) {
                            tempMax++;
                        } else if (newTabuleiro[i][col] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
            for (int row = getRows(); row < 3; row--) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = row - 1; i > row - 3; i--) {
                        if (startPoint == newTabuleiro[i][col]) {
                            tempMax++;
                        } else if (newTabuleiro[i][col] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }

        }
        return max;
    }

    public int checkDiagonalBLTRPieces(int playerNumber, int[][] newTabuleiro) {
        //Check diagonal win from bottom left to top right
        int max = 0;
        for (int row = getRows() - 1; row >= 3; row--) {
            for (int col = 0; col < getCols() - 3; col++) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = 1; i < 4; i++) {
                        if (startPoint == newTabuleiro[row - i][col + i]) {
                            tempMax++;
                        } else if (newTabuleiro[row - i][col + i] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
        }
        for (int row = 0; row > getRows()-3; row++) {
            for (int col = getCols()-1; col > getCols() - 5; col--) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = 1; i < 4; i++) {
                        if (startPoint == newTabuleiro[row + i][col - i]) {
                            tempMax++;
                        } else if (newTabuleiro[row + i][col - i] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
        }

        return max;
    }

    public int checkDiagonalBRTLPieces(int playerNumber, int[][] newTabuleiro) {
        //Check diagonal win from bottom left to top right
        int max = 0;
        for (int row = getRows() - 1; row >= 3; row--) {
            for (int col = 3; col < getCols(); col++) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = 1; i < 4; i++) {
                        if (startPoint == newTabuleiro[row - i][col - i]) {
                            tempMax++;
                        } else if (newTabuleiro[row - i][col - i] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
        }
        for (int row = 0; row < getRows()-3; row++) {
            for (int col = 0; col < getCols()-3; col++) {
                int tempMax = 0;
                if (newTabuleiro[row][col] == playerNumber) {
                    tempMax++;
                    int startPoint = newTabuleiro[row][col];
                    for (int i = 1; i < 4; i++) {
                        if (startPoint == newTabuleiro[row + i][col + i]) {
                            tempMax++;
                        } else if (newTabuleiro[row + i][col + i] != 0) {
                            tempMax = -1;
                        }
                    }
                    if (max < tempMax) {
                        max = tempMax;
                    }
                }
            }
        }
        return max;
    }

    public int checkWin() {
        int start = 0;
        //Check horizontal win
        for (int row = getRows() - 1; row >= 0; row--) {
            for (int col = 0; col < getCols() - 3; col++) {
                start = getGameBoard()[row][col];
                if (start != EMPTY
                        && start == getGameBoard()[row][col + 1]
                        && start == getGameBoard()[row][col + 2]
                        && start == getGameBoard()[row][col + 3]) {
                    for (int i = 0; i < 4; i++) {
                        getConnectFour()[i] = new Point(row, col + i);
                    }
                    return start;
                }
            }
        }

        //Check vertical win
        for (int row = getRows() - 1; row >= 3; row--) {
            for (int col = 0; col < getCols(); col++) {
                start = getGameBoard()[row][col];
                if (start != EMPTY
                        && start == getGameBoard()[row - 1][col]
                        && start == getGameBoard()[row - 2][col]
                        && start == getGameBoard()[row - 3][col]) {
                    for (int i = 0; i < 4; i++) {
                        getConnectFour()[i] = new Point(row - i, col);
                    }
                    return start;
                }
            }
        }

        //Check diagonal win from bottom left to top right
        for (int row = getRows() - 1; row >= 3; row--) {
            for (int col = 0; col < getCols() - 3; col++) {
                start = getGameBoard()[row][col];
                if (start != EMPTY
                        && start == getGameBoard()[row - 1][col + 1]
                        && start == getGameBoard()[row - 2][col + 2]
                        && start == getGameBoard()[row - 3][col + 3]) {
                    for (int i = 0; i < 4; i++) {
                        getConnectFour()[i] = new Point(row - i, col + i);
                    }
                    return start;
                }
            }
        }

        //Check diagonal win from bottom right to top left
        for (int row = getRows() - 1; row >= 3; row--) {
            for (int col = getCols() - 1; col >= 3; col--) {
                start = getGameBoard()[row][col];
                if (start != EMPTY
                        && start == getGameBoard()[row-1][col-1]
                        && start == getGameBoard()[row-2][col-2]
                        && start == getGameBoard()[row-3][col-3]) {
                    for (int i = 0; i < 4; i++) {
                        getConnectFour()[i] = new Point(row - i, col - i);
                    }
                    return start;
                }
            }
        }

        if(tabuleiroCheio()){
            return -1;
        }
        return 0;
    }

    public void removeLastJogadas(){
        int i = 0;
        int col = lastPlays[1];
        while(i<ROWS){
            if(gameBoard[i][col]!=0){
                gameBoard[i][col]=0;
                break;
            }
            i++;
        }
        i=0;
        col = lastPlays[0];
        while(i<ROWS){
            if(gameBoard[i][col]!=0){
                gameBoard[i][col]=0;
                break;
            }
            i++;
        }
    }

    public void addLastJogada(int x) {
        lastPlays[0]=lastPlays[1];
        lastPlays[1]=x;
    }
}
