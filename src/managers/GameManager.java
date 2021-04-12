package managers;

import commons.Generators;
import exceptions.CoreNotInitializedException;
import exceptions.GraphicsNotInitializedException;

import java.util.Properties;

public class GameManager {

    //private members
    private String[] arguments;
    private GUIManager guiManager;
    private AudioManager audioManager;
    private AIManager aiManager;
    private FileManager fileManager;
    private GameCoreManager gameCoreManager;
    private PropertiesManager propertiesManager;

    //public members
    public String instanceId;

    public GameManager(String arguments[]) {
        this.instanceId = Generators.generateInstanceId();
        this.arguments = new String[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
    }

    public void startGame() throws Exception{
        if(this.guiManager == null)
            throw new GraphicsNotInitializedException(this.instanceId);
        if (this.gameCoreManager == null)
            throw new CoreNotInitializedException(this.instanceId);
        guiManager.setVisible(true);
        gameCoreManager.startGame();
    }


    public void attach(GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    public void attach(AIManager aiManager) {
        this.aiManager = aiManager;
    }

    public void attach(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public void attach(GameCoreManager gameCoreManager) throws Exception{
        if(this.guiManager == null)
            throw new GraphicsNotInitializedException(this.instanceId);
        this.gameCoreManager = gameCoreManager;
        this.gameCoreManager.attach(this.guiManager);
    }

    public void attach(PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    public void attach(FileManager fileManager) {
        this.fileManager = fileManager;
    }


}
