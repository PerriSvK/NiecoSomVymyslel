package sk.perri.spongia.game;

import com.sun.xml.internal.messaging.saaj.soap.ImageDataContentHandler;
import org.newdawn.slick.*;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;
import sk.perri.spongia.veci.*;

import java.util.Vector;

public class Level implements KeyListener
{
    private Image dataT, bgT, vyh, preh;
    private Clovek ja;
    private Camera camera;
    //private Spawn spawn, spawn1;
    private Spawner spawnDrevo, spawnZelezo, spawnBeton, spawnPalivo, spawnClovek;
    private Vector<Vec> vec = new Vector<>();
    private Vector<Stage> stages = new Vector<>();
    private Vector<Image> raketa = new Vector<>();
    private int stagesComplete = 0;
    private int raketaStupen = -1;
    private int keyState = 0;
    private boolean boosted = false;
    private boolean ePressed = false;
    private boolean resetInv = false;
    private float time = 0;
    private float timeleft = Constants.TIME_LIMIT;
    private int gameStatus = 0; //0 = playing the game, 1 = game over, 2 = you win
    private String mapName;
    public Level(String mapName)
    {
        this.mapName = mapName;
        try
        {
            vyh = new Image(Constants.ASSETS_PATH+"vyhra.png");
            preh = new Image(Constants.ASSETS_PATH+"prehra.png");
            dataT = new Image(Constants.ASSETS_PATH+mapName+"Data.png");
            bgT = new Image(Constants.ASSETS_PATH+mapName+".png");
            raketa.add(new Image(Constants.ASSETS_PATH+"rak1.png"));
            raketa.add(new Image(Constants.ASSETS_PATH+"rak2.png"));
            raketa.add(new Image(Constants.ASSETS_PATH+"rak3.png"));
        }
        catch (SlickException e)
        {
            System.err.println("Could not bind map texture or data(.png): "+mapName);
            e.printStackTrace();
        }

        ja = new Clovek((int)Math.round(1500*Constants.SCALE_MAP), (int) Math.round(800*Constants.SCALE_MAP), "ROBOTIK", "r", 8);
        camera = new Camera(ja);
        /*spawn = new Spawn(Vec.DREVO, (int)Math.round(1500*Constants.SCALE_MAP), (int) Math.round(900*Constants.SCALE_MAP),
                (int)Math.round(1600*Constants.SCALE_MAP), (int) Math.round(1000*Constants.SCALE_MAP), 4000);
        spawn1 = new Spawn(Vec.ZELEZO, (int)Math.round(1600*Constants.SCALE_MAP), (int) Math.round(900*Constants.SCALE_MAP),
                (int)Math.round(1700*Constants.SCALE_MAP), (int) Math.round(1000*Constants.SCALE_MAP), 4000);*/
        spawnDrevo = new Spawner(Color.green, Vec.DREVO);
        spawnZelezo = new Spawner(Color.magenta, Vec.ZELEZO);
        spawnBeton = new Spawner(Color.cyan, Vec.BETON);
        spawnClovek = new Spawner(Color.pink, Vec.CLOVEK);
        spawnPalivo = new Spawner(Color.orange, Vec.PALIVO);

        //stages
        stages.add(new Stage(2492*Constants.SCALE_MAP, 1172*Constants.SCALE_MAP, 150, true, camera));
        stages.get(0).addUloha(Vec.DREVO, 10);
        stages.get(0).addUloha(Vec.ZELEZO, 10);
        stages.get(0).addUloha(Vec.BETON, 10);
        stages.add(new Stage(2492*Constants.SCALE_MAP, 1172*Constants.SCALE_MAP, 150, true, camera));
        stages.get(1).addUloha(Vec.ZELEZO, 40);
        stages.add(new Stage(2492*Constants.SCALE_MAP, 1172*Constants.SCALE_MAP, 150, true, camera));
        stages.get(2).addUloha(Vec.ZELEZO, 50);
        stages.add(new Stage(2492*Constants.SCALE_MAP, 1172*Constants.SCALE_MAP, 150, true, camera));
        stages.get(3).addUloha(Vec.PALIVO, 30);
        stages.add(new Stage(2492*Constants.SCALE_MAP, 1172*Constants.SCALE_MAP, 150, true, camera));
        stages.get(4).addUloha(Vec.CLOVEK, 11);
    }

    public void update(GameContainer gc, long delta)
    {
        if(gameStatus != 0)
            return;

        spawnDrevo.updateMinihra(gc);
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
            Constants.print("Check color at x:", (int)Math.round(ja.getX()/Constants.SCALE_MAP), "y:",
                    (int)Math.round(ja.getY()/Constants.SCALE_MAP));
            checkSpawners(dataT.getColor((int)Math.round(ja.getX()/Constants.SCALE_MAP),
                    (int)Math.round(ja.getY()/Constants.SCALE_MAP)));
        }

        if(resetInv && !ja.getInv().isEmpty())
        {
            resetInv = false;
            ja.getInv().clear();
        }

        camera.update();

        /*Vec v = spawn.update(delta);
        if(v != null)
        {
            vec.add(v);
            v = null;
        }

        v = spawn1.update(delta);
        if(v != null)
        {
            vec.add(v);
            v = null;
        }*/

        if(stages.get(Math.min(stages.size() - 1, stagesComplete)).isIn(ja.getX(), ja.getY()))
        {
            stages.get(Math.min(stages.size() - 1, stagesComplete)).completeUloha(ja.getInv());
        }

        if(stages.get(Math.min(stages.size() - 1,stagesComplete)).isCompleted())
        {
            stages.get(Math.min(stages.size() - 1,stagesComplete)).deactivate();
            raketaStupen = Math.min(raketa.size()-1, stagesComplete);
            stagesComplete++;
            Constants.print("STAGE", stagesComplete, "COMPLETED!------------------------------------------------------",
                    raketaStupen);

            if(stagesComplete == stages.size())
            {
                gameStatus = 2;
            }
        }

        if(Math.floor(time + delta / (float)1000) > Math.floor(time))
            Constants.print("time:", time);
        timeleft -= delta;
        if(timeleft <= 0)
            gameStatus = 1;
        time += delta/(float)1000;
    }

    private void checkSpawners(Color col)
    {
        if(spawnDrevo.isIn(col) != -1)
        {
            ja.getInv().add(new Vec(0, 0, Vec.DREVO, "drevo.png"));
        }

        if(spawnZelezo.isIn(col) != -1)
        {
            ja.getInv().add(new Vec(0, 0, Vec.ZELEZO, "zelezo.png"));
        }

        if(spawnBeton.isIn(col) != -1)
        {
            ja.getInv().add(new Vec(0, 0, Vec.BETON, "zelezo.png"));
        }

        if(spawnPalivo.isIn(col) != -1)
        {
            ja.getInv().add(new Vec(0, 0, Vec.PALIVO, "zelezo.png"));
        }

        if(spawnClovek.isIn(col) != -1)
        {
            ja.getInv().add(new Vec(0, 0, Vec.CLOVEK, "zelezo.png"));
        }
    }

    public void checkCollisions()
    {
        /*Constants.print("Level, test colide, picking up, vec:", vec.size());*/
        for(int i = 0; i < vec.size(); i++)
        {
            if(ja.getInv().isFull())
                break;

            if(Entit.collide(ja, vec.get(i)))
            {
                /*Constants.print("Level, colide, picking up, vec:", vec.size());*/
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

            if(dataT.getColor(tx, ty).equals(Color.red) || dataT.getColor(tx, ty).equals(Color.blue))
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
            //case Constants.CONTROL_SPEED: boosted = true; break;
            case Constants.CONTROL_USE: ePressed = true; break;
            case Constants.CONTROL_RESET_INV: resetInv = true; break;
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
            case Constants.CONTROL_USE: ePressed = false; break;
        }
    }

    public void render(Graphics g)
    {
        if(gameStatus == 2)
        {
            vyh.draw(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            return;
        }
        else if(gameStatus == 1)
        {
            preh.draw(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            return;
        }

        bgT.draw(-camera.getX(), -camera.getY(), Constants.SCALE_MAP);
        for(Vec v : vec)
            v.render(camera);

        //stages.get(0).draw();
        ja.render(camera);
        ja.getInv().drawStatus(g);

        if(raketaStupen > -1)
        {
            raketa.get(raketaStupen).draw(stages.get(Math.min(stages.size()-1, stagesComplete)).getX()-camera.getX(),
                    stages.get(Math.min(stages.size()-1, stagesComplete)).getY()-camera.getY(),
                    stages.get(Math.min(stages.size()-1, stagesComplete)).getR(),
                    stages.get(Math.min(stages.size()-1, stagesComplete)).getR());
        }

        stages.get(Math.min(stages.size()-1, stagesComplete)).drawQuest(g);
        if(timeleft < 10020)
        {
            g.setColor(Color.red);
        }
        else if(timeleft < 30020)
        {
            g.setColor(Color.orange);
        }

        g.drawString("Cas do narazu: "+Double.toString((double)Math.round(timeleft/10)/100), 90, 10);
        g.setColor(Color.white);
    }

    private void drawWin(Graphics g)
    {

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
