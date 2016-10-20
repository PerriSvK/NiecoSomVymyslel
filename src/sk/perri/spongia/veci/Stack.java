package sk.perri.spongia.veci;

import com.sun.istack.internal.NotNull;

public class Stack
{
    public int typ = -1;
    public int pocet = 0;

    public Stack(int typ)
    {
        this.typ = typ;
    }

    public Stack(int typ, int pocet)
    {
        this.typ = typ;
        this.pocet = pocet;
    }

    public void add(int kolko)
    {
        this.pocet += kolko;
    }

    public int remove(int kolko)
    {
        int ret = kolko;

        if(this.pocet < kolko)
            ret = this.pocet;

        this.pocet -= ret;
        return ret;
    }

    public void clear()
    {
        this.pocet = 0;
        this.typ = -1;
    }

    public static String typToString(@NotNull int typ)
    {
        switch (typ)
        {
            case Vec.DREVO: return "DREVO";
            case Vec.ZELEZO: return "ZELEZO";
            default: return "NEZNAME";
        }
    }
}
