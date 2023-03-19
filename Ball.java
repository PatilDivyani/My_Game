import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle { //extends to draw ball
    //co ordinates to move ball
    int x_velocity;
    int y_velocity;


    Ball(int x, int y, int width, int height){
        super(x, y, width, height); //to work over all class,this will pass the variable
        // to start in any directn
        Random random = new Random();;
        int r = random.nextInt(2);
        if(r==1){
            x_velocity = -3;
        }
        else {
            x_velocity = 3;
        }

        y_velocity = 3;
    }

    public void move(){
        x += x_velocity;
        y += y_velocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,width,height); //collect variables from super constructor
        g.drawLine(1000/2,0,1000/2,555);
       
    }
}
