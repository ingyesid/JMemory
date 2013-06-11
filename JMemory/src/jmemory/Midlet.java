/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmemory;

import edu.CECAR.jmemomry.logica.Card;
import edu.CECAR.jmemomry.logica.MemoryGame;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

import javax.microedition.midlet.*;

/**
 * @author linkjf
 */
public class Midlet extends MIDlet implements CommandListener{
    private Display display;
    private TextBox txtPlayerName;
    private Command commandPlay;
    public Midlet(){
    display=Display.getDisplay(this);
    txtPlayerName= new TextBox("Player Name", "", 100, TextField.ANY);
    commandPlay= new Command("Jugar",Command.OK, 0);
    txtPlayerName.addCommand(commandPlay);
    txtPlayerName.setCommandListener(this);
    }

    public void startApp() {
     display.setCurrent(txtPlayerName);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==commandPlay){
            if (txtPlayerName.getString().equals("")) {
                Alert alert= new Alert("Falta el nombre", "", null, AlertType.ERROR);
                display.setCurrent(alert,txtPlayerName);
            } else {     

                         display.setCurrent(new MemoryCanvas(new MemoryGame(MemoryGame.EASY, txtPlayerName.getString().trim())));
            }
        }
    }
    
    public Display getDisplay(){
    return display;
    }
}
