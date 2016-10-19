package sk.perri.spongia.utils;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class Constants
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final String ASSETS_PATH = "/assets/";

    public static final float SCALE_MAP = 1f;
    public static final float SCALE_PAN = 1f;
    public static final float SPEED_CLOVEK = 100.0f;

    public static final Color COLOR_STAND = Color.black;

    public static final int CONTROL_UP = Input.KEY_W;
    public static final int CONTROL_DOWN = Input.KEY_S;
    public static final int CONTROL_RIGHT = Input.KEY_D;
    public static final int CONTROL_LEFT = Input.KEY_A;

    public static void print(Object... o)
    {
        String res = "";
        int i = 0;
        for(Object ob : o)
            res += ob.toString()+(i++ < o.length ? " " : "");

        System.out.println(res);
    }
}
