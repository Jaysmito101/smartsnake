package commons;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Coordinate> parts;

    public Snake(){
        parts = new ArrayList<Coordinate>(Constants.INITIAL_SNAKE_LENGTH);
    }

    public void resetSnake(){
        parts.clear();
        parts = null;
        parts = new ArrayList<Coordinate>(Constants.INITIAL_SNAKE_LENGTH);
    }

    public Coordinate getPart(int index){
        return parts.get(index);
    }

    public void setPart(int index, int x, int y){
        while(parts.size() <= index)
            parts.add(new Coordinate());
        parts.set(index, new Coordinate(x, y));
    }

    public int size(){
        return parts.size();
    }

    public Coordinate getHead(){
        return parts.get(0);
    }

    public void addPart(char direction) {
        Coordinate part = new Coordinate(parts.get(parts.size()-1));
        switch (direction){
            case 'R':{
                part.x--;
                break;
            }
            case 'L':{
                part.x++;
                break;
            }
            case 'U':{
                part.y++;
                break;
            }
            case 'D':{
                part.y--;
                break;
            }
        }
        parts.add(part);
    }
}
