import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    GameClient(){
        this.setPreferredSize(new Dimension(1024,768));
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),getCenterPosX(47),getCenterPosY(47),null);
    }

    private int getCenterPosX(int width){
        return (screenWidth-width)/2;
    }
    private int getCenterPosY(int Heidth){
        return (screenHeight-Heidth)/2;
    }
}
