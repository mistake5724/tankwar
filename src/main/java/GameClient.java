import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    private List<GameObject> gameObjects=new ArrayList<>();
    private boolean stop;

    public List<GameObject> getGameObjects(){
        return gameObjects;
    }
    public static Image[] BulletImage=new Image[8];

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));

        init();

        new Thread(()->{
            while(!stop){
                repaint();
                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addGameObject(GameObject object){
        gameObjects.add(object);
    }

    public void init(){
                Image[] brickImage={Tools.getImage("brick.png")};
        Image[] iTankImage=new Image[8];
        Image[] eTankImage=new Image[8];

        String[] subName={"U.png","D.png","L.png","R.png","RU.png","LU.png","RD.png","LD.png",};

        for (int i=0;i<iTankImage.length;i++){
            iTankImage[i]=Tools.getImage("itank"+subName[i]);
            eTankImage[i]=Tools.getImage("etank"+subName[i]);
            BulletImage[i]=Tools.getImage("missile"+subName[i]);
        }

        playerTank = new Tank(getCenterPosX(47),100, Direction.DOWN,iTankImage);
        for(int i = 0;i < 3;i++){
            for(int j = 0 ;j < 4;j++){
                gameObjects.add(new Tank(300+j*80,500+i*80,Direction.UP,true,eTankImage));
            }
        }
        Wall[] walls={
                new Wall(250,150,true,15,brickImage),
                new Wall(150,200,false,15,brickImage),
                new Wall(800,200,false,15,brickImage),
        };
        gameObjects.add(playerTank);
        Collections.addAll(gameObjects, walls);
    }


    @Override
    protected void paintComponent(Graphics g) {
        playerTank.draw(g);
        for(GameObject object: gameObjects){
            object.draw(g);
        }
        Iterator<GameObject> iterator =gameObjects.iterator();
        while (iterator.hasNext()){
            if (!(iterator.next()).alive){
                iterator.remove();
            }
        }
    }

    private int getCenterPosX(int width){
        return (screenWidth-width)/2;
    }
    private int getCenterPosY(int Heidth){
        return (screenHeight-Heidth)/2;
    }
    public void keyPressed(KeyEvent e){
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0]=true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=true;
                break;
            case KeyEvent.VK_SPACE:
                playerTank.fire();
                break;
            default:
        }
    }
    public void keyReleased(KeyEvent e){
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0]=false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=false;
                break;
            default:
        }
    }
}
