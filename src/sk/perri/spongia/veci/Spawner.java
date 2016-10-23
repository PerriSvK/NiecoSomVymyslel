package sk.perri.spongia.veci;

import org.newdawn.slick.Color;
import sk.perri.spongia.utils.Constants;

public class Spawner
{
    private Color color;
    private int spawnType;

    public Spawner(Color col, int spawnItemType)
    {
        this.color = col;
        this.spawnType = spawnItemType;
    }

    public int isIn(Color col1)
    {
        boolean r = color.r == (double)Math.round(col1.r*100)/100;
        boolean g = color.g == (double)Math.round(col1.g*100)/100;
        boolean b = color.b == (double)Math.round(col1.b*100)/100;
        Constants.print("Check spawners spawnerColor:", color, "getColor:", col1, "output:", r, g, b);
        return r && g && b ? spawnType : -1;
    }
}
