import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class PlayField here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayField extends World
{
    private boolean startGame = false;
    private static Ball theBall;
    private static Score player1Score;
    private static Score player2Score;

    /**
     * Constructor for objects of class PlayField.
     * 
     */
    public PlayField()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        createBackground();
        initializePlayingField();
    }
    
    private void createBackground()
    {
        getBackground().setColor( Color.BLACK );
        getBackground().fillRect( 0, 0, getWidth(), getHeight() );
        
        getBackground().setColor( Color.GRAY );
        getBackground().fillRect( getWidth()/2 - 2, 0, 5, getHeight() );
        showText("Press space to begin the game", 200, 550 );
    }
    
    private void initializePlayingField()
    {
        theBall = new Ball();
        player1Score = new Score(true);
        player2Score = new Score(false);
        addObject( theBall, getWidth()/2, getHeight()/2 );
        addObject( player1Score, 200, 50 );
        addObject( player2Score, 600, 50 );
        addObject( new Paddle(true), 10, getHeight()/2 );
        addObject( new Paddle(false), getWidth() - 10, getHeight()/2 );
    }
    
    public void act()
    {
        if( startGame == false )
        {
            checkKeyPress();
            checkWin();
        }
    }
    
    private void checkKeyPress()
    {
        if( Greenfoot.isKeyDown("space") )
        {
            startGame = true;
            showText("", 200, 550 );
            theBall.setVelocity(5);
        }
    }
    
    public void reset()
    {
        theBall.setLocation( getWidth()/2, getHeight()/2 );
        theBall.setVelocity(0);
        showText("Press spaceBar to begin", 200, 550 );
        startGame = false;
    }
    
    private void checkWin()
    {
        GreenfootImage player1Win = new GreenfootImage("player 1 Wins!", 45, Color.RED, Color.BLACK );
        GreenfootImage player2Win = new GreenfootImage("player 2 Wins!", 45, Color.BLUE, Color.BLACK );
        if( player1Score.getScore() >=7)
        {
            removeObjects( getObjects(null) );
            getBackground().setColor( Color.BLACK );
            getBackground().fillRect( 0, 0, getWidth(), getHeight() );
            getBackground().drawImage( player1Win, getWidth()/2, getHeight() );
        }
        
        if( player2Score.getScore() >=7)
        {
            removeObjects( getObjects(null) );
            getBackground().setColor( Color.BLACK );
            getBackground().fillRect( 0, 0, getWidth(), getHeight() );
            getBackground().drawImage( player2Win, getWidth()/2, getHeight() );
        }
    }
    
    public boolean getStarted()
    {
        return startGame;
    }
    
}
