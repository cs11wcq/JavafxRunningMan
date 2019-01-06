package Running_Man_Game;

/**
 * Created by sethy on 5/30/2017
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by sethy on 5/30/2017.
 */
public class GameKeyAdapter extends KeyAdapter
{
    Runner runner;

    public GameKeyAdapter(Runner r)
    {
        runner = r;
    }

    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP)
        {
            if(runner.y > 0)
                up();
        }

        if(code == KeyEvent.VK_DOWN)
        {
            if(runner.y < 600)
                down();
        }

        if(code == KeyEvent.VK_LEFT)
        {
            if(runner.x > 120)
                left();
        }

        if(code == KeyEvent.VK_RIGHT)
        {
            if(runner.x < 240)
                right();
        }

        if(code == KeyEvent.VK_SPACE)
        {
            runner.jump();
        }

        if(code == KeyEvent.VK_B)
        {
            runner.tumble = 1;
        }
    }

    public void left()
    {
        runner.vely = 0;
        runner.velx = -10;
    }

    public void right()
    {
        runner.vely = 0;
        runner.velx = 10;
    }

    public void up()
    {
        runner.vely = -2;
        runner.velx = 0;
    }

    public void down()
    {
        runner.vely = 2;
        runner.velx = 0;
    }

}
