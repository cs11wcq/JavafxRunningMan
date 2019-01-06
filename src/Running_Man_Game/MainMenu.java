package Running_Man_Game;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * This class generates the main menu, which has a play button and also details the instructions
 * for how to play the game.  When the play button is clicked, a new window is launched
 * where the game is played
 */
public class MainMenu extends JFrame
{
    public MainMenu()
    {
        initUI();
    }

    /**
     * //https://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
     * method for resizing an image
     * @param image the image to resize
     * @param width  the desired width
     * @param height the desired height
     * @return
     */
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    /**
     * Makes the GUI components
     */
    public void initUI()
    {


        //bottompanel will be red
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        //topPanel is gray

        JLabel label = new JLabel("TAKEOFF", JLabel.CENTER);
        label.setFont(new Font("HeadLong", Font.BOLD, 30));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel instructions = new JLabel("<html>Instructions: <br><br>Use arrow keys to move<br>" +
                "Use space bar to jump<br> Press b to roll. <br>Avoid obstacles.  You must JUMP over spikes. " +
                "You must ROLL under thin bar.</html>");
        instructions.setFont(new Font("HeadLong", Font.PLAIN, 20));
        instructions.setVerticalAlignment(JLabel.CENTER);

        //Link that helped me figure out how to fit an Image icon onto a button
        //https://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
        BufferedImage image = null;
        try
        {
            //  path at home:       C:\\Users\\sethy\\Documents\\Boulder.gif
            image = ImageIO.read(new File("/Users/1827129/Desktop/images.png"));
        }
        catch (IOException e) {

        }
        BufferedImage resizedImage = resize(image, 80, 40);
        //ImageIcon boulderIcon = new ImageIcon("C:\\Users\\sethy\\Documents\\Boulder.gif", "PLAY");
        //ImageIcon boulderIcon = new ImageIcon(Image resizedImage);
        JButton playButton = new JButton("Play", new ImageIcon(resizedImage));

        playButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        playButton.setHorizontalTextPosition(SwingConstants.CENTER); //centers the "play" text in middle of button
        bottomPanel.setBackground(Color.red); //sets background color to red


        playButton.addActionListener(new ActionListener()
        {

            //handle the button click
            //when user clicks the play button, the main window will disappear
            //and a new frame will be created where the user will play the game
            public void actionPerformed(ActionEvent e)
            {
                JFrame f = new JFrame();
                Container cp = f.getContentPane();
                cp.setLayout(new BorderLayout());
                //Create Runner Componenets

                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Runner runner = new Runner();

                //create a start button and stop button
                JButton startButton = new JButton("Start");
                JButton stopButton  = new JButton("Stop");

//                //... Add Listeners
//                startButton.addActionListener(new ActionListener()
//                {
//                    public void actionPerformed(ActionEvent e)
//                    {
//                        runner.setAnimation(true);
//                    }
//                });
//
//                stopButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        runner.setAnimation(false);
//                    }
//                });
                JLabel score = new JLabel("Score: 0");
                JLabel lives = new JLabel("Lives: 3");
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));


                score.setBounds(5, 5, 10, 10);
//                panel.add(startButton);    THIS PREVENTED IT FROM WORKING
//                panel.add(stopButton);     THIS PREVENTED IT FROM WORKING
                panel.add(runner);
                //panel.add(score);

                cp.add(panel, BorderLayout.CENTER);
                f.setSize(520,850);
                f.setVisible(true);
                //        f.setVisible(true);  THIS MUST BE AT THE END OTHERWISE THE key adapter
                //only works sporadically.  The runner was only moving when we pressed the arrow keys
                //sometimes because this line was here and not at the end only
                MainMenu.this.setVisible(false); //Hides the main menu page!
            }
        });

        //width, height

        bottomPanel.setPreferredSize(new Dimension(600, 1000));
        bottomPanel.add(label, BorderLayout.NORTH);
        bottomPanel.add(instructions, BorderLayout.CENTER);
        bottomPanel.add(playButton, BorderLayout.SOUTH);


//        Runner runner = new Runner();
//
//        bottomPanel.add(runner);



        //top, left, bottom, right
        bottomPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));

        this.add(bottomPanel); //add bottomPanel to the JFrame
//         pack();
//
//         setTitle("Border Example");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
    }



    public static void main(String[] args) {


    }
}
