package Running_Man_Game;

import java.awt.*;
import java.awt.geom.Point2D;
/**
 * class Obstacle -- creates the obstacle course for the game take off
 * @version 2
 */

///////////////////////////////////////////////////////////////// BallModel
public abstract class Obstacle
{
    //... Constants
    final static int DIAMETER = 40;

    //... Instance variables
    protected int m_x;           // x and y coordinates upper left
    protected int m_y;

    protected int m_velocityX;   // Pixels to move each time move() is called.
    protected static int m_velocityY;

    protected int m_rightBound;  // Maximum permissible x, y values.
    protected int m_bottomBound;

    /***********************************Change****************************/
    protected int move;

    //... Zone
    protected Point2D[] zone;

    //======================================================== constructor
    public Obstacle(int x, int y, int velocityX, int velocityY) {
        m_x = x;
        m_y = y;
        m_velocityX = velocityX;
        m_velocityY = velocityY;
        move = 0;
    }

    //======================================================== setBounds
    public void setBounds(int width, int height) {
        m_rightBound  = width  - DIAMETER;
        m_bottomBound = height - DIAMETER;
    }

    //============================================================== move
    public void move() {
        //... Move the ball at the given velocity.
        m_x += m_velocityX;
        m_y += m_velocityY;

        //... Bounce the ball off the walls if necessary.

        if (m_x < 0) {                  // If at or beyond left side
            m_x         = 0;            // Place against edge and
            m_velocityX = -m_velocityX; // reverse direction.

        } else if (m_x > m_rightBound) { // If at or beyond right side
            m_x         = m_rightBound;    // Place against right edge.
            m_velocityX = -m_velocityX;  // Reverse direction.
        }

        if (m_y < 0) {                 // if we're at top
            m_y       = 0;
            m_velocityY = -m_velocityY;

        } else if (m_y > m_bottomBound)
        { // if we're at bottom
            // m_y       =  m_bottomBound;
            //m_velocityY = -m_velocityY;
        }
    }

    //============================================================== draw
    public void draw(Graphics g) {
        g.fillOval(m_x, m_y, DIAMETER, DIAMETER);
    }

    //============================================= getDiameter, getX, getY
    public int  getDiameter() { return DIAMETER;}
    public int  getX()        { return m_x;}
    public int  getY()        { return m_y;}
    public int getBound()     { return m_bottomBound; }

    //======================================================== setPosition
    public void setPosition(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public void changeVel(int change)
    {
        m_velocityY += change;
    }

    public int getVel()
    {
        return m_velocityY;
    }

    /**
     * Is the move matching?
     */
    public abstract int getMove();


    /**
     * Returns the array of Point2D Coordinates
     */
    public abstract Point2D[] getZone();
}