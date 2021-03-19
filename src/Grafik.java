import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * This is a class
 * Created 2021-03-19
 *
 * @author Magnus Silverdal
 */
public class Grafik extends Canvas implements Runnable{
    private int width = 800;
    private int height = 600;

    private Thread thread;
    int fps = 30;
    private boolean isRunning;

    private BufferStrategy bs;
    private BufferedImage image;

    private int houseX, houseY;
    private int houseVX, houseVY;

    private int treeX, treeY;

    public Grafik() {
        JFrame frame = new JFrame("A simple painting");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        houseX = 300;
        houseY = 150;
        houseVX = 1;
        houseVY = 0;

        treeX = 200;
        treeY = 200;
    }

    public void update() {
        houseX += houseVX;
        if (houseX > width){
            houseVX = -1;
        }
        if (houseX < 0 ) {
            houseVX = 1;
        }
    }

    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        update();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        drawHouse(g, houseX,houseY);
        drawTree(g, treeX,treeY);
        drawTree(g, 100,200);
        drawTree(g, 110,200);
        drawTree(g, 120,200);
        drawTree(g, 130,200);
        drawTree(g, 140,200);
        drawTree(g, 150,200);
        g.dispose();
        bs.show();
    }

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(0,128,0));
        int[] xs = {0+x, 10+x, 20+x};
        int[] ys = {30+y,0+y,30+y};
        g.fillPolygon(xs,ys,3);
        g.setColor(new Color(200,128,30));
        g.fillRect(7+x,30+y,6,10);
    }

    private void drawHouse(Graphics g, int x, int y) {
        g.setColor(new Color(247, 255, 0));
        g.fillRect(5+x,28+y,90,80);
        g.setColor(new Color(0, 255, 255));
        g.fillRect(x+20,y+43,20,20);
        g.fillRect(x+60,y+43,20,20);
        g.setColor(new Color(144, 95, 21));
        int[] xs = {x,50+x,100+x};
        int[] ys = {30+y,y,30+y};
        g.fillPolygon(xs,ys,3);
    }

    public static void main(String[] args) {
        Grafik painting = new Grafik();
        painting.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1000.0/fps;
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT) {
                update();
                draw();
                lastTime = now;
            }

        }
        stop();
    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                treeX -= 5;
            }
            if (keyEvent.getKeyChar() == 'd') {
                treeX += 5;
            }
            if (keyEvent.getKeyChar() == 'w') {
                treeY -= 5;
            }
            if (keyEvent.getKeyChar() == 's') {
                treeY += 5;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    private class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    private class MML implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }

}
