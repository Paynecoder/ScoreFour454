/**
 * The entry point for the Score Four game.
 * Creates a new Game and ConsoleView and starts the game through a GameController.
 *
 * @author 454HorsePower
 * @version 1
 */
import controller.GameController;
import model.Game;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        ConsoleView view = new ConsoleView();
        GameController controller = new GameController(game, view);
        controller.startTesting();
    }
}

/**
 * To Do:
 * Comments
 * Add where the AI played a move in interactive mode
 * Diagonal Lines in drawBoard
 * Ensure all wincheck is 100%
 */