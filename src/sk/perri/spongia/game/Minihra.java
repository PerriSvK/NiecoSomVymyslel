package sk.perri.spongia.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Vector;

public class Minihra
{
    public static final int SPAM = 1;
    public static final int HIT = 2;
    public static final int PUSH = 3;

    private int typ;
    private Vector<Integer> key = new Vector<>();
    private Vector<Integer> keys = new Vector<>();
    private int spamCount = 7;

    public Minihra(int typ)
    {
        this.typ = typ;
    }

    public void minihraStart(GameContainer c, Graphics g)
    {
        if(typ == SPAM)
        {

        }
    }
}
