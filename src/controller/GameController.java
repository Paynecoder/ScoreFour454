/**
 * This class represents the Game Controller. That houses the
 * current Game model as well as a View whether it be Graphics or a GUI.
 *
 * @author Joshua Payne
 * @author Sukirat Dhillon
 * @author Swethin Panjwani
 * @author Gursevak Billing
 * @author Anupriya Shaju
 * @author Willbert Suteja
 * @version 1
 */
package controller;

import model.AI;
import model.BeadColour;
import model.Game;
import model.Move;
import model.player.AIPlayer;
import view.Viewable;

public class GameController {
  private Game game;
  private Viewable view;
  private CommandReader commandReader;
  private AI ai;

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
    this.ai = new AI(game);
  }

  /**
   * Starts a testing session by entering the commandReader loop.
   * The loop is exited by entering quit command that uses system.exit
   */
  public void startTesting() {
    view.displayMessage(
        "Welcome to Score Four 454! Game started in testing mode. Enter 'show manual.' for a list of commands.");
    while (true) {
      commandReader.readCommand(this);
    }
  }

  /**
   * Starts a 1v1 interactive game vs the AI.
   * Players are restricted to turn play until a win or draw occurs.
   * Or a quit command has been entered.
   */
  public void startInteractiveGame() {
    BeadColour chosenColour = commandReader.getInteractiveColour();
    this.game.startNewGame(chosenColour);
    view.displayMessage("Game started.");

    while (!game.checkDraw() && !game.checkWin()) {
      Move move;
      if (game.getTurn() instanceof AIPlayer) {
        move = ai.getMove();
      } else {
        move = commandReader.readInteractiveMoveCommand(this);
      }
      if (game.makeMove(move)) {
        view.displayMessage(getGame().getPrevTurn().getName() + " plays " + move.toString() + ".\n");
        view.displayMessage(game.drawBoard());
      } else {
        view.displayMessage("Invalid Move: Spike Full");
      }
    }
    checkGameStatus();
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
      } else if (input.startsWith("get white move")) {
        handleRecommendWhiteMoveCommand();
      } else if (input.startsWith("get black move")) {
        handleRecommendBlackMoveCommand();
      } else if (input.startsWith("go interactive")) {
        handleGoInteractiveCommand();
      } else {
        view.displayMessage("Invalid Command.");
      }
    } else {
      view.displayMessage("Invalid Command.");
    }
  }

  /**
   * Helper method to init a interactive game.
   */
  private void handleGoInteractiveCommand() {
    startInteractiveGame();
  }

  /**
   * Helper method to display a recommended move from the AI.
   */
  private void handleRecommendBlackMoveCommand() {
    view.displayMessage(ai.recommendBlackMove());
  }

  /**
   * Helper method to display a recommended move from the AI.
   */
  private void handleRecommendWhiteMoveCommand() {
    view.displayMessage(ai.recommendWhiteMove());
  }

  /**
   * Helper method to display a manual of commands for the user.
   */
  private void handleShowManualCommand() {
    view.displayMessage("Commands:");
    view.displayMessage("'clear.' Empties the current board");
    view.displayMessage("'quit.' Gracefully quits");
    view.displayMessage("'addBlackbeadtoA1.' adds black bead to position A1. (A-D, 1-4).");
    view.displayMessage("'addWhitebeadtoA1.' adds white bead to position A1. (A-D, 1-4).");
    view.displayMessage("'removebeadfromB3.' removes top bead from position B3. (A-D, 1-4).");
    view.displayMessage("'show board.' Products the current board in a list format.");
    view.displayMessage("'draw board.' draws a more visual representation of the current game board.");
    view.displayMessage("'get white move.' Get a recommended move from the AI subsystem for the white beads.");
    view.displayMessage("'get black move.' Get a recommended move from the AI subsystem for the black beads.");
    view.displayMessage("'go interactive.' Plays an Interactive 1v1 VS the CPU!");
  }

  /**
   * Helper method to display the board in a list format to the user.
   */
  private void handleShowBoardCommand() {
    view.displayMessage(game.showBoard());
  }

  /**
   * Helper method to display a 3d board in ascii to the user.
   */
  private void handleDrawBoardCommand() {
    view.displayMessage(game.drawBoard());
  }

  /**
   * Helper method to quit the program.
   */
  private void handleQuitCommand() {
    System.exit(0);
  }

  /**
   * Helper method to clear the board.
   */
  private void handleClearCommand() {
    game.clearBoard();
  }

  /**
   * Helper method to add a bead through the testing command.
   *
   * @param input      String of input to parse for location
   * @param beadColour Colour of bead to place.
   */
  private void handleAddBeadCommand(String input, BeadColour beadColour) {
    Move move = commandReader.parseAddBeadCommand(input, beadColour);
    if (beadColour != game.getTurn().getPlayerColour()) {
      game.switchTurn();
    }
    if (move != null && game.makeMove(move)) {
      view.displayMessage("Done.");
      checkGameStatus();
    } else {
      view.displayMessage("Impossible.");
    }
    checkGameStatus();
  }

  /**
   * Helper method to remove top bead from a specified spike.
   *
   * @param input The input to parse for the location.
   */
  private void handleRemoveBeadCommand(String input) {
    int[] coordinates = commandReader.parseRemoveBeadCommand(input);
    if (coordinates != null && game.deleteTopBead(coordinates[0], coordinates[1])) {
      view.displayMessage("Done.");
      checkGameStatus();
    } else {
      view.displayMessage("Impossible.");
    }
    checkGameStatus();
  }

  /**
   * Helper method to add a White bead.
   *
   * @param input to parse for location.
   */
  private void handleAddWhiteBeadCommand(String input) {
    handleAddBeadCommand(input, BeadColour.WHITE);
  }

  /**
   * Helper method to add a Black bead.
   *
   * @param input to parse for location.
   */
  private void handleAddBlackBeadCommand(String input) {
    handleAddBeadCommand(input, BeadColour.BLACK);
  }

  /**
   * Checks the current win or draw status of the game.
   */
  private void checkGameStatus() {
    if (game.checkWin()) {
      view.displayMessage("Game Over! " + game.getPrevTurn().getName() + " wins.");
      game.clearBoard();
    } else if (game.checkDraw()) {
      view.displayMessage("Game Over! It was a draw.");
      game.clearBoard();
    }
  }

  /**
   * @param view The view to switch to
   */
  public void switchView(Viewable view) {
    this.view = view;
  }

  /**
   * @return the current game object.
   */
  public Game getGame() {
    return this.game;
  }

}
