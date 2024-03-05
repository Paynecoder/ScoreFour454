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
 * handleGoInteractiveCommand method -> asks the user for a colour -> plays turn style game with AI with a shorter and easier command set.
 * Enter GUI Mode -> Enter by command -> 'go gui'
 * Interactive Play -> AI Will instantly respond with a countermove and player can choose colour. -> Make Playable in Console? 'go interactive'
 * Better Draw Board Testing Method
 * Make a list of all previous moves -> To be displayed in GUI.
 * Fix win check for more complex lines.
*/
