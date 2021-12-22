
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class X_mas extends JFrame implements Runnable{
    private MyPanel panel;
    public X_mas(){
        super("White Christmas!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        panel = new MyPanel(this);
        setContentPane(panel);
        setVisible(true);
    }



    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("C:\\Java\\2071470\\merrymerry.png");
        private Image img = icon.getImage();
        private Vector<Point> pointVector = new Vector<Point>();
        private MyRunnable runnable;
        public MyPanel(Component c){
            runnable = new MyRunnable();
            Thread th = new Thread(runnable);
            th.start();
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);    // 오버라이딩 되고 나서 this.getWidth, this.getHeight 동작.
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            g.setColor(Color.WHITE);
            makeSnow();
            for(int i=0; i<pointVector.size(); i++){
                Point p = pointVector.elementAt(i);
                g.fillOval(p.x, p.y, 10, 10);
            }
        }
        public void makeSnow(){

            if(pointVector.size() == 0){
                for(int i=pointVector.size(); i<(this.getWidth()+this.getHeight())/10; i++){
                    int x = (int)(Math.random()*this.getWidth());
                    int y = (int)(Math.random()*this.getHeight());
                    pointVector.add(new Point(x, y));
                }
            }
            else{
                for(int i=pointVector.size(); i<(this.getWidth()+this.getHeight())/10; i++){
                    int x = (int)(Math.random()*this.getWidth());
                    int y = (int)(Math.random()*this.getHeight()/3);
                    pointVector.add(new Point(x, y));
                }
            }
        }
        class MyRunnable implements Runnable{
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(20);
                        for(int i=0; i<pointVector.size(); i++){
                            Point p = pointVector.elementAt(i);
                            if(p.y+10 > panel.getHeight()){
                                pointVector.remove(i);
                            }
                            else{
                                p.setLocation(p.x, p.y+5);
                                pointVector.set(i, p);
                            }
                        }
                        repaint();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    public void run() {
        new X_mas();
    }
    public static void main(String[] args){
        Runnable playMP3 = new PlayMP3();
        Thread subThread1 = new Thread(playMP3);
        subThread1.start();

        Runnable x_mas = new X_mas();
        Thread subThread2 = new Thread(x_mas);
        subThread2.start();
    }
}
