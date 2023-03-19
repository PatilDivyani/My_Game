import java.awt.*;

public class Score extends Rectangle {
    int Game_Width;
    int Game_Height;

    int player1;
    int player2;

    Score(int Game_Width, int Game_Height){
        this.Game_Height = Game_Height;
        this.Game_Width = Game_Width;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("Consolas",Font.PLAIN,50));
        g.drawString(String.valueOf(player1) , Game_Width/2-80, 100);
        g.drawString(String.valueOf(player2), Game_Width/2+40,100);
    }
}
