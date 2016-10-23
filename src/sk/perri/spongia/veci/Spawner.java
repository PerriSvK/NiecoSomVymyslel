package sk.perri.spongia.veci;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import sk.perri.spongia.game.Minihra;
import sk.perri.spongia.utils.Constants;

public class Spawner
{
    private Color color;
    private int spawnType;
    private Minihra minihra;

    public Spawner(Color col, int spawnItemType)
    {
        this.color = col;
        this.spawnType = spawnItemType;
        this.minihra = new Minihra(Minihra.HIT);
    }

    public int isIn(Color col1)
    {
        boolean r = (double) Math.round(color.r*100)/100 == (double)Math.round(col1.r*100)/100;
        boolean g = (double) Math.round(color.g*100)/100 == (double)Math.round(col1.g*100)/100;
        boolean b = (double) Math.round(color.b*100)/100 == (double)Math.round(col1.b*100)/100;
        Constants.print("Check spawners spawnerColor:", color, "getColor:", col1, "output:", r, g, b);
        return r && g && b ? spawnType : -1;
    }

    public void spawn()
    {
        minihra.minihraStart();
    }

    public void renderMinihra(Graphics g)
    {
        if(minihra.isRunning())
            minihra.draw(g);
    }

    public void updateMinihra(GameContainer c)
    {
        if(minihra.isRunning())
            minihra.update(c);
    }
}
