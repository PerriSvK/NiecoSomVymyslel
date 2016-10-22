package sk.perri.spongia;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import sk.perri.spongia.game.Level;
import sk.perri.spongia.utils.Constants;

public class Main extends BasicGame
{
    private Level level;
    private boolean startGame = false;
    Image playButton;
    Rectangle playButtonRect;
    Circle mouseCircle;

    public Main(String name)
    {
        super(name);
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        playButton = new Image(Constants.ASSETS_PATH+"playButton.png");
        playButtonRect = new Rectangle(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 200, playButton.getWidth(), playButton.getHeight());
        mouseCircle = new Circle(0, 0, 1);
    }

    public void startGame()
    {
        playButtonRect = null;
        mouseCircle = null;
        level = new Level("map3");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        if(startGame)
        {
            level.update(container, delta);
        }
        else
        {
            mouseCircle.setCenterX(container.getInput().getMouseX());
            mouseCircle.setCenterY(container.getInput().getMouseY());

            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
            {
                if(playButtonRect.contains(mouseCircle) || playButtonRect.intersects(mouseCircle))
                {
                    startGame = true;
                    startGame();
                }
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        if(startGame)
        {
            level.render(g);
        }
        else
        {
            playButton.draw(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 200);
        }
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
