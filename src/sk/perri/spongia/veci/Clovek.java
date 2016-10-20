package sk.perri.spongia.veci;

import sk.perri.spongia.utils.Constants;

import java.util.Vector;
import java.util.concurrent.CompletionService;

public class Clovek extends Entit
{
    private String name;
    private int LDX, LDY, RDX, RDY, CDX, CDY, RCX, RCY, RUX, RUY, CUX, CUY, LUX, LUY, LCX, LCY;
    private Inventory inv;


    public Clovek(int x, int y, String name, String textureName)
    {
        super(Entit.CLOVEK, x, y, Constants.ASSETS_PATH+textureName);
        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RCX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
        RUX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RUY = (int)getY();
        CUX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CUY = (int)getY();
        LUX = (int)getX();
        LUY = (int)getY();
        LCX = (int)getX();
        LCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
    }

    public Clovek(int x, int y, String name, String textureName, int count)
    {
        super(Entit.CLOVEK, x, y, Constants.ASSETS_PATH+textureName, count);
        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RCX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
        RUX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RUY = (int)getY();
        CUX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CUY = (int)getY();
        LUX = (int)getX();
        LUY = (int)getY();
        LCX = (int)getX();
        LCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
        inv = new Inventory(10);
    }

    public Inventory getInv()
    {
        return inv;
    }

    public void move(double degAngle, long delta, boolean boosted)
    {
        float speed = boosted ? Constants.SPEED_BOOST_CLOVEK : Constants.SPEED_CLOVEK;
        super.setPos(getX()+Math.cos(Math.toRadians(degAngle))*speed*delta/(float)1000,
                getY()+Math.sin(Math.toRadians(degAngle))*speed*delta/(float)1000);

        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RCX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
        RUX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RUY = (int)getY();
        CUX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CUY = (int)getY();
        LUX = (int)getX();
        LUY = (int)getY();
        LCX = (int)getX();
        LCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
    }

    public Vector<Integer> getDownPoints()
    {
        Vector<Integer> res= new Vector<>();
        res.add(LDX);
        res.add(LDY);
        res.add(CDX);
        res.add(CDY);
        res.add(RDX);
        res.add(RDY);
        res.add(RCX);
        res.add(RCY);
        res.add(RUX);
        res.add(RUY);
        res.add(CUX);
        res.add(CUY);
        res.add(LUX);
        res.add(LUY);
        res.add(LCX);
        res.add(LCY);
        return res;
    }

    @Override
    public void setPos(double x, double y)
    {
        super.setPos(x, y);
        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RCX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
        RUX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RUY = (int)getY();
        CUX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CUY = (int)getY();
        LUX = (int)getX();
        LUY = (int)getY();
        LCX = (int)getX();
        LCY = (int)getY() + Math.round(getTexture().getHeight() /2*Constants.SCALE_PAN);
    }
}
