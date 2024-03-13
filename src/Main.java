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
        controller.startGame();
    }
}

/**
 * TO DO:
 * Cleanup GameController class -> seperate CommandReader to the view and try to move all game logic to to the Game class.
 * ^^ -> Try to make more generic methods that can be used for both gui/console when moving to commandReader
 * 'go gui' -> Start making gui components
 * Better Draw Board Testing Method
 * Fix win check for more complex lines.
*/
