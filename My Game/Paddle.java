import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int y_Velocity = 2;
    int id;

    Paddle(int x, int y , int Paddle_Width, int Paddle_Height,int id){
        super(x,y,Paddle_Width,Paddle_Height);
        this.id = id;


    }

    public void draw(Graphics g){
        //rightside
        if(id==1)
        g.setColor(Color.magenta);

        //left side
        if(id==2)
            g.setColor(Color.blue);

        g.fillRect(x,y,width,height);
    }

    public void move() {
        y += y_Velocity;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {

            case 1:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y_Velocity = -5;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y_Velocity = 5;
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    y_Velocity = 5;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    y_Velocity = -5;
                }

        }

    }


    public void keyReleased(KeyEvent e) {
        //to stop
        switch (id) {

            case 1:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y_Velocity = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y_Velocity = 0;
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    y_Velocity = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    y_Velocity = 0;
                }
        }
    }
    }
