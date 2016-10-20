package sk.perri.spongia.veci;

import sk.perri.spongia.utils.Constants;

import java.util.Collection;

public class Spawn
{
    private int typ, LUX, LUY, RDX, RDY, timeSpawn, time;
    private String texture;

    public Spawn(int typ, int LUX, int LUY, int RDX, int RDY, int spawnTime)
    {
        this.typ = typ;
        this.LUX = LUX;
        this.LUY = LUY;
        this.RDX = RDX;
        this.RDY = RDY;
        timeSpawn = spawnTime;
        time = 0;

        switch (typ)
        {
            case Vec.DREVO: texture = "drevo.png"; break;
            case Vec.ZELEZO: texture = "zelezo.png"; break;
        }
    }

    public Vec update(long delta)
    {
        time += delta;
        if(time >= timeSpawn)
        {
            time = 0;
            int posunX = (int) Math.round((LUX-RDX)*Math.random());
            int posunY = (int) Math.round((LUY-RDY)*Math.random());
            Vec v = new Vec(LUX+posunX, LUY+posunY, typ, texture);
            Constants.print("Spawn, spawning:", v.getTyp());
            return v;
        }

        return null;
    }
}
