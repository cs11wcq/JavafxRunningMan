package Running_Man_Game;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The circle
 */
public class Circle extends Obstacle
{
    /**
     * Constructor for objects of class Circle
     */
    public Circle(int x, int y, int velocityX, int velocityY)
    {
        super(x, y, velocityX, velocityY);
        zone = new Point2D[4];
        /***********************************Change****************************/
        move = 5;
    }

    public void draw(Graphics g) {
        Graphics2D c = (Graphics2D) g;
        c.setColor(Color.ORANGE);
        g.fillOval(m_x, m_y, DIAMETER, DIAMETER);
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
        // Coords
        zone[0] = new Point2D.Double(m_x, m_y);
        zone[1] = new Point2D.Double(m_x + DIAMETER, m_y);
        zone[2] = new Point2D.Double(m_x, m_y + DIAMETER);
        zone[3] = new Point2D.Double(m_x + DIAMETER, m_y + DIAMETER);
        return zone;
    }
}
