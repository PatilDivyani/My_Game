import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Table extends JPanel implements Runnable{
    int Game_Width = 1000;
    int Game_Height = (int)(1000*(0.555));
    Dimension Screen_Size = new Dimension(Game_Width,Game_Height);

    //create ball
    int Ball_Diameter = 20;
    Image image;
    Graphics graphics;
    Thread gameThread;

    Ball ball;
    Paddle paddle1;
    int Paddle_Height = 100;
    int Paddle_Width = 25;
    Paddle paddle2;

    Score score = new Score(Game_Width,Game_Height);

    //create constructor
    Table(){
        this.setPreferredSize(Screen_Size);
        gameThread = new Thread(this);
        gameThread.start();
        //use key
        this.setFocusable(true);
        this.addKeyListener(new MyKeys()); //Abstract Listener
        //call ball class
        newBall();
        newPaddle();
    }

    private void newPaddle() {
        paddle1 = new Paddle(0,(Game_Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,1);
        paddle2 = new Paddle(Game_Width-Paddle_Width,(Game_Height-Paddle_Height)/2,Paddle_Width,Paddle_Height,2);
    }

    private void newBall() {
        Random random = new Random();
        ball = new Ball(Game_Width/2,random.nextInt(Game_Height),Ball_Diameter,Ball_Diameter);
    }

    @Override
    public void paint(Graphics g) { //to print ball
        super.paint(g);
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics graphics) {
        ball.draw(graphics);
        paddle1.draw(graphics);
        paddle2.draw(graphics);
        score.draw(graphics);
    }
    public void move(){
        ball.move();
        paddle1.move();
        paddle2.move();

    }

    public void Collision(){ //Maintain ball inside boundary
        if(ball.y<=0){ //up
            ball.y_velocity = -ball.y_velocity;
        }
        if(ball.y>=Game_Height-Ball_Diameter){//down
            ball.y_velocity = -ball.y_velocity;
        }



        if(ball.intersects(paddle1) || ball.intersects(paddle2)){//left
            ball.x_velocity = -ball.x_velocity;
        }
        else if(ball.x<0 || ball.x>=Game_Width){

            if(ball.x<0){
                newBall();
                newPaddle();
                score.player2++;
            }
            if(ball.x>Game_Width){
                newBall();
                newPaddle();
                score.player1++;
            }
        }

        //for paddle1
        if(paddle1.y>=Game_Height-Paddle_Height){
            paddle1.y_Velocity = -paddle1.y_Velocity;
        }
        if(paddle1.y<0){
            paddle1.y_Velocity = -paddle1.y_Velocity;
        }
        //for paddle2
        if(paddle2.y>=Game_Height-Paddle_Height){
            paddle2.y_Velocity = -paddle2.y_Velocity;
        }
        if(paddle2.y<0){
            paddle2.y_Velocity = -paddle2.y_Velocity;
        }


    }

    @Override
    public void run() { //to run the loop
        long lastTime = System.nanoTime(); //time of system
        double amountOfTicks = 60.0; //one min = 60sec lines
        double nanoSec = 1000000000/amountOfTicks; //calc it
        double delta = 0; //change in time

        //checking diff in time
        while(true){
            long currTime = System.nanoTime();
            delta += (currTime-lastTime)/nanoSec;
            lastTime = currTime;
            if(delta>=1){ //i.e. game is on
                move();
                repaint();
                Collision();
                delta--;
            }
        }
    }


    public class MyKeys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }


}
