package sk.perri.spongia.utils;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class Constants
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final String ASSETS_PATH = "assets/";

    public static final float SCALE_MAP = 1.6f;
    public static final float SCALE_PAN = 0.1f;
    public static final float SCALE_VEC = 0.05f;
    public static final float SPEED_CLOVEK = 150.0f;
    public static final float SPEED_BOOST_CLOVEK = 500.0f;
    public static final float TIME_LIMIT = 210000;

    public static final Color COLOR_STAND = Color.black;

    public static final int CONTROL_UP = Input.KEY_W;
    public static final int CONTROL_DOWN = Input.KEY_S;
    public static final int CONTROL_RIGHT = Input.KEY_D;
    public static final int CONTROL_LEFT = Input.KEY_A;
    public static final int CONTROL_SPEED = Input.KEY_LCONTROL;
    public static final int CONTROL_RESET = Input.KEY_R;
    public static final int CONTROL_USE = Input.KEY_E;
    public static final int CONTROL_RESET_INV = Input.KEY_I;

    public static void print(Object... o)
    {
        String res = "";
        int i = 0;
        for(Object ob : o)
            res += ob.toString()+(i++ < o.length ? " " : "");

        System.out.println(res);
    }


}
