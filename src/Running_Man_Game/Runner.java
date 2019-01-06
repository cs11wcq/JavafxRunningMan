package Running_Man_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.geom.Point2D;
/**
 * Write a description of class Runner here.
 * @version (a version number or a date)
 */
public class Runner extends JPanel implements ActionListener
{
    JLabel label = new JLabel("Lives: 3");
    private JLabel score = new JLabel("SCORE: 0");
    private JLabel levelLabel = new JLabel("Level: 1");
    private double score1 = 0;
    //... Instance variables for the animation
    private int   m_interval  = 60;  // Milliseconds between updates.
    private Timer m_timer;           // Timer fires to animate one step.
    private List<Obstacle> list = new ArrayList<>();
    private int lives;
    private int level = 1;
    private int w = 300;

    protected double x = 180; // X position of the body
    protected double y = 600;
    protected double velx = 0; // Change in x for body
    protected double vely = 0; // Change in y for body
    protected int tumble = 0;

    private double x1 = 180; // Position of the hand (necessary for jumping animation)
    private double x2 = 180; // Position for other hand

    private double y1 = 620; //Y coordinate of hand 1
    private double y2 = 620; //Y coordinate of hand 2
    private double inc1 = 0.4; // Frequency of arm movement
    private double inc2 = -0.4;


    private int sizeB = 60; // Radius of the size of the body
    private int sizeH = 20; // Radius of the size of the hands
    private int hops = 0; // Jump or nah?
    private int move = 0; // Movement type
    private Point2D[] runnerArray;


    private Timer t = new Timer(5, this);
    /**
     * Constructor for objects of class Runner
     */
    public Runner()
    {
        t.start();
        addKeyListener(new GameKeyAdapter(this));
        setFocusable(true);

        lives = 3;
        add(label, BorderLayout.SOUTH);
        add(score, BorderLayout.SOUTH);
        add(levelLabel);
        setPreferredSize(new Dimension(420, 700));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        m_timer = new Timer(m_interval, new TimerAction());
        m_timer.start();

        /***********************************Change Start****************************/
        Point2D p1 = new Point2D.Double(returnX(), returnY());
        Point2D p2 = new Point2D.Double(returnX() + 60, returnY());
        Point2D p3 = new Point2D.Double(returnX(), returnY()+60);
        Point2D p4 = new Point2D.Double(returnX()+ 60, returnY()+60);
        /***********************************Change End****************************/
        runnerArray = new Point2D[]{p1, p2, p3, p4};
        //each index of this array corresponds to a certain zone of the runner
        //topleft, topright, bottomleft, bottomright
        // each index of this array corresponds to a certain zone of the runner
        //topleft, topright, bottomleft, bottomright

    }

    /**
     * Painting the body, hands, and feet
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Paint background, border
        for (Obstacle b: list)
        {
            b.draw(g);           // Draw the obstacle
        }

        g.setColor(Color.BLUE);
        Graphics2D g2 = (Graphics2D) g; // Body
        Graphics2D g1 = (Graphics2D) g; // Hand
        Graphics2D g3 = (Graphics2D) g; // Hand
        Graphics2D f1 = (Graphics2D) g;
        Graphics2D f2 = (Graphics2D) g;

        g2.fill(new Ellipse2D.Double(x, y, sizeB, sizeB));

        //Try the hands
        g1.fill(new Ellipse2D.Double(x1 - 30, y1, sizeH, sizeH));
        g3.fill(new Ellipse2D.Double(x2 + 70, y2, sizeH, sizeH));

        // Now the feet
        g1.fill(new Ellipse2D.Double(x + 5, y2 - 15, sizeH, sizeH));
        g3.fill(new Ellipse2D.Double(x + 35, y1 - 15, sizeH, sizeH));
    }

    //////////////////////////////////// inner listener class ActionListener
    class TimerAction implements ActionListener {
        //================================================== actionPerformed
        /** ActionListener of the timer.  Each time this is called,
         *  the obstacle's position is updated, creating the appearance of
         *  movement.
         *@param e This ActionEvent parameter is unused.
         */
        public void actionPerformed(ActionEvent e)
        {
            updateScore(false);
            //Ball b = new Ball(0, 0, 2, 3);
            //list.add(b);
            int x = 1;
            int startX = 0;
            int lane, shape;
            int ran = (int)(Math.random()*35); //randomly generated number btwn 0 and 34
            boolean collision;

            //System.out.println(ran);
            Obstacle b = new Circle(startX, 0, 0, ((int)Math.random()*10)+2);

            //if the randomly generated number == 1 then a new obstacle is created
            if (ran == x)
            {
                lane = (int)(Math.random() *3);
                shape = (int)(Math.random() *3);

                if(lane == 0)
                    startX = 60;
                else
                if(lane == 1)
                    startX = 180;
                else
                if(lane == 2)
                    startX = 300;

                b = new Circle(startX, 0, 0, ((int)Math.random()*10)+2);
                if(shape == 0)
                    b = new Bar(startX, 0, 0, 5);
                else
                if(shape == 1)
                    b = new Circle(startX, 0, 0, 5);
                else
                if(shape == 2)
                    b = new Triangle(startX, 0, 0, 5);

                list.add(0, b);
            }

            for(int a = 0; a < list.size(); a++)
            {
                list.get(a).setBounds(getWidth(), getHeight());
                list.get(a).move();  // Move the ball.
                repaint();      // Repaint indirectly calls paintComponent.

                //THIS WAS THE FIX
                //Before, we were not passing in the updated coordinates of the runner
                // Just like in a halo game, you have to constantly update the player's position to reflect
                //where the player is at that moment in time
                //these next lines do that
                Point2D p1 = new Point2D.Double(returnX(), returnY());
                Point2D p2 = new Point2D.Double(returnX() + sizeB, returnY());
                Point2D p3 = new Point2D.Double(returnX(), returnY()+sizeB);
                Point2D p4 = new Point2D.Double(returnX()+ sizeB, returnY()+sizeB);
                runnerArray = new Point2D[]{p1, p2, p3, p4};
                collision = checkCollision(runnerArray, list.get(a).getZone(), a, list.get(a));


                // Take out the obstacle if the collision happens

                if(list.get(list.size() - 1).getY() > 700)
                {
                    list.remove(list.size()-1);
                    updateScore(true);
                }
            }
        }
    }

    /**
     * Updates the score
     * avoided will be true if the obstacle was avoided (in which case more points will be added to score)
     */
    public void updateScore(boolean avoided)
    {
        //if obstacle was avoided, add points to their score
        if (avoided == true)
        {
            score1+=100;
        }
        score1 += 10;
        //if they avoided obstacle add points to their score
        score.setText("Score: " + score1);

        if (score1 >= w)
        {
            w *= 2;
            if (score1 >= 1000)
                level++;
            if (m_interval > 0)
                m_interval -= 5;
            m_timer.setDelay(m_interval);
        }
        levelLabel.setText("Level: " + level);
    }

    /**
     * This method checks to see if there is a collision between the runner and the specified obstacle
     * @param runnerArray
     * @param obstacleArray
     */
    public boolean checkCollision(Point2D[] runnerArray, Point2D[] obstacleArray, int indexOfObstacle, Obstacle obs)
    {
        boolean collision = false;
        //if there is a collision
        double obTopLeft = obstacleArray[0].getX();
        double obTopRight = obstacleArray[1].getX();
        double obBotLeftY = obstacleArray[2].getY();
        System.err.print(" Y: " + obBotLeftY);
        double runTopLeft = runnerArray[0].getX();////////////////////
        double runTopRight = runnerArray[1].getX();
        double runTopLeftY = runnerArray[0].getY();
        double runBotLeftY = runnerArray[2].getY();

        boolean bothTopLeft = obTopLeft >= runTopLeft;
        boolean obTopLeftRunTopRight = obTopLeft <= runTopRight;
        boolean obTopRightRunTopLeft = obTopRight >= runTopLeft;
        boolean c4 = obTopRight <= runTopRight;
        boolean c5 = obBotLeftY >= runTopLeftY;
        boolean c6 = obBotLeftY <= runBotLeftY;
        boolean com1 = bothTopLeft && obTopLeftRunTopRight; //overlapping horizontally (the obstacle is on the right hand side)
        boolean com2 = obTopRightRunTopLeft && c4; //is the obstacle's upper right hand corner overlapping with the runner
        boolean com3 = c5 && c6; //the obstacle's bottom overlaps with the runner
        boolean d1 = com1 || com2;
        boolean d2 = com3;
        boolean isCollision = (d1) && d2; //both the x and y coordinates of runner and obstacle must overlap for there to be a collision



        if (isCollision && (obs.getMove() != getMove()))
        {
            lives--;
            updateLives();
            list.remove(indexOfObstacle);
            collision = true;
        }

        if(lives <= 0)
        {
            t.stop();
            m_timer.stop();
        }

        return collision;
    }

    /**
     * Outputs the amount of lives that the player has remaining
     */
    public void updateLives()
    {
        label.setText("Lives: " + lives);
    }

    /**
     * Animations of jumping, and rolling
     * If hops == 1, the the runner jumps
     * If tumble == 1, then the runner rolls
     */
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        x += velx; // Body x axis
        x1 += velx; //Make sure the arms are following the body
        x2 += velx;
        y += vely; // Pretty much useless
        y1 +=vely;
        y2 += vely;

        // Arms moving
        if(y1 >= y + 30)
            inc1 = -0.4;
        else
        if(y1 <= y + 10)
            inc1 = 0.4;

        inc2 = -inc1;

        //Jumping animation
        if(hops == 1)
        {
            /***********************************Change****************************/
            move = 1;
            jumpAnim(1);
            if(sizeB >= 120)
                hops = -1;
        }
        else
        if(hops == -1)
        {
            jumpAnim(-1);
            if(sizeB <= 60)
            {
                /***********************************Change****************************/
                hops = 0;
                move = 0;
            }
        }

        //Rolling animation
        if(tumble == 1)
        {
            rollAnim(0.8);
            move = 2;
            if(x1 >= x + 40)
                tumble = -1;
        }
        else
        if(tumble == -1)
        {
            rollAnim(-0.8);
            if(x1 <= x)
            {
                tumble = 0;
                y2 = y + 20 - (y1 - (y + 20));
                inc2 = -inc1;
                move = 0;
            }
        }

        // Change the Y axis
        y1 += inc1;
        y2 += inc2;

        // Boundaries
        if(x <= 60 || x== 180 || x >= 300)
            velx = 0;
        if(y <= 0 || y >= 600)
            vely = 0;
    }

    /**
     * Jumping Switch
     */
    public void jump()
    {
        hops = 1;
    }

    /**
     * Animation of jumping
     */
    public void jumpAnim(double change)
    {
        sizeB += change;
        sizeH += change;
        x1 -= change;
        x2 += change;
    }

    /**
     * Jumping Switch
     */
    public void rollAnim(double change)
    {
        x1 += change;
        x2 -= change;
        y2 = y1;
    }

    /**
     * Return the x value
     */
    public double returnX()
    {
        return x;
    }

    /**
     * Return the y value
     */
    public double returnY()
    {
        return y;
    }

    /**
     * Returns the state of action that the runner is in (jumping, rolling, or running)
     */
    public int getMove()
    {
        /***********************************Change****************************/
        if(hops == 1 && tumble == 1)
            move = 3;
        return move;
    }

    public void keyTyped(KeyEvent e)
    {}

    public void keyReleased(KeyEvent e)
    {}

}