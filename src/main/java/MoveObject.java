import java.awt.*;

public abstract class MoveObject extends GameObject{
    protected Direction direction;
    protected int speed;
    protected boolean enemy;

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

    public MoveObject(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        this.enemy=enemy;

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

            move();
            collision();
        }

    }

