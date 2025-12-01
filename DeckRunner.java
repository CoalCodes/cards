import java.util.*;
import java.io.*;
/**
 * Creating a deck...actually that's not obvious.
 * @author (Cole Sottile)
 * @version (1/30/2020)
 */
public class DeckRunner
{
    public static void main(String[]args)
    {
        String[]suit = {"Heart","Diamond","Spade","Club",};
        int[]points = {1,2,3,4,5,6,7,8,9,10,10,10,10};
        String[]ranks = {"Ace",":",":.","::",":.:",":::",":::.","::::", "::::.", ":::::", "Jack", "Queen", "King"};
        Deck deck = new Deck(points,ranks,suit);
        deck.flapJack(21, 0);
        //deck.cardsToBust(21,100);
        //System.out.println(deck);
    }
}