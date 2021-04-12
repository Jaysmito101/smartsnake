package GUIElements;

import commons.Constants;
import commons.KeyValuePair;
import managers.GameCoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.security.PolicySpi;
import java.util.ArrayList;
import java.util.Stack;

public class GraphicsPanel extends JPanel {
    private int defaultWidth, defaultHeight;
    private boolean isGrid;
    public ArrayList<KeyValuePair> commandStack;

    public GraphicsPanel(int defaultHeight, int defaultWidth){
        isGrid = true;
        commandStack = new ArrayList<KeyValuePair>();
        this.defaultHeight = defaultHeight;
        this.defaultWidth = defaultWidth;
        this.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        this.setBackground(Color.BLACK);
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void addCommand(KeyValuePair command){
        commandStack.add(command);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(151, 151, 151));
        for (int i = -1 ; i < Constants.GAME_PANEL_SIZE-1 && isGrid;  i += Constants.GAME_UNIT_SIZE){
            g.drawLine(0, i, Constants.GAME_PANEL_SIZE, i);
        }

        for (int i = -1 ; i < Constants.GAME_PANEL_SIZE-1 && isGrid;  i += Constants.GAME_UNIT_SIZE){
            g.drawLine(i, 0, i, Constants.GAME_PANEL_SIZE);
        }
        boolean isSnakeHeadDone = false;
        for(KeyValuePair item: commandStack){
            if(item.key.equals("apple")){
                g.setColor(Constants.APPLE_COLOR);
                Dimension xy = (Dimension)item.value;
                g.fillOval(xy.width, xy.height, Constants.GAME_UNIT_SIZE, Constants.GAME_UNIT_SIZE);
            }else if(item.key.equals("snakeHead") && !isSnakeHeadDone){
                isSnakeHeadDone = true;
                g.setColor(Constants.SNAKE_HEAD_COLOR);
                Dimension xy = (Dimension)item.value;
                g.fillRect(xy.width, xy.height, Constants.GAME_UNIT_SIZE, Constants.GAME_UNIT_SIZE);
            }else if (item.key.equals("snake")){
                g.setColor(Constants.SNAKE_BODY_COLOR);
                Dimension xy = (Dimension)item.value;
                g.fillRect(xy.width, xy.height, Constants.GAME_UNIT_SIZE, Constants.GAME_UNIT_SIZE);
            }else if (item.key.equals("gover")){
                try {
                    Font gover_font = Font.createFont(Font.TRUETYPE_FONT, new File("res/gover_font.ttf"));
                    //FontMetrics metrics = getFontMetrics(gover_font);
                    //Rectangle2D rect = metrics.getStringBounds("!Game Over!", g);
                    //int x = (int)rect.getX() + (int)(rect.getWidth() - metrics.stringWidth("!Game Over!")) / 2;
                    //int y = (int)rect.getY() + (int)((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                    //gover_font = new Font("Arial", Font.BOLD, 32);
                    g.setColor(Color.RED);
                    g.setFont(gover_font);
                    g.drawString("!Game Over!", 100, 300);
                }catch (Exception ex){

                }
            }
        }
    }

    public void removeApples(){
        int index = -1;        for (int i = 0; i <commandStack.size();i++){
            if(commandStack.get(i).key.equals("apple")){
                index =i;
                break;
            }
        }
        if(index==-1)return;
        commandStack.remove(index);
    }

    public void removeSnakes(){
        ArrayList<KeyValuePair> lst = new ArrayList<KeyValuePair>();
        for (int i = 0; i <commandStack.size();i++){
            if(commandStack.get(i).key.equals("snake") || commandStack.get(i).key.equals("snakeHead") ){
                lst.add(commandStack.get(i));
            }
        }
        for (KeyValuePair i : lst) {
            commandStack.remove(i);
        }
    }

    public void removeGrid(){
        isGrid = false;
    }

    public void removeAll(){
        commandStack.clear();
    }
}
