/**
 * This class represents the Game Controller. That houses the
 * current Game model as well as a View whether it be Graphics or a GUI.
 *
 * @author Joshua Payne,
 *         Student Number: 230152032
 * @version 1
 */
package controller;

import model.BeadColour;
import model.Game;
import view.Viewable;

public class GameController {
  private Game game;
  private Viewable view;
  private CommandReader commandReader;

  /**
   * Returns a new Game controller object with the specified Game model
   * and View.
   *
   * @param game The Game to control.
   * @param view How the game is displayed to the end user.
   */
  public GameController(Game game, Viewable view) {
    this.game = game;
    this.view = view;
    this.commandReader = new CommandReader();
  }

  /**
   * Starts a new Game by entering the commandReader loop.
   */
  public void startGame() {
    view.displayMessage("Game started. Enter 'show manual' for a list of commands.");
    while (true) {
      commandReader.readCommand(this);
    }
  }

  /**
   * Performs a command if it is valid, Prints an error message otherwise.
   *
   * @param input Console command to be handled
   */
  public void handleConsoleInput(String input) {
    if (input.endsWith(".")) {
      if (input.startsWith("show board")) {
        handleShowBoardCommand();
      } else if (input.startsWith("quit")) {
        handleQuitCommand();
      } else if (input.startsWith("clear")) {
        handleClearCommand();
      } else if (input.startsWith("addWhitebeadto")) {
        handleAddWhiteBeadCommand(input);
      } else if (input.startsWith("addBlackbeadto")) {
        handleAddBlackBeadCommand(input);
      } else if (input.startsWith("removebeadfrom")) {
        handleRemoveBeadCommand(input);
      } else if (input.startsWith("draw board")) {
        handleDrawBoardCommand();
      } else if (input.startsWith("show manual")) {
        handleShowManualCommand();
      } else {
        view.displayMessage("Invalid Command.");
      }
    } else {
      view.displayMessage("Invalid Command.");
    }
  }

  private void handleShowManualCommand() {
    view.displayMessage("Commands:");
    view.displayMessage("'clear.' Empties the current board");
    view.displayMessage("'quit.' Gracefully quits");
    view.displayMessage("'addBlackbeadtoA1.' adds black bead to position A1. (A-D, 1-4).");
    view.displayMessage("'addWhitebeadtoA1.' adds white bead to position A1. (A-D, 1-4).");
    view.displayMessage("'removebeadfromB3.' removes top bead from position B3. (A-D, 1-4).");
    view.displayMessage("'show board.' Products the current board in a list format.");
    view.displayMessage("''draw board.' draws a more visual representation of the current game board.");
    // List Interactive, Gui, AI commands
  }

  private void handleShowBoardCommand() {
    view.displayMessage(game.showBoard());
  }

  private void handleDrawBoardCommand() {
    view.displayMessage(game.drawBoard());
  }

  private void handleQuitCommand() {
    System.exit(0);
  }

  private void handleClearCommand() {
    game.clearBoard();
  }

  private void handleAddWhiteBeadCommand(String input) {
    String location = input.substring(14, input.length() - 1);
    char rowChar = location.charAt(0);
    int row = rowChar - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    if (game.getTurn().getPlayerColour() == BeadColour.BLACK) {
      game.switchTurn();
    }
    boolean success = game.makeMove(row, col);
    if (success) {
      view.displayMessage("Done.");
      if (game.checkWin()) {
        view.displayMessage(game.drawBoard());
        view.displayMessage(game.getPrevTurn().getName() + " wins!");
        game.clearBoard();
        view.displayMessage("New Game Created: Humans Turn ->");
        return;
      } else if (game.checkDraw()) {
        view.displayMessage(game.drawBoard());
        view.displayMessage("It's a draw!");
        game.clearBoard();
        view.displayMessage("New Game Created: Humans Turn ->");
        return;
      }
    } else {
      view.displayMessage("Impossible");
    }
  }

  private void handleAddBlackBeadCommand(String input) {
    String location = input.substring(14, input.length() - 1);
    char rowChar = location.charAt(0);
    int row = rowChar - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    if (game.getTurn().getPlayerColour() == BeadColour.WHITE) {
      game.switchTurn();
    }
    boolean success = game.makeMove(row, col);
    if (success) {
      view.displayMessage("Done.");
      if (game.checkWin()) {
        view.displayMessage(game.drawBoard());
        view.displayMessage(game.getPrevTurn().getName() + " wins!");
        game.clearBoard();
        view.displayMessage("New Game Created: Humans Turn ->");
        return;
      } else if (game.checkDraw()) {
        view.displayMessage(game.drawBoard());
        view.displayMessage("It's a draw!");
        game.clearBoard();
        view.displayMessage("New Game Created: Humans Turn ->");
        return;
      }
    } else {
      view.displayMessage("Impossible");
    }
  }

  private void handleRemoveBeadCommand(String input) {
    String location = input.substring(14, input.length() - 1);
    char rowChar = location.charAt(0);
    int row = rowChar - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    boolean success = game.deleteTopBead(row, col);
    if (success) {
      view.displayMessage("Done.");
    } else {
      view.displayMessage("Impossible");
    }
  }

}
