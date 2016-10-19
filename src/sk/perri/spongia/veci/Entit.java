package sk.perri.spongia.veci;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;

public class Entit
{
    public static final int VEC = 0;
    public static final int CLOVEK = 1;
    public static final int STAVBA = 2;

    private double x, y;
    private int typ;
    private boolean visible;
    private boolean pickable;
    private Image texture;
    private float scale;

    public Entit(int typ, int x, int y)
    {
        this.typ = typ;
        this.x = x;
        this.y = y;
    }

    public Entit(int typ, int x, int y, String texturePath)
    {
        this.typ = typ;
        this.x = x;
        this.y = y;
        try
        {
            texture = new Image(texturePath);
        }
        catch (SlickException e)
        {
            System.err.println("Could not load texture: "+texturePath+" "+e.toString());
        }

        if(typ == CLOVEK)
        {
            scale = Constants.SCALE_PAN;
        }
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void render(Camera cam)
    {
        if(texture != null)
        {
            texture.draw(Math.round(x-cam.getX()), Math.round(y-cam.getY()), scale);
        }
    }

    public void setPos(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Image getTexture()
    {
        return texture;
    }

}
