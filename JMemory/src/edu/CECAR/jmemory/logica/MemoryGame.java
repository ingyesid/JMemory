/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.jmemory.logica;

import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author linkjf
 */
public class MemoryGame {

    private Player player;
    private Card[][] cards;
    private boolean gameStatus;
    //Dificulties
    public static final Card[][] EASY = new Card[3][4];
    public static final Card[][] MEDIUN = new Card[6][6];
    public static final Card[][] HARDCORE = new Card[8][8];

    public MemoryGame(Card[][] dificulty, String playerName) {

        if (dificulty.equals(EASY)) {
            Vector gameCards = generateRamdonNumbers(6, 12);
            player = new Player(playerName);
            gameStatus = true;
            cards = EASY;
            int index = 0;
            for (int i = 0; i < cards.length; i++) {
                for (int j = 0; j < cards[i].length; j++) {
                    cards[i][j] = (Card) gameCards.elementAt(index);
                    index++;
                }

            }
        } else if (dificulty.equals(MEDIUN)) {
        } else {
        }
    }

    private Vector generateRamdonNumbers(int numbersNeeded, int maxNumber) {

        Random rng = new Random(); // Ideally just create one instance globally
        Vector generatedNB = new Vector();
        int[] generated = new int[numbersNeeded];

        for (int i = 0; i < generated.length; i++) {
            generated[i] = 0;
        }

        //Vector cards = new Vector();
        for (int i = 0; i < numbersNeeded; i++) {
            while (true) {

                int next = rng.nextInt(maxNumber);

                if (next != 0) {

                    if (!contains(generated, next)) {
                        // Done for this iteration
                        generated[i] = next;

                        break;
                    }
                }
            }
        }

        int[] generated2 = generated;

        int[] ab = new int[generated.length + generated2.length];

        System.arraycopy(generated, 0, ab, 0, generated.length);
        System.arraycopy(generated2, 0, ab, generated.length, generated2.length);

        shuffleArray(ab);

        for (int i = 0; i < ab.length; i++) {
            generatedNB.addElement(new Card(String.valueOf(ab[i])));

        }
        return generatedNB;
    }

    private static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    private boolean contains(int[] vector, int number) {
        boolean isContained = false;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] != 0) {
                if (vector[i] == number) {
                    isContained = true;
                    break;
                }
            }
        }
        return isContained;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card[][] getCards() {
        return cards;
    }

    public void setCards(Card[][] cards) {
        this.cards = cards;
    }

    public boolean isGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean isComplete() {
        boolean result = false;
        for (int i = 0; i < cards.length; i++) {

            for (int j = 0; j < cards[0].length; j++) {


                if (cards[i][j].isDiscovered()) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }

        }

        return result;
    }
}
