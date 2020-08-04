import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {
    private boolean horizontal;
    private int bricks;

    Wall(int x, int y, boolean horizontal, int bricks,Image image) {
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g){
        if(horizontal){
            for (int i=0;i<bricks;i++){
                g.drawImage(image,x+i*(image.getWidth(null)-2),y,null);
            }
        }else {
            for(int i=0;i<bricks;i++){
                g.drawImage(image,x,y+i*(image.getHeight(null)-3),null);
            }
        }
    }
}
