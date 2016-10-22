package sk.perri.spongia.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import sk.perri.spongia.utils.Camera;
import sk.perri.spongia.utils.Constants;
import sk.perri.spongia.veci.Inventory;
import sk.perri.spongia.veci.Stack;

import java.util.*;

public class Stage
{
    public Map<Integer, Integer> uloha = new HashMap<>();
    private int stoPercent = 0;
    private int status = 0;
    private boolean completed = false;
    private boolean active = false;
    private float stageLocationX, stageLocationY, stageLocationR;
    private Camera cam;
    private Image quest;

    public Stage(float slx, float sty, float radius, boolean active, Camera cam)
    {
        stageLocationX = slx;
        stageLocationY = sty;
        stageLocationR = radius;
        try
        {
            quest = new Image(Constants.ASSETS_PATH+"quest.png");
        }
        catch (SlickException e)
        {
            System.err.println("Could not load quest.png");
            e.printStackTrace();
        }

        this.active = active;
        this.cam = cam;
    }

    public boolean isIn(double x, double y)
    {
        double a = Math.pow(stageLocationX-x, 2);
        double b = Math.pow(stageLocationY-y, 2);
        return a + b <= Math.pow(stageLocationR, 2);
    }

    public void activate()
    {
        active = true;
    }

    public void deactivate()
    {
        active = false;
    }

    public boolean isActive()
    {
        return active;
    }

    public void draw()
    {
        if(!active || completed)
            return;

        quest.draw(stageLocationX-cam.getX()-quest.getWidth()/2, stageLocationY-cam.getY()-quest.getHeight()/2,
                Math.round(stageLocationR*Math.sqrt(2)), Math.round(stageLocationR*Math.sqrt(2)));

    }

    public void addUloha(int typVeci, int pocet)
    {
        uloha.put(typVeci, pocet);
        stoPercent += pocet;
    }

    public int completeUloha(int typVeci, int pocet)
    {
        int poc = uloha.get(typVeci);
        int ostalo = Math.max(0, poc - pocet);
        uloha.replace(typVeci, ostalo);
        status += poc - ostalo;
        checkComplete();
        return ostalo;
    }

    public void completeUloha(Inventory inv)
    {
        /*Set<Integer> poc = inv.contains(uloha.keySet());
        Integer[] poca = new Integer[poc.size()];
        poc.toArray(poca);*/
        for(Map.Entry<Integer, Integer> en : uloha.entrySet())
        {
            int poc = inv.contains(en.getKey());
            int hod = en.getValue();

            if(poc > 0)
            {
                inv.remove(en.getKey(), poc - Math.max(0, poc-en.getValue()));
                en.setValue(Math.max(0, en.getValue()-poc));
                status += hod - en.getValue();
            }
        }
        checkComplete();
    }

    private void checkComplete()
    {
        int rem = 0;

        Integer[] itm = new Integer[uloha.size()]; uloha.keySet().toArray(itm);
        Integer[] val = new Integer[uloha.size()]; uloha.values().toArray(val);

        for(int i = uloha.size()-1; i >= 0; i--)
        {
            if(val[i] <= 0)
            {
                uloha.remove(itm[i]);
            }
        }

        if(uloha.size() == 0)
            completed = true;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public int getStatus()
    {
        return status/stoPercent;
    }

    public float getX()
    {
        return stageLocationX;
    }

    public float getY()
    {
        return stageLocationY;
    }

    public float getR()
    {
        return stageLocationR;
    }

    public void drawQuest(Graphics g)
    {
        Integer[] itm = new Integer[uloha.size()]; uloha.keySet().toArray(itm);
        Integer[] val = new Integer[uloha.size()]; uloha.values().toArray(val);

        g.drawString("Zozbieraj este:", 10, 25);
        for(int i = 0; i < uloha.size(); i++)
        {
            g.drawString(Stack.typToString(itm[i])+": "+val[i], 13, 40+15*i);
        }
    }
}
