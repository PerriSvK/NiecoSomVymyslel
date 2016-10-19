package sk.perri.spongia.game;

import org.newdawn.slick.*;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;
import sk.perri.spongia.veci.Clovek;

import java.util.Vector;

public class Level implements KeyListener
{
    private Image dataT, bgT;
    private Clovek ja;
    private Camera camera;
    private int keyState = 0;

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

        ja = new Clovek(1500, 800, "ROBOTIK", "PAN.png");
        camera = new Camera(ja);
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
            ja.move(ang, delta);
        camera.update();
    }
    
    public boolean canGo(double degAngle, long delta)
    {
        Vector<Integer> test = ja.getDownPoints();
        
        boolean vys = true;
        
        for(int i = 0; i < 6; i+=2)
        {
            int tx = (int) Math.round((test.get(i)+Math.cos(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*(delta/(float)1000)) / Constants.SCALE_MAP);
            int ty = (int) Math.round((test.get(i+1)+Math.sin(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*(delta/(float)1000)) / Constants.SCALE_MAP);
            Constants.print("PLAYER X:", ja.getX(), "Y:", ja.getY(), "W:",ja.getTexture().getWidth(), "H:",
                    ja.getTexture().getHeight(), "DATA X:", tx, "Y:", ty);
            if(tx < 0 || tx > dataT.getWidth() || ty < 0 || ty > dataT.getHeight())
            {
                return false;
            }

            if(!dataT.getColor(tx, ty).equals(Color.black))
            {
                vys = false;
                Constants.print("BAD COLOR AT I:", i, "X:", tx, "Y:", ty, "COLOR:", dataT.getColor(tx, ty));
                break;
            }
        }

        Constants.print("RETURN:", vys);
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
        }
    }

    @Override
    public void keyReleased(int key, char c)
    {
        Constants.print("Level, key released:", key);
        switch(key)
        {
            case Constants.CONTROL_UP: keyState -= 1; break;
            case Constants.CONTROL_DOWN: keyState += 1; break;
            case Constants.CONTROL_RIGHT: keyState -= 3; break;
            case Constants.CONTROL_LEFT: keyState += 3; break;
        }
    }

    public void render(Graphics g)
    {
        bgT.draw(-camera.getX(), -camera.getY(), Constants.SCALE_MAP);
        ja.render(camera);
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
