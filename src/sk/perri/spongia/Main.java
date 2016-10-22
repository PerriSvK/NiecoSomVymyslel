package sk.perri.spongia;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import sk.perri.spongia.game.Level;
import sk.perri.spongia.utils.Constants;

public class Main extends BasicGame
{
    private Level level;
    private int gameState = 0; //0 = main menu, 1 = controls, 2 = authors, 3 = playGame
    private boolean startGame = false;
    private Image playButton, conButton, creButton;
    private Rectangle playButtonRect, conButtonRect, creButtonRect;
    private Circle mouseCircle;

    public Main(String name)
    {
        super(name);
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        playButton = new Image(Constants.ASSETS_PATH+"playButton.png");
        playButtonRect = new Rectangle(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 50, playButton.getWidth(), playButton.getHeight());
        conButton = new Image(Constants.ASSETS_PATH+"controlButton.png");
        conButtonRect = new Rectangle(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 200, playButton.getWidth(), playButton.getHeight());
        creButton = new Image(Constants.ASSETS_PATH+"creditsButton.png");
        creButtonRect = new Rectangle(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 450, playButton.getWidth(), playButton.getHeight());
        mouseCircle = new Circle(0, 0, 1);
    }

    public void startGame()
    {
        playButtonRect = null;
        conButtonRect = null;
        creButtonRect = null;
        mouseCircle = null;
        level = new Level("map3");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE) && gameState != 0)
        {
            gameState = 0;
            level = null;
            init(container);
        }

        if(gameState == 3)
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
                    gameState = 3;
                    startGame();
                }
                else if(conButtonRect.contains(mouseCircle) || conButtonRect.intersects(mouseCircle))
                {
                    gameState = 1;
                }
                else if(creButtonRect.contains(mouseCircle) || creButtonRect.intersects(mouseCircle))
                {
                    gameState = 2;
                }
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        switch (gameState)
        {
            case 3: level.render(g); break;
            case 2: drawCredits(g); break;
            case 1: drawControls(g); break;
            default: drawMenu(g);
        }
    }

    private void drawMenu(Graphics g)
    {
        playButton.draw(Constants.WINDOW_WIDTH/2-playButton.getWidth()/2, 100);
        conButton.draw(Constants.WINDOW_WIDTH/2-conButton.getWidth()/2, 250);
        creButton.draw(Constants.WINDOW_WIDTH/2-creButton.getWidth()/2, 400);
    }

    private void drawControls(Graphics g)
    {
        g.setLineWidth(20);
        g.drawString("Pohyb: W - dopredu, S - dozadu, A/D - do strán", Constants.WINDOW_WIDTH/2-50, 100);
        g.drawString("E - Interakcia, R - Reset levelu", Constants.WINDOW_WIDTH/2-40, 150);
        g.drawString("I - vyčistenie inventáru, ESC - Hlavné menu", Constants.WINDOW_WIDTH/2-48, 200);
    }

    private void drawCredits(Graphics g)
    {
        g.setLineWidth(20);
        g.drawString("NiečoSomVymyslel TÍM", Constants.WINDOW_WIDTH/2-20, 50);
        g.drawString("Grafika: Samuel Zeman", Constants.WINDOW_WIDTH/2-25, 100);
        g.drawString("Kód: Adam \"Perri\"Perinay", Constants.WINDOW_WIDTH/2-30, 150);
        g.drawString("Animácie, Web: Jakub Vacula", Constants.WINDOW_WIDTH/2-25, 200);
        g.drawString("Zvukové efekty: Juraj Vašut", Constants.WINDOW_WIDTH/2-25, 250);
    }

    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Main("NiecoSomNakodil"));

        app.setDisplayMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, false);
        app.setVSync(true);
        app.setMaximumLogicUpdateInterval(60);

        app.start();
    }

    @Override
    public void keyPressed(int key, char c)
    {

    }

    @Override
    public void keyReleased(int key, char c)
    {

    }

    @Override
    public void setInput(Input input)
    {

    }

    @Override
    public boolean isAcceptingInput()
    {
        return false;
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
