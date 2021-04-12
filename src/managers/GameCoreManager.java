package managers;


import commons.Constants;
import commons.Coordinate;
import commons.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameCoreManager extends KeyAdapter implements ActionListener {
    private GUIManager guiManager;
    private Timer renderTimer, gameClockTimer;

    private char direction;

    private Coordinate apple;
    private Snake snake;

    public GameCoreManager(){
        this.direction = 'R';
        this.apple = new Coordinate();
        this.snake = new Snake();
        for (int i=0,j=Constants.INITIAL_SNAKE_LENGTH-1;i<Constants.INITIAL_SNAKE_LENGTH;i++,j--) {
            this.snake.setPart(j, i, 0);
        }
        renderTimer = new Timer(Constants.CLOCK_DELAY, this);
    }

    public void moveSnake(){
        Coordinate snakeHead = snake.getHead();
        for (int i=snake.size()-1;i>1;i--){
            snake.getPart(i).x = snake.getPart(i-1).x;
            snake.getPart(i).y = snake.getPart(i-1).y;
        }
        snake.getPart(1).x = snakeHead.x;
        snake.getPart(1).y = snakeHead.y;
        switch (direction){
            case 'R':{
                snakeHead.x++;
                break;
            }
            case 'L':{
                snakeHead.x--;
                break;
            }
            case 'U':{
                snakeHead.y--;
                break;
            }
            case 'D':{
                snakeHead.y++;
                break;
            }
        }
    }

    public void generateApple(){
        apple.x = (int)(Math.random() * (Constants.GAME_PANEL_SIZE/Constants.GAME_UNIT_SIZE));
        apple.y = (int)(Math.random() * (Constants.GAME_PANEL_SIZE/Constants.GAME_UNIT_SIZE));
    }

    public void collisionDetection(){
        Coordinate snakeHead = snake.getHead();
        if(apple.x == snakeHead.x && apple.y==snakeHead.y){
            generateApple();
            addSnakePart();
            return;
        }
        if(snakeHead.x >= (Constants.GAME_PANEL_SIZE/Constants.GAME_UNIT_SIZE) || snakeHead.y >= (Constants.GAME_PANEL_SIZE/Constants.GAME_UNIT_SIZE) || snakeHead.x<0 || snakeHead.y <0){
            renderTimer.stop();
            gameOver();
        }
        for (int i=1;i<snake.size();i++){
            if(snake.getPart(i).x==snakeHead.x && snake.getPart(i).y==snakeHead.y){
                renderTimer.stop();
                gameOver();
                break;
            }
        }
    }

    private void addSnakePart() {
        snake.addPart(direction);
    }

    public void attach(GUIManager guiManager){
        this.guiManager = guiManager;
        guiManager.addKeyListenerManager(this);
    }

    public void gameOver(){
        guiManager.setGameOver();
        System.out.println("Game Over!");
    }

    public void startGame(){
        renderTimer.start();
        generateApple();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        moveSnake();
        collisionDetection();
        guiManager.setApple(apple.x, apple.y);
        guiManager.setSnake(snake);
        guiManager.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                if(this.direction!='L')
                    this.direction = 'R';
                break;
            }
            case KeyEvent.VK_LEFT:{
                if(this.direction!='R')
                    this.direction = 'L';
                break;
            }
            case KeyEvent.VK_UP:{
                if(this.direction!='D')
                    this.direction = 'U';
                break;
            }
            case KeyEvent.VK_DOWN:{
                if(this.direction!='U')
                    this.direction = 'D';
                break;
            }
        }
    }
}
