import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject{
    private Direction direction;
    private int speed;
    private boolean[] dirs=new boolean[4];
    private boolean enemy;

    public Tank(int x, int y, Direction direction,Image[] images) {
        this(x,y,direction,false,images);
    }
    public Tank(int x, int y, Direction direction,boolean enemy,Image[] images) {
        super(x,y,images);
        this.direction = direction;
        this.enemy=enemy;
        speed =5;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean[] getDirs(){return dirs;}

    public void determineDirection(){
        //0:上  1:下  2:左  3:右
        if(dirs[0]&&!dirs[1]&&!dirs[2]&&!dirs[3]) direction=Direction.UP;
        else if(!dirs[0]&&dirs[1]&&!dirs[2]&&!dirs[3]) direction=Direction.DOWN;
        else if(!dirs[0]&&!dirs[1]&&dirs[2]&&!dirs[3]) direction=Direction.LEFT;
        else if(!dirs[0]&&!dirs[1]&&!dirs[2]&&dirs[3]) direction=Direction.RIGHT;
        else if(dirs[0]&&!dirs[1]&&!dirs[2]&&dirs[3]) direction=Direction.RIGHT_UP;
        else if(dirs[0]&&!dirs[1]&&dirs[2]&&!dirs[3]) direction=Direction.LEFT_UP;
        else if(!dirs[0]&&dirs[1]&&!dirs[2]&&dirs[3]) direction=Direction.RIGHT_DOWN;
        else if(!dirs[0]&&dirs[1]&&dirs[2]&&!dirs[3]) direction=Direction.LEFT_DOWN;
    }

    public void move(){
        oldX=x;
        oldY=y;
        switch (direction){
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case RIGHT_UP:
                x += speed;
                y -= speed;
                break;
            case RIGHT_DOWN:
                x += speed;
                y += speed;
                break;
            case LEFT_UP:
                x -= speed;
                y -= speed;
                break;
            case LEFT_DOWN:
                x -= speed;
                y += speed;
                break;
        }

    }
    public void collision(){
        if(x<0){
            x=0;
        }else if(x>TankWar.gameClient.getWidth()-width){
            x=TankWar.gameClient.getWidth()-width;
        }
        if(y<0){
            y=0;
        }else if(y>TankWar.gameClient.getHeight()-height){
            y=TankWar.gameClient.getHeight()-height;
        }
        for(GameObject object:TankWar.gameClient.getGameObjects()){
            if(object!=this){
                if(object.getRectangle().intersects(getRectangle())){
                    x=oldX;
                    y=oldY;
                    return;
                }
            }
        }
    }
    public void draw(Graphics g){
        if (!isStop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()],x,y,null);
    }
    public boolean isStop(){
        for (boolean dir: dirs){
            if(dir){
                return false;
            }
        }
        return true;
    }
}
