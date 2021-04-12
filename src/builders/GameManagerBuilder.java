package builders;

import managers.GameManager;

public class GameManagerBuilder {
    public static GameManager args(String[] arguments){
        return new GameManager(arguments);
    }

}
