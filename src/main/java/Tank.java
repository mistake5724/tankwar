import java.awt.*;

public class Tank extends MoveObject {

    protected boolean[] dirs=new boolean[4];

    public Tank(int x, int y, Direction direction, Image[] images) {
        this(x,y,direction,false,images);
    }
    public Tank(int x, int y, Direction direction, boolean enemy, Image[] images) {
        super(x, y,direction,enemy,images);
        speed =5;
    }

    public boolean[] getDirs(){return dirs;}

    public void determineDirection(){
        //0:上  1:下  2:左  3:右
        if(dirs[0]&&!dirs[1]&&!dirs[2]&&!dirs[3]) direction= Direction.UP;
        else if(!dirs[0]&&dirs[1]&&!dirs[2]&&!dirs[3]) direction= Direction.DOWN;
        else if(!dirs[0]&&!dirs[1]&&dirs[2]&&!dirs[3]) direction= Direction.LEFT;
        else if(!dirs[0]&&!dirs[1]&&!dirs[2]&&dirs[3]) direction= Direction.RIGHT;
        else if(dirs[0]&&!dirs[1]&&!dirs[2]&&dirs[3]) direction= Direction.RIGHT_UP;
        else if(dirs[0]&&!dirs[1]&&dirs[2]&&!dirs[3]) direction= Direction.LEFT_UP;
        else if(!dirs[0]&&dirs[1]&&!dirs[2]&&dirs[3]) direction= Direction.RIGHT_DOWN;
        else if(!dirs[0]&&dirs[1]&&dirs[2]&&!dirs[3]) direction= Direction.LEFT_DOWN;
    }



    @Override
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
    public void fire(){
                        TankWar.gameClient.addGameObject(
                        new Bullet(x+width/2-GameClient.BulletImage[0].getWidth(null)/2,
                                y+height/2-GameClient.BulletImage[0].getHeight(null)/2,
                                direction,enemy,GameClient.BulletImage));
    }
}
