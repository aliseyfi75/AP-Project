import javax.swing.*;
import java.awt.*;

class gamePanel extends JPanel {
    private Image[] images;
    private int currentBG;

    gamePanel(Image image) {
        images = new Image[1];
        images[0] = image;
    }

    gamePanel(Image[] images) {
        this.images = images;
        currentBG = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBG(g);

    }

    private void drawBG(Graphics g) {
        g.drawImage(images[currentBG], 0, 0, this);
    }

    void nextBG() {
        currentBG = (currentBG + 1) % images.length;
    }
}