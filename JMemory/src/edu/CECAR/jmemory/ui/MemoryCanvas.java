/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.jmemory.ui;

import edu.CECAR.jmemory.logica.Card;
import edu.CECAR.jmemory.logica.MemoryGame;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author ingyesid
 */
public class MemoryCanvas extends Canvas implements Runnable {
    
    private MemoryGame game;
    private int color = 255;
    private int WIDTH;
    private int HEIGHT;
    private boolean comodin = true;
    private Card currentCard;
    private CountDownTask countDownTask;
    private Timer timer;
private MemoryMidlet memoryMidlet;
    public MemoryCanvas(MemoryGame memoryGame,MemoryMidlet memoryMidlet) {
        game = memoryGame;
        this.WIDTH = getWidth();
        this.HEIGHT = getHeight();
        this.memoryMidlet=memoryMidlet;
        Thread thread = new Thread(this);
        thread.start();
        
    }
    
    protected void paint(Graphics graphics) {
        graphics.setColor(color, 255, 255);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(0, 0, 0);
        graphics.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        graphics.drawString("Player:" + game.getPlayer().getName(), 12, 0, Graphics.TOP | Graphics.LEFT);
        
        if (comodin) {
            graphics.setColor(250, 0, 0);
            for (int i = 0; i < game.getCards().length; i++) {
                
                for (int j = 0; j < game.getCards()[0].length; j++) {
                    
                    graphics.drawRect(i * 60, j * 60 + 60, 60, 60);
                    
                    if (game.getCards()[i][j].isDiscovered() == true || game.getCards()[i][j].isActive()) {
                        
                        graphics.drawString(game.getCards()[i][j].getName(), 30 + 60 * i, 90 + 60 * j, Graphics.BASELINE | Graphics.HCENTER);
                    } else {
                        graphics.drawString("?", 30 + 60 * i, 90 + 60 * j, Graphics.BASELINE | Graphics.HCENTER);
                        
                    }
                    
                }
                
            }
            
        }
    }
    
    protected void keyPressed(int keyCode) {
        Card selectedCard = null;
        switch (keyCode) {
            case KEY_NUM1:
                selectedCard = game.getCards()[0][0];
                break;
            case KEY_NUM2:
                selectedCard = game.getCards()[1][0];
                break;
            case KEY_NUM3:
                selectedCard = game.getCards()[2][0];
                break;
            case KEY_NUM4:
                selectedCard = game.getCards()[0][1];
                break;
            case KEY_NUM5:
                selectedCard = game.getCards()[1][1];
                break;
            case KEY_NUM6:
                selectedCard = game.getCards()[2][1];
                break;
            case KEY_NUM7:
                selectedCard = game.getCards()[0][2];
                break;
            case KEY_NUM8:
                selectedCard = game.getCards()[1][2];
                break;
            case KEY_NUM9:
                selectedCard = game.getCards()[2][2];
                break;
            case KEY_NUM0:
                selectedCard = game.getCards()[1][3];
                break;
            case KEY_STAR:
                selectedCard = game.getCards()[0][3];
                break;
            case KEY_POUND:
                selectedCard = game.getCards()[2][3];
                break;
        }
        
        if (currentCard != null) {
            
            if (currentCard != selectedCard) {
                if (selectedCard.getName().equalsIgnoreCase(currentCard.getName())) {
                    
                    selectedCard.setStatus(true);
                    selectedCard.setDiscovered(true);
                    currentCard.setDiscovered(true);
                    
                } else {
                    
                    currentCard.setStatus(false);
                    selectedCard.setStatus(false);
                }
                currentCard = null;
                
            }
        } else {
            currentCard = selectedCard;
            selectedCard.setStatus(true);
        }
        repaint();
    }
    
    public void run() {
        timer = new Timer();
        countDownTask = new CountDownTask();
        timer.schedule(countDownTask, 1000, 1000);
        repaint();
        
    }
    
    private class CountDownTask extends TimerTask {

        int val = 5000;

        public void run() {
            if (val > 0) {
                val -= 1000;
                System.out.println("Value : " + (val - 1000));
                if (game.isComplete()) {
                   
                    memoryMidlet.destroyApp(true);
                    System.out.println("Ganoooo!!");
                    cancel();
                }
            } else {
               
                System.out.println("Perdio!!");
                cancel();
            }
        }
    }
}
