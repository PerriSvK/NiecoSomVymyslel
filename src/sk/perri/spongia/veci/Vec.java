package sk.perri.spongia.veci;

import sk.perri.spongia.utils.Constants;

public class Vec extends Entit
{
    public static final int DREVO = 0;
    public static final int ZELEZO = 1;
    public static final int BETON = 2;
    public static final int PALIVO = 3;
    public static final int CLOVEK = 4;
    public static final int VODA = 5;

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
