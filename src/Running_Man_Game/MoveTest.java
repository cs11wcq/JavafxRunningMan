package Running_Man_Game;


import javax.swing.JFrame;

/**
 * This is the tester class. It calls Main Menu's initUI() method which runs our game
 * @version (a version number or a date)
 */
public class MoveTest
{
    /**
     * Here is the main() method
     *
     */
    public static void main(String[] args)
    {
        MainMenu frame = new MainMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.initUI();

        //display the window
        frame.pack();
        frame.setVisible(true);
    }
}

