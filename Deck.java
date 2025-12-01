import java.io.*;
import java.util.*;
/**
 * I will DECK you.
 *
 * @author (Cole Sottile)
 * @version (1/30/2020)
 */
public class Deck
{
    private ArrayList<Card> a = new ArrayList<Card>();
    private ArrayList<Card> c = new ArrayList<Card>();
    public Deck(int[]pts,String[]rnks,String[]sts)
    {
        for(int i = 0; i<sts.length; i++)
        {
            for(int j = 0; j<pts.length; j++)
            {
                Card c = new Card(pts[j],rnks[j],sts[i]);
                a.add(c);
            }
        }
    }

    public void imShufflin()
    {
        for(int i = 0; i<a.size();i++)
        {
            int j = (int)(Math.random()*a.size());
            Card k = a.get(j);
            a.remove(j);
            a.add(k);
        }
    }

    public void deal(int cards)
    {
        imShufflin();
        System.out.println("Your Hand: ");
        for(int i = 0; i<cards;i++)
        {
            System.out.print(a.get(i)+" , ");
        }
    }

    public void cardsToBust(int target, int trials)
    {
        ArrayList<Integer>b = new ArrayList<Integer>();
        int[]freq = new int[12];
        for(int i =0; i<trials;i++)
        {
            int total = 0;
            while(total<target)
            {
                int e = a.get((int)(Math.random()*a.size())).getPoints();
                b.add(e);
                total = total+e;
                if(a.size() == b.size())
                {
                    imShufflin();
                    b.clear();
                }
            }
            freq[total] = freq[total+1];
        }
        System.out.println(freq);
    }

    public void cMassDraw(int ind)
    {
        for (int i = 0; i<ind; i++)
        {
            c.add(a.get(ind));
        }
    }

    public void draw(int ind)
    {
        c.add(a.get(ind));
    }

    public void spaceTxt(String txt)
    {
        System.out.println();
        System.out.println(txt);
        System.out.println();
    }

    public void doubleSpaceTxt(String txt1,String txt2)
    {
        System.out.println();
        System.out.println(txt1);
        System.out.println();
        System.out.println(txt2);
    }

    public void houseRules(int target)
    {
        System.out.println("HOUSE RULES:");
        doubleSpaceTxt("1. All ties go to the house.","2. The house must hit on every number turn that their score is less than "+(target-5)+".");
        doubleSpaceTxt("3. Have Fun!","Target score is " +target+", Believe in the Heart of the cards!");
    }

    public void flapJack(int target, int winStreak)
    {
        String response = "";
        c.clear();
        int total = 0;
        int houseVal = 0;
        int turn = 1;
        int count = 4;
        imShufflin();
        houseRules(target);
        spaceTxt("~~~~~~~~~~ Turn " +turn+ " ~~~~~~~~~~~");
        total = total + a.get(0).getPoints() + a.get(2).getPoints();
        houseVal = houseVal + a.get(1).getPoints() + a.get(3).getPoints();
        c.add(a.get(0));
        c.add(a.get(2));
        System.out.println("Your Hand: "+ a.get(0)+" "+ a.get(2)+" ; "+ "Current Total is: " + total + ".");
        System.out.println("Hit or Pass? : HINT! Type. (PS: type 'end' to end the game at any time or 'help' to see how to play.)");
        boolean playerBust = false;
        boolean houseBust = false;
        while(!response.equals("end"))
        {
            boolean humanTurn = false;
            Scanner phil = new Scanner (System.in);
            response = phil.nextLine().toLowerCase();
            if (houseVal <= (target-5) && humanTurn == false && !response.equals("end") && (response.equals("hit") || response.equals("pass")))
            {
                houseVal = houseVal + a.get(count).getPoints();
                spaceTxt("House used HIT!");
                count++;
                humanTurn = true;
                if(houseVal > target)
                {
                    houseBust = true;
                }
            }
            else if (houseVal >= (target-4) && houseVal <= target)
            {
                spaceTxt("House used PASS!");
                humanTurn = true;
            }
            else if(houseVal > target)
            {
                houseBust = true;
                humanTurn = true;
            }
            if (response.indexOf("hit") >= 0  && humanTurn == true)
            {
                response = "";
                turn++;
                spaceTxt("~~~~~~~~~~ Turn " +turn+ " ~~~~~~~~~~~");
                System.out.println("Card pulled was a " + a.get(count));
                total = total + a.get(count).getPoints();
                draw(count);
                spaceTxt("Current Hand: "+c+" ; "+ "Current Total is: " + total + ".");
                count++;
                humanTurn = false;
                if(total>target)
                {
                    playerBust = true;
                    if(houseBust == true)
                    {
                        spaceTxt("Both parties busted! Twas a bad hand. Final Score is:  PLAYER: "+total+ " HOUSE: "+houseVal);
                        System.out.println("The house busted by: " +(houseVal-target)+" point(s)! You busted by: "+(total-target)+ " point(s)!");
                        System.out.println();
                        winStreak = 0;
                        System.out.println("Current win streak is: "+winStreak);
                    }
                    else
                    {
                        System.out.println("Final Score is:  PLAYER: "+total+ " HOUSE: "+houseVal);
                        System.out.println("You busted by "+(total-target)+ " point(s)! You Lose!");
                        System.out.println();
                        winStreak = 0;
                        System.out.println("Current win streak is: "+winStreak);
                    }
                    System.out.println();
                    System.out.println("Thank you for playing!");
                    System.out.println("Play Again? (yes or no)");
                }
                else if(total == target)
                {
                    System.out.println("You've reached the target of " +target+", it's in your best interest to pass.");
                    System.out.println("Hit? or Pass?");
                }
                else
                {
                    System.out.println("Hit? or Pass?");
                }
            }
            else if(response.indexOf("pass") >= 0) {
                spaceTxt("~~~~~~~~ FINAL RESULTS ~~~~~~~");
                System.out.println("Final Score is: PLAYER: " + total + " HOUSE: " + houseVal);
                if (total < houseVal && !houseBust && !playerBust) {
                    System.out.println("You Lose by " + (houseVal - total) + " point(s)!");
                    if (turn == 1) {
                        System.out.println("REVERSE TKO!");
                    }
                    winStreak = 0;
                    System.out.println("Current win streak is: " + winStreak);
                } else if (total == houseVal && !houseBust && !playerBust) {
                    System.out.println("Its a tie, and all ties go the house, You Lose!");
                    winStreak = 0;
                    System.out.println("Current win streak is: " + winStreak);
                } else if (houseBust == true) {
                    System.out.println("House busted by " + (houseVal - target) + " point(s)! You Win!");
                    if (turn == 1) {
                        System.out.println("TKO!");
                    }
                    winStreak++;
                    System.out.println("Current win streak is: " + winStreak);
                } else if (total > houseVal && !houseBust && !playerBust) {
                    System.out.println("You win by " + (total - houseVal) + " point(s)!");
                    winStreak++;
                    if (turn == 1) {
                        System.out.println("TKO!");
                    }
                    System.out.println("Current win streak is: " + winStreak);
                }
                System.out.println();
                System.out.println("Thank you for playing!");
                System.out.println("Play Again? (yes or no)");

            }
            if (response.indexOf("yes") >= 0) {
                this.flapJack(target, winStreak);
            } else if (response.indexOf("no") >= 0) {
                System.out.println("Thank you for playing! Closing program...");
                System.exit(0);
                response = "end";
            }
            else if(response.indexOf("help") >= 0)
            {
                System.out.println("How to play: Try and get as close if not exactly "+target+" in point value with your cards.");
                System.out.println("Hit - means you draw and additional card.");
                System.out.println("Pass - means you are fine with your current score and want to see how it plays out.");
            }
            //spaceTxt("New Super Mario Brothers for the Nintendo DS gave children severe gambling addiction. Why? the poker game with luigi.");
        }
    }

    public boolean isEmpty()
    {
        for (int k = 0; k < a.size(); k++)
        {
            if (a.get(k) != null)
            {
                return false;
            }
        }
        return true;
    }

    public String toString()
    {
        return "["+a+"]";
    }
}
