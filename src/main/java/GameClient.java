import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    private List<Tank> enemyTank=new ArrayList<>();
    private boolean stop;

    GameClient(){
        this.setPreferredSize(new Dimension(1024,768));
    }

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

    public void init(){

        playerTank = new Tank(getCenterPosX(47),getCenterPosY(47), Direction.DOWN);
        for(int i = 0;i < 3;i++){
            for(int j = 0 ;j < 4;j++){
                enemyTank.add(new Tank(300+j*80,500+i*80,Direction.UP,true));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        playerTank.draw(g);
        for(Tank tank: enemyTank){
            tank.draw(g);
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
