package Running_Man_Game;

import java.awt.*;
import java.awt.geom.Point2D;
/**
 * Write a description of class Triangle here.
 *
 * @author Meep
 * @version (a version number or a date)
 */
public class Triangle extends Obstacle
{
    private int[] ty = new int[3];
    private int[] tx = new int[3];
    private int[] lx = new int[3];
    private int[] ly = new int[3];
    private int[] px = new int[3];
    private int[] py = new int[3];

    /**
     * Constructor for objects of class Triangle
     */
    public Triangle(int x, int y, int velocityX, int velocityY)
    {
        super(x, y, velocityX, velocityY);
        zone = new Point2D[4];
        /***********************************Change****************************/
        move = 1;
    }

    public void draw(Graphics g)
    {
        Graphics2D t = (Graphics2D) g; // Body

        // Spike one
        ty[0] = m_y;
        ty[1] = ty[0] - 16;
        ty[2] = ty[0];
        tx[0] = m_x + 40;
        tx[1] = tx[0] - 8;
        tx[2] = tx[0] - 16;
        // Spike two
        ly[0] = m_y + 20;
        ly[1] = ly[0] - 16;
        ly[2] = ly[0];
        lx[0] = m_x + 60;
        lx[1] = lx[0] - 8;
        lx[2] = lx[0] - 16;
        //Spike three
        py[0] = m_y + 20;
        py[1] = py[0] - 16;
        py[2] = py[0];
        px[0] = m_x + 20;
        px[1] = px[0] - 8;
        px[2] = px[0] - 16;

        t.setColor(Color.RED);

        t.fillPolygon(tx, ty, 3); // Traingle
        t.fillPolygon(lx, ly, 3);
        t.fillPolygon(px, py, 3);

    }

    /**
     * Returns the array of Point2D Coordinates
     */
    public Point2D[] getZone()
    {
        // Coordinates
        zone[0] = new Point2D.Double(px[2],m_y);
        zone[1] = new Point2D.Double(lx[1],m_y);
        zone[2] = new Point2D.Double(px[2],py[0]);
        zone[3] = new Point2D.Double(lx[1],py[0]);

        return zone;
    }

    public int getMove()
    {
        /***********************************Change****************************/
        return move;
    }

}
