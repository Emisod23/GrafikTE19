import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Grafik extends Canvas implements Runnable{
    private int width = 800;
    private int height = 600;

    private Thread thread;
    int fps = 100;
    private boolean isRunning;

    private BufferStrategy bs;
    private BufferedImage image;

    private int notex, notey;
    private int noteVX, noteVY;


    public Grafik() {
        JFrame frame = new JFrame("A simple painting");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        notex = 300;
        notey = -500;
        noteVX = 1;
        noteVY = 0;

    }

    public void update() {
        notey += noteVY;
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
        drawnote(g, notex, notey);
        drawcatcher(g, 300, 450);
        g.dispose();
        bs.show();
    }

    private void drawnote(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(5+x,30+y,200,6);
    }

    private void drawcatcher(Graphics g, int x, int y) {
        g.setColor(new Color(255, 0, 0, 87));
        g.fillRect(5+x,30+y,200,20);
        g.fillRect(5+x,110+y,200,20);
        g.setColor(new Color(255, 255, 0, 87));
        g.fillRect(5+x,50+y,200,20);
        g.fillRect(5+x,90+y,200,20);
        g.setColor(new Color(0, 255, 0, 87));
        g.fillRect(5+x,70+y,200,20);
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
            if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                noteVY *= -1;
                if (notey > 453 && notey < 467) {
                    System.out.println("no");
                }
                if (notey > 466 && notey < 487) {
                    System.out.println("yes");
                }
                if (notey > 486 && notey < 507) {
                    System.out.println("YARRO");
                }
                if (notey > 506 && notey < 527) {
                    System.out.println("yes");
                }
                if (notey > 526 && notey < 547) {
                    System.out.println("no");
                }
                else {
                    System.out.println("cringe");
                }
            }
            if (keyEvent.getKeyChar() == 'p') {
                noteVY = 3;
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
