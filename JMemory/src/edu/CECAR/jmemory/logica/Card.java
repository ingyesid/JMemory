/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.jmemory.logica;

/**
 *
 * @author linkjf
 */
public class Card {

    private String name;
    private String img;
    private boolean status;
    private boolean discovered;

    public Card(String name, String img) {
        this.name = name;
        this.img = img;
        status = false;
        discovered=false;
    }

    public Card(String name) {
        this.name = name;
        this.img = "";
        status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean state) {
        this.status = state;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }
    
    
}
