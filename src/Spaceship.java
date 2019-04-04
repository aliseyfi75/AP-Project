import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Spaceship extends JLabel {

    private int x;
    private int y;

    Spaceship() {
        setIcon(new ImageIcon("resources/spaceship-resized.png"));
    }

    void moveToPoint(int x, int y){
        this.x = x;
        this.y = y;
        setLocation(x,y);
    }

    void fire(gamePanel panel) {
        shot s = new shot();
        s.setBounds(x+40,y,10,10);
        panel.add(s);
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.moveToPoint(s.getX(),s.getY()-1);
                if(s.getY() < 50)
                    ((Timer)e.getSource()).stop();
                panel.repaint();
            }
        });
        timer.setRepeats(true);
        timer.start();



    }
}