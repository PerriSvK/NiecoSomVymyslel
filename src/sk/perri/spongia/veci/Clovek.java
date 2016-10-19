package sk.perri.spongia.veci;

import sk.perri.spongia.utils.Constants;

import java.util.Vector;

public class Clovek extends Entit
{
    private String name;
    private int LDX, LDY, RDX, RDY, CDX, CDY;

    public Clovek(int x, int y, String name, String textureName)
    {
        super(Entit.CLOVEK, x, y, Constants.ASSETS_PATH+textureName);
        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
    }

    public void move(double degAngle, long delta)
    {
        super.setPos(getX()+Math.cos(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*delta/(float)1000,
                getY()+Math.sin(Math.toRadians(degAngle))*Constants.SPEED_CLOVEK*delta/(float)1000);

        LDX = (int)getX();
        LDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        CDX = (int)getX() + Math.round(getTexture().getWidth() / 2*Constants.SCALE_PAN);
        CDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
        RDX = (int)getX() + Math.round(getTexture().getWidth()*Constants.SCALE_PAN);
        RDY = (int)getY() + Math.round(getTexture().getHeight()*Constants.SCALE_PAN);
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
        Constants.print("SEND:", LDX, LDY, CDX, CDY, RDX, RDY);
        return res;
    }
}
