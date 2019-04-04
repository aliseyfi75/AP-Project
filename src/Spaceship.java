import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
}