package Running_Man_Game;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Bar class
 */
public class Bar extends Obstacle
{

    /**
     * Constructor for objects of class Square
     */
    public Bar(int x, int y, int velocityX, int velocityY)
    {
        super(x, y, velocityX, velocityY);
        zone = new Point2D[4];
        /***********************************Change****************************/
        move = 2;
    }

    public void draw(Graphics g) {
        Graphics2D b = (Graphics2D) g; // Body
        b.setColor(Color.GREEN);

        b.fillRect(m_x - 10, m_y, 100, 10);
    }

    public int getMove()
    {
        /***********************************Change****************************/
        return move;
    }

    /**
     * Returns the array of Point2D Coordinates
     */
    public Point2D[] getZone()
    {
        // Coordinates
        zone[0] = new Point2D.Double(m_x, m_y);
        zone[1] = new Point2D.Double(m_x + 100, m_y);
        zone[2] = new Point2D.Double(m_x, m_y + 10);
        zone[3] = new Point2D.Double(m_x + 100, m_y + 10);

        return zone;
    }
}












