package sk.perri.spongia.veci;

import sk.perri.spongia.utils.Constants;

public class Vec extends Entit
{
    public static final int DREVO = 0;
    public static final int ZELEZO = 1;

    private int typ;

    public Vec(int x, int y, int typ, String texturePath)
    {
        super(Entit.VEC, x, y, Constants.ASSETS_PATH+texturePath);
        setVisibility(true);
        this.typ = typ;
    }

    public int getTyp()
    {
        return typ;
    }
}
