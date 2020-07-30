import com.sun.javafx.scene.traversal.Direction;

import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;

    GameClient(){
        this.setPreferredSize(new Dimension(1024,768));
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));

        init();
    }

    public void init(){
        playerTank = new Tank(getCenterPosX(47),getCenterPosY(47), Direction.DOWN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(playerTank.getImage(),getCenterPosX(47),getCenterPosY(47),null);
    }

    private int getCenterPosX(int width){
        return (screenWidth-width)/2;
    }
    private int getCenterPosY(int Heidth){
        return (screenHeight-Heidth)/2;
    }
}
