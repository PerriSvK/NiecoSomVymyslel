package sk.perri.spongia.utils;

import sk.perri.spongia.veci.Clovek;

public class Camera
{
    private int lux, luy, rdx, rdy, cx, cy;
    private Clovek osoba;
    private boolean follow = false;

    public Camera(int leftUpX, int leftUpY)
    {
        lux = leftUpX;
        luy = leftUpY;
        rdx = leftUpX + Constants.WINDOW_WIDTH;
        rdy = leftUpY + Constants.WINDOW_HEIGHT;
        cx = leftUpX + Constants.WINDOW_WIDTH / 2;
        cy = leftUpY + Constants.WINDOW_HEIGHT / 2;
    }

    public Camera(Clovek clovek)
    {
        osoba = clovek;
        cx = (int) clovek.getX();
        cy = (int) clovek.getY();
        lux = cx - Constants.WINDOW_WIDTH / 2;
        luy = cy - Constants.WINDOW_HEIGHT / 2;
        rdx = cx + Constants.WINDOW_WIDTH / 2;
        rdy = cy + Constants.WINDOW_HEIGHT / 2;
        follow = true;
    }

    public void follow(boolean follow)
    {
        this.follow = follow;
    }

    public void update()
    {
        cx = (int) Math.round(osoba.getX());
        cy = (int) Math.round(osoba.getY());
        lux = cx - Constants.WINDOW_WIDTH / 2;
        luy = cy - Constants.WINDOW_HEIGHT / 2;
        rdx = cx + Constants.WINDOW_WIDTH / 2;
        rdy = cy + Constants.WINDOW_HEIGHT / 2;
    }

    public int getX() { return lux; }

    public int getY() { return luy; }

    public int getRDX() { return rdx; }

    public int getRDY() { return rdy; }

    public int getCX() { return cx; }

    public int getCY() { return cy; }
}
