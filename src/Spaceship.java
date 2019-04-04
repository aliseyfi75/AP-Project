import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Spaceship extends JLabel {

    Spaceship() {
        setIcon(new ImageIcon("resources/spaceship-resized.png"));
    }
}