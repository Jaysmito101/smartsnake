package exceptions;

public class CoreNotInitializedException extends Exception{
    public CoreNotInitializedException(String instanceId){
        super("Game Core has not been initialized for GameManager instance : " + instanceId);
    }
}