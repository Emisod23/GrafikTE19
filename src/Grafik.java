import javax.swing.*;
import java.awt.*;

public class Grafik extends Canvas {
    public Grafik() {
        JFrame frame = new JFrame("A Simp Painting");
        this.setSize(800, 600);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        drawHouse(g, 100,300);
        drawTree(g, 100, 200);

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

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(0,128,0));
        int[] xs = {0+x, 10+x, 20+x};
        int[] ys = {30+y,0+y,30+y};
        g.fillPolygon(xs,ys,3);
        g.setColor(new Color(144, 95, 21));
        g.fillRect(7+x,30+y,6,10);
    }

    public static void main(String[] args) {
        Grafik painting = new Grafik();
    }
}
