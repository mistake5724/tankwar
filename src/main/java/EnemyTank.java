import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] images) {
        super(x, y, direction, enemy, images);
    }
    public void AI(){

        Random rand = new Random();

        if(rand.nextInt(20)==1){
            dirs =new boolean[4];
            if (rand.nextInt(2)==1){
                return;
            }
            getNewDirection();
        }
        if(rand.nextInt(50)==1){
            fire();
        }

    }

    public void getNewDirection() {
        Random rand = new Random();

        int dir = rand.nextInt(Direction.values().length);

        if (dir <= Direction.RIGHT.ordinal()) {
            dirs[dir] = true;
        } else {
            if (dir == Direction.LEFT_UP.ordinal()) {
                dirs[0] = true;
                dirs[2] = true;
            } else if (dir == Direction.RIGHT_UP.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            } else if (dir == Direction.LEFT_DOWN.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            } else if (dir == Direction.RIGHT_DOWN.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public boolean collision(){
        if(super.collision()){
            getNewDirection();
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g){
        AI();
        super.draw(g);
    }
}
