import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    //makes sure that there is enough space(750) for the snake to grow its tail
    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];

    //adds directions for snake and will turn true when snake is going to the appropriate direction
    private  boolean left = false;
    private  boolean right = false;
    private  boolean up = false;
    private boolean down = false;

    //adds images for the direction of snake
    private ImageIcon headRight;
    private ImageIcon headLeft;
    private ImageIcon headUp;
    private ImageIcon headDown;

//    length of snake
    private int lengthOfSnake = 3;

    private Timer timer;
    private int delay = 100;

    //adds tail image
    private ImageIcon tail;

    private int moves = 0;
    private int score = 0;

    private ImageIcon titleImage;

    GamePlay(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics graphics){

        if(moves == 0){
            //if game just started we set length of tail to such
            snakeXlength[0] = 100;
            snakeXlength[1] = 75;
            snakeXlength[2] = 50;
//everything set to 100 since snake is displayed horizontally
            snakeYlength[0] = 100;
            snakeYlength[1] = 100;
            snakeYlength[2] = 100;
        }

        //Displays title
        titleImage = new ImageIcon("Snake_Pics/title.png");
        //configures how image is displayed
        titleImage.paintIcon(this, graphics, 21, 5);
        //Displays gameplay border
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawRect(24, 74, 851, 577);

        //displays gameplay background
        graphics.setColor(Color.black);
        graphics.fillRect(25, 75, 850, 575);

//        initial position of the head image
//        adds snake head to the panel
        headRight = new ImageIcon("Snake_Pics/headRight.png");
//        displays snake head when facing right alongside the position of the head
        headRight.paintIcon(this, graphics, snakeXlength[0], snakeYlength[0]);

//      Defines which head image should be displayed in the future depending on the key pressed
        for(int i = 0; i < lengthOfSnake; i++){
//if right is true paint headRight img
            if(i == 0 && right) {
                headRight = new ImageIcon("Snake_Pics/headRight.png");
                headRight.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && left) {
                headLeft = new ImageIcon("Snake_Pics/headLeft.png");
                headLeft.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && up) {
                headUp = new ImageIcon("Snake_Pics/headUp.png");
                headUp.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
            }
            if(i == 0 && down) {
                headDown = new ImageIcon("Snake_Pics/headDown.png");
                headDown.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
            }

//            if i is not equal to one then the tail will be displayed
            if(i != 0){
                tail = new ImageIcon("Snake_Pics/tail.png");
                tail.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
            }
        }

        graphics.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
