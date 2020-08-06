import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {
    private boolean horizontal;
    private int bricks;

    Wall(int x, int y, boolean horizontal, int bricks,Image[] image) {
        super(x, y, image);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g){
        if(horizontal){
            for (int i=0;i<bricks;i++){
                g.drawImage(image[0],x+i*(width-2),y,null);
            }
        }else {
            for(int i=0;i<bricks;i++){
                g.drawImage(image[0],x,y+i*(height-3),null);
            }
        }
    }
}
