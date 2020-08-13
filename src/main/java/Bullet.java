import java.awt.*;
import java.util.List;

public class Bullet extends MoveObject {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] images) {
        super(x, y,direction,enemy,images);
        speed = 10;
    }
    @Override
    public void draw(Graphics g){
        if(!alive){
            return;
        }
        move();
        collision();

        g.drawImage(image[direction.ordinal()],x,y,null);
    }
    @Override
    public boolean collision(){

        List<GameObject> objects = TankWar.gameClient.getGameObjects();

        for(GameObject object:TankWar.gameClient.getGameObjects()){
            if (object==this || object instanceof Bullet || object instanceof Explosion){
                continue;
            }
            if ((object instanceof Tank)){
                if(((Tank)object).enemy==enemy){
                    continue;
                }
            }
                if(object.getRectangle().intersects(getRectangle())){
                    alive=false;
                    if ((object instanceof Tank)){
                        object.alive=false;
                    }
                    TankWar.gameClient.addGameObject(new Explosion(x,y,GameClient.ExplosionImage));
                    return true;
            }
        }
        return false;
    }
}
