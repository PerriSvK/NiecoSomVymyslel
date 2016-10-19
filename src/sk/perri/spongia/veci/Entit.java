package sk.perri.spongia.veci;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.InternalTextureLoader;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;

import java.util.Vector;

public class Entit
{
    public static final int VEC = 0;
    public static final int CLOVEK = 1;
    public static final int STAVBA = 2;

    private double x, y;
    private int typ;
    private boolean visible;
    private boolean pickable;
    private boolean multi = false;
    private int noTexture = 0;
    private Image texture;
    private Vector<Image> textures = new Vector<>();
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

    public Entit(int typ, int x, int y, String texturePath, int count)
    {
        this.typ = typ;
        this.x = x;
        this.y = y;
        for(int i = 0; i < count; i++)
        {
            String path = texturePath+Integer.toString(i)+".png";
            try
            {
                textures.add(new Image(path));
            } catch (SlickException e) {
                System.err.println("Could not load texture: " + path + " " + e.toString());
            }
        }

        if(typ == CLOVEK)
        {
            scale = Constants.SCALE_PAN;
        }
        multi = true;
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
        if(multi)
        {
            textures.get(noTexture).draw(Math.round(x-cam.getX()), Math.round(y-cam.getY()), scale);
            return;
        }

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

    public void setTexture(int texture)
    {
        if(multi)
        {
            noTexture = texture;
        }
    }

    public Image getTexture()
    {
        Constants.print("Ent, noTexture:", noTexture, "multi:", multi, textures.size());
        if(multi)
            return textures.get(noTexture);

        return texture;
    }

}
