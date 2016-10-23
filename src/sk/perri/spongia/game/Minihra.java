package sk.perri.spongia.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import sk.perri.spongia.utils.Constants;

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
    private Circle c;
    private Line l;
    private boolean running = false;


    public Minihra(int typ)
    {
        this.typ = typ;
    }

    public void minihraStart()
    {
        running = true;
        c = new Circle(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2, 100);
        l = new Line(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2, Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2-120);
    }

    public void draw(Graphics g)
    {
        g.drawString("Stlac: WASD", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2);
    }

    public boolean isRunning()
    {
        return running;
    }

    public int update(GameContainer gc)
    {
        return -1;
    }
}
