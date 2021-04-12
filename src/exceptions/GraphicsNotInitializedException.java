package exceptions;

public class GraphicsNotInitializedException extends Exception{
    public GraphicsNotInitializedException(String instanceId){
        super("Graphics has not been initialized for GameManager instance : " + instanceId);
    }
}
