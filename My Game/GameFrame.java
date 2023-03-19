import javax.swing.*;
import java.awt.*;

public class GameFrame {
    JFrame frame;
    Table table;
    GameFrame(){
       frame = new JFrame("Ping Pong Game");
       table = new Table();
       table.setBackground(Color.yellow);
       frame.add(table);
       frame.setResizable(false);
       frame.setBackground(Color.yellow);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       frame.pack(); //adjust the size i.e. will take the size of table
       frame.setLocationRelativeTo(null);
    }
}
