package commons;

public class Coordinate {
    public int x, y;
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Coordinate(){
        this.x=-1;
        this.y=-1;
    }

    public Coordinate(Coordinate coordinate){
        this.x=coordinate.x;
        this.y=coordinate.y;
    }
}
