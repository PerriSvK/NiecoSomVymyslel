package sk.perri.spongia.game;

import org.newdawn.slick.*;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;
import sk.perri.spongia.veci.Clovek;
import sk.perri.spongia.veci.Entit;
import sk.perri.spongia.veci.Vec;

import java.util.Vector;

public class Level implements KeyListener
{
    private Image dataT, bgT;
    private Clovek ja;
    private Camera camera;
    private Vector<Vec> vec = new Vector<>();
    private int keyState = 0;
    private boolean boosted = false;
    private boolean ePressed = false;

    public Level(String mapName)
    {
        try
        {
            dataT = new Image(Constants.ASSETS_PATH+mapName+"Data.png");
            bgT = new Image(Constants.ASSETS_PATH+mapName+".png");
        }
        catch (SlickException e)
        {
            System.err.println("Could not bind map texture or data(.png): "+mapName);
            e.printStackTrace();
        }

        ja = new Clovek((int)Math.round(1500*Constants.SCALE_MAP), (int) Math.round(800*Constants.SCALE_MAP), "ROBOTIK", "r", 8);
        camera = new Camera(ja);
        vec.add(new Vec((int)Math.round(1600*Constants.SCALE_MAP), (int) Math.round(850*Constants.SCALE_MAP), Vec.DREVO, "drevo.png"));
    }

    public void update(GameContainer gc, long delta)
    {
        gc.getInput().addKeyListener(this);
        int ang = -1;
        switch (keyState)
        {
            case 1: ang = 270; break;
            case -1: ang = 90; break;
            case 3: ang = 0; break;
            case -3: ang = 180; break;
            case 4: ang = 315; break;
            case -4: ang = 135; break;
            case 2: ang = 45; break;
            case -2: ang = 225; break;
        }

        if(ang != -1 && canGo(ang, delta))
        {
            ja.move(ang, delta, boosted);
            ja.setTexture(ang / 45);
            ja.setVisibility(true);
        }

        if(ePressed)
        {
            checkCollisions();
        }

        camera.update();
    }

    public void checkCollisions()
    {
        Constants.print("Level, test colide, picking up, vec:", vec.size());
        for(int i = 0; i < vec.size(); i++)
        {
            if(Entit.collide(ja, vec.get(i)))
            {
                Constants.print("Level, colide, picking up, vec:", vec.size());
                ja.getInv().add(vec.get(i));
                vec.get(i).setVisibility(false);
                vec.remove(i);
            }
        }
    }
    
    public boolean canGo(double degAngle, long delta)
    {
        Vector<Integer> test = ja.getDownPoints();
        
        boolean vys = true;
        
        for(int i = 0; i < test.size(); i+=2)
        {
            int tx = (int) Math.round((test.get(i)+Math.cos(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*(delta/(float)1000)) / Constants.SCALE_MAP);
            int ty = (int) Math.round((test.get(i+1)+Math.sin(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*(delta/(float)1000)) / Constants.SCALE_MAP);
            if(tx < 0 || tx > dataT.getWidth() || ty < 0 || ty > dataT.getHeight())
                return false;

            if(!dataT.getColor(tx, ty).equals(Color.black))
            {

                vys = false;
                break;
            }
        }
        return vys;
    }

    @Override
    public void keyPressed(int key, char c)
    {

        switch(key)
        {
            case Constants.CONTROL_UP: keyState += 1; break;
            case Constants.CONTROL_DOWN: keyState -= 1; break;
            case Constants.CONTROL_RIGHT: keyState += 3; break;
            case Constants.CONTROL_LEFT: keyState -= 3; break;
            case Constants.CONTROL_SPEED: boosted = true; break;
            case Constants.CONTROL_USE: ePressed = true; break;
        }
    }

    @Override
    public void keyReleased(int key, char c)
    {
        switch(key)
        {
            case Constants.CONTROL_UP: keyState -= 1; break;
            case Constants.CONTROL_DOWN: keyState += 1; break;
            case Constants.CONTROL_RIGHT: keyState -= 3; break;
            case Constants.CONTROL_LEFT: keyState += 3; break;
            case Constants.CONTROL_SPEED: boosted = false; break;
            case Constants.CONTROL_RESET: ja.setPos((int)Math.round(1500*Constants.SCALE_MAP), (int) Math.round(800*Constants.SCALE_MAP)); camera.update(); break;
            case Constants.CONTROL_USE: ePressed = false; break;
        }
    }

    public void render(Graphics g)
    {
        bgT.draw(-camera.getX(), -camera.getY(), Constants.SCALE_MAP);
        for(Vec v : vec)
            v.render(camera);

        ja.render(camera);
        ja.getInv().drawStatus(g);
    }

    @Override
    public void setInput(Input input)
    {

    }

    @Override
    public boolean isAcceptingInput()
    {
        return true;
    }

    @Override
    public void inputEnded()
    {

    }

    @Override
    public void inputStarted()
    {

    }
}
