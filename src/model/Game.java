/**
 * This class represents a game of Score Four.
 * Bringing all elements of the model together.
 *
 * @author Joshua Payne,
 * @author Gursevak Billing
 * @author Willbert Suteja
 * @version 1
 */
package model;

import model.player.AIPlayer;
import model.player.HumanPlayer;
import model.player.Playable;

public class Game {
  private Board board;
  private Playable player1;
  private Playable player2;
  private Playable currPlayer;

  /**
   * Constructs a new Game Object with a Human, AI Player, and Empty Board.
   * Sets the Current player to the human.
   */
  public Game() {
    board = new Board();
    player1 = new HumanPlayer(BeadColour.WHITE, "Human");
    player2 = new AIPlayer(BeadColour.BLACK, "AI");
    currPlayer = player1;
  }

  /**
   * Starts a new interactive game, clears the board and sets the Players based
   * off the users specified colour.
   * @param chosenColour The colour of the human player.
   */
  public void startNewGame(BeadColour chosenColour) {
    this.board.clearBoard();

    if (chosenColour == BeadColour.WHITE) {
      player1 = new HumanPlayer(BeadColour.WHITE, "Human");
      player2 = new AIPlayer(BeadColour.BLACK, "AI");
      currPlayer = player1;
    } else {
      player1 = new AIPlayer(BeadColour.WHITE, "AI");
      player2 = new HumanPlayer(BeadColour.BLACK, "Human");
      currPlayer = player1;
    }

  }

  /**
   * Checks if the spike at a specificed location is full.
   * @param row coordinate of spike to check.
   * @param col coordinate of spike to be checked.
   * @return True if the spike is full, false otherwise.
   */
  public boolean isSpikeFullAt(int row, int col) {
    return board.isSpikeFullAt(row, col);
  }

  /**
   * Checks if the current board has a winning line on it.
   * @return True if a winning line is found, false otherwise.
   */
  public boolean checkWin() {
    return board.checkWin();
  }

  /**
   * Checks if the current board has a draw by calling checkFull method
   * @return True if the board is full, false otherwise.
   */
  public boolean checkDraw() {
    return board.isFull();
  }

  /**
   * Attempts to place a bead on the board if the specified Spike is not full.
   *
   * @param row Row coordinate of specified spike
   * @param col Column coordinate of specified spike
   * @return True if the bead was placed, False otherwise.
   */
  public boolean makeMove(int row, int col) {
    boolean success = board.addBead(row, col, currPlayer.getPlayerColour());
    if (success) {
      switchTurn();
    }
    return success;
  }

  /**
   * Attempts to make the specified move
   *
   * @param move data of move
   * @return True if the bead was placed, False otherwise.
   */
  public boolean makeMove(Move move) {
    boolean success = board.addBead(move.getRow(), move.getCol(), currPlayer.getPlayerColour());
    if (success) {
      switchTurn();
    }
    return success;
  }

  /**
   * Attempts to remove the top most bead from specified spike.
   *
   * @param row Row coordinate of specified spike
   * @param col Column coordinate of specified spike.
   * @return True if a bead was removed, false otherwise.
   */
  public boolean deleteTopBead(int row, int col) {
    return board.removeTopBeadFrom(row, col);
  }

  /**
   * @return The Game board in neat ASCII format.
   */
  public String showBoard() {
    return board.showBoard();
  }

  /**
   * @return The game board in enahnced ASCII format.
   */
  public String drawBoard() {
    return board.drawBoard();
  }

  /**
   * @return The Current Player.
   */
  public Playable getTurn() {
    return currPlayer;
  }

  /**
   * Switches turn to the opposite player of current.
   */
  public void switchTurn() {
    currPlayer = (currPlayer == player1) ? player2 : player1;
  }

  /**
   * @return The game board object
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Clears the game board and resets the turn to the Human player.
   */
  public void clearBoard() {
    board.clearBoard();
    currPlayer = player1;
  }

  /**
   * @return The player of the previous turn.
   */
  public Playable getPrevTurn() {
    return (getTurn() == player1) ? player2 : player1;
  }
}