import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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

    //fruit parameters
    private int[] fruitXpos = {25, 50, 75, 125, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
    425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] fruitYpos = {75, 125, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
            425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private ImageIcon fruitImage;

    private Random random = new Random();

    private int xpos = random.nextInt(34);

    private int ypos = random.nextInt(23);

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

            fruitImage = new ImageIcon("Snake_Pics/fruit.png");

            if(fruitXpos[xpos] == snakeXlength[0] && fruitYpos[ypos] == snakeYlength[0]){
                score = score + 5;
                lengthOfSnake++;
                xpos = random.nextInt(34);
                ypos = random.nextInt(23);
            }

            fruitImage.paintIcon(this, graphics, fruitXpos[xpos], fruitXpos[ypos]);
        }

        graphics.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //adds event listener
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if(!left){
                right = true;
            }else{
                right = false;
                left = true;

            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if(!right){
                left = true;
            }else{
                left = false;
                right = true;

            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if(!down){
                up = true;
            }else{
                up = false;
                down = true;

            }
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;

            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    adds snake movement
    @Override
    public void actionPerformed(ActionEvent e) {

        timer.restart();
        if(right){
            for (int n = lengthOfSnake - 1; n >= 0 ; n--){
                snakeYlength[n + 1] = snakeYlength[n];
            }
            for(int n = lengthOfSnake; n >= 0; n--){
                if(n == 0){
//                    25 represents the pixels you'd like the snake to move
                    snakeXlength[n] = snakeXlength[n]+25;
                }else{
                    snakeXlength[n] = snakeXlength[n - 1];
                }
//                if snake touches wall make the sake appear on the opposite border
                if(snakeXlength[n] > 850){
                    snakeXlength[n] = 25;
                }
            }
            repaint();
        }

        if(left){
            for (int n = lengthOfSnake - 1; n >= 0 ; n--){
                snakeYlength[n + 1] = snakeYlength[n];
            }
            for(int n = lengthOfSnake; n >= 0; n--){
                if(n == 0){
//                    25 represents the pixels you'd like the snake to move
                    snakeXlength[n] = snakeXlength[n]-25;
                }else{
                    snakeXlength[n] = snakeXlength[n - 1];
                }
//                if snake touches wall make the sake appear on the opposite border
                if(snakeXlength[n] < 25){
                    snakeXlength[n] = 850;
                }
            }
            repaint();
        }

        if(up){
            for (int n = lengthOfSnake - 1; n >= 0 ; n--){
//                avoids changing the horizontal x-axis of snake since snake is moving vertically
                snakeXlength[n + 1] = snakeXlength[n];
            }
            for(int n = lengthOfSnake; n >= 0; n--){
                if(n == 0){
//                    25 represents the pixels you'd like the snake to move
                    snakeYlength[n] = snakeYlength[n]-25;
                }else{
                    snakeYlength[n] = snakeYlength[n - 1];
                }
//                if snake touches wall make the sake appear on the opposite border
                if(snakeYlength[n] < 75){
                    snakeYlength[n] = 625;
                }
            }
            repaint();
        }

        if(down){
            for (int n = lengthOfSnake - 1; n >= 0 ; n--){
//                avoids changing the horizontal x-axis of snake since snake is moving vertically
                snakeXlength[n + 1] = snakeXlength[n];
            }
            for(int n = lengthOfSnake; n >= 0; n--){
                if(n == 0){
//                    25 represents the pixels you'd like the snake to move
                    snakeYlength[n] = snakeYlength[n]+25;
                }else{
                    snakeYlength[n] = snakeYlength[n - 1];
                }
//                if snake touches wall make the sake appear on the opposite border
                if(snakeYlength[n] > 625){
                    snakeYlength[n] = 75;
                }
            }
            repaint();
        }


    }
}