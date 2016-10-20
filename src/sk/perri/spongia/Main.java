package sk.perri.spongia;

import org.newdawn.slick.*;
import sk.perri.spongia.game.Level;
import sk.perri.spongia.utils.Constants;

public class Main extends BasicGame
{
    private Level level;

    public Main(String name)
    {
        super(name);
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        level = new Level("map3");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        level.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        level.render(g);
    }

    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Main("Spongia"));

        app.setDisplayMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, false);
        app.setVSync(true);
        app.setMaximumLogicUpdateInterval(60);

        app.start();
    }
}
