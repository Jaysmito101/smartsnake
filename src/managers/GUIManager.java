package managers;

import GUIElements.GraphicsPanel;
import commons.Constants;
import commons.KeyValuePair;
import commons.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIManager extends JFrame {
    private GraphicsPanel panel;

    public GUIManager(int panelWidth, int panelHeight){
        panel = new GraphicsPanel(panelHeight, panelWidth);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SmartSnake -Jaysmito Mukherjee");
        this.pack();
        this.setFocusable(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void addKeyListenerManager(GameCoreManager gameCoreManager){
        super.addKeyListener(gameCoreManager);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    public void setApple(int x, int y){
        panel.removeApples();
        panel.addCommand(new KeyValuePair("apple", new Dimension(x * Constants.GAME_UNIT_SIZE, y * Constants.GAME_UNIT_SIZE)));
        panel.repaint();
    }

    public void setSnake(Snake snake){
        panel.removeSnakes();
        panel.addCommand(new KeyValuePair("snakeHead", new Dimension(snake.getPart(0).x*Constants.GAME_UNIT_SIZE, snake.getPart(0).y*Constants.GAME_UNIT_SIZE)));
        for(int i=1;i<snake.size();i++){
            panel.addCommand(new KeyValuePair("snake", new Dimension(snake.getPart(i).x*Constants.GAME_UNIT_SIZE, snake.getPart(i).y*Constants.GAME_UNIT_SIZE)));
        }
    }

    public void setGameOver(){
        removeAllCommands();
        panel.addCommand(new KeyValuePair("gover", null));
    }

    public void removeGrid(){
        panel.removeGrid();
    }

    public void removeAllCommands(){
        panel.removeAll();
    }
}
