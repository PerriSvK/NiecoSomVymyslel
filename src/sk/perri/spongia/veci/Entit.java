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
    public int typ;
    private boolean visible = true;
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
        else if(typ == VEC)
        {
            scale = Constants.SCALE_VEC;
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
        if(!visible)
            return;

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
        if(multi)
            return textures.get(noTexture);

        return texture;
    }

    public float getScale()
    {
        return scale;
    }

    public void setVisibility(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public static boolean collide(Entit e1, Entit e2)
    {
        boolean coll = false;
        if((e1.getX() < e2.getX() && e2.getX() < e1.getX()+e1.getTexture().getWidth()*e1.getScale()) ||
                (e2.getX() < e1.getX() && e1.getX() < e2.getX()+e2.getTexture().getWidth()*e2.getScale()))
            if((e1.getY() < e2.getY() && e2.getY() < e1.getY()+e1.getTexture().getHeight()*e1.getScale()) ||
                    (e2.getY() < e1.getY() && e1.getY() < e2.getY()+e2.getTexture().getHeight()*e2.getScale()))
                coll = true;
        /*if(e2.getY() - e1.getY() > 0)
        {
            if (e2.getY() - e1.getY() < e1.getTexture().getHeight() * e1.getScale() &&
                    e2.getX() - e1.getX() < e1.getTexture().getWidth() * e1.getScale())
                coll = true;
        }
        else
        {
            if (e1.getY() - e2.getY() < e2.getTexture().getHeight() * e2.getScale() &&
                    e1.getX() - e2.getX() < e2.getTexture().getWidth() * e2.getScale())
                coll = true;
        }*/
        Constants.print("Entit, return coll:", coll, "e1 X:", e1.getX(), "Y:", e1.getY(), "W:",
                e1.getTexture().getWidth()*e1.getScale(), "H:", e1.getTexture().getHeight()*e1.getScale(),
                "e2 X:", e2.getX(), "Y:", e2.getY(), "W:",
                e2.getTexture().getWidth()*e2.getScale(), "H:", e2.getTexture().getHeight()*e2.getScale());
        return coll;
    }
}
