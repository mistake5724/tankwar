import java.awt.*;

public abstract class MoveObject extends GameObject{
    protected Direction direction;
    protected int speed;
    protected boolean[] dirs=new boolean[4];
    protected boolean enemy;

    public MoveObject(int x, int y,Direction direction,boolean enemy, Image[] image) {
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
}
