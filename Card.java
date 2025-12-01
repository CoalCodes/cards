import java.util.*;
import java.io.*;
/**
 * Kim CARDashian.
 *
 * @author (Cole Sottile)
 * @version (1/30/2020)
 */
public class Card
{
    private int point;
    private String rank;
    private String suit;
    public Card(int pts, String rnk, String sts)
    {
        point = pts;
        rank = rnk;
        suit = sts;
    }

    public int getPoints()
    {
        return point;
    }

    public String getRank()
    {
        return rank;
    }

    public String getSuit()
    {
        return suit;
    }

    public String toString()
    {
        return "["+suit+  " | " +rank+ " | " +point+ "]";
    }
}

