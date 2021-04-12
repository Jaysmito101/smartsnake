import commons.Constants;
import managers.GUIManager;
import managers.GameCoreManager;
import managers.GameManager;

public class Main {
    public static void main(String[] args) throws Exception{
        GameManager manager = new GameManager(args);
        GUIManager graphicsManager = new GUIManager(Constants.WINDOW_SIZE.width, Constants.WINDOW_SIZE.height);
        graphicsManager.removeGrid();
        GameCoreManager coreManager = new GameCoreManager();
        manager.attach(graphicsManager);
        manager.attach(coreManager);
        manager.startGame();
    }
}
