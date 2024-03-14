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
   * Starts a new Game by entering the commandReader loop.
   */
  public void startTesting() {
    view.displayMessage("Game started in testing mode. Enter 'show manual.' for a list of commands.");
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
      game.makeMove(move);
      view.displayMessage(game.drawBoard());
      view.displayMessage(getGame().getPrevTurn().getName() + " plays " + move.toString() + ".\n");
    }
    checkGameStatus();
  }

  public void switchView(Viewable view) {
    this.view = view;
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
        handleRecommendWhiteMove();
      } else if (input.startsWith("get black move")) {
        handleRecommendBlackMove();
      } else if (input.startsWith("go interactive")) {
        handleGoInteractiveCommand();
      } else {
        view.displayMessage("Invalid Command.");
      }
    } else {
      view.displayMessage("Invalid Command.");
    }
  }

  private void handleGoInteractiveCommand() {
    startInteractiveGame();
  }

  private void handleRecommendBlackMove() {
    view.displayMessage(ai.recommendBlackMove());
  }

  private void handleRecommendWhiteMove() {
    view.displayMessage(ai.recommendWhiteMove());
  }

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
    handleAddBeadCommand(input, BeadColour.WHITE);
  }

  private void handleAddBlackBeadCommand(String input) {
    handleAddBeadCommand(input, BeadColour.BLACK);
  }

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

  private void checkGameStatus() {
    if (game.checkWin()) {
      view.displayMessage("Game Over! " + game.getPrevTurn().getName() + " wins.");
      game.clearBoard();
    } else if (game.checkDraw()) {
      view.displayMessage("Game Over! It was a draw.");
      game.clearBoard();
    }
  }

  public Game getGame() {
    return this.game;
  }

}
