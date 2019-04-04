import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GamePlay extends JFrame {

    private gamePanel panel;
    private Spaceship ss;

    private String username;
    private Player player;

    final private int width = 1280;
    final private int height = 720;

    GamePlay(String username) {
        init(username);
        addItems(new GameState(width, height, ss));
    }

    GamePlay(String username, GameState gs) {
        init(username);
        addItems(gs);
    }

    private void init(String username) {
        this.username = username;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
        setTitle("Playing Chicken Invaders: " + username);

        // add more background pics
        BufferedImage[] bgs = new BufferedImage[1];
        try {
            bgs[0] = ImageIO.read(new File("resources/chicken-invaders-hd-wallpaper-0.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel = new gamePanel(bgs);
        setContentPane(panel);
        setResizable(false);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        ss = new Spaceship();
        setVisible(true);
    }

    private void addItems(GameState gs) {
        ss.setBounds(gs.getSpaceshipX(), gs.getSpaceshipY(), ss.getIcon().getIconWidth(), ss.getIcon().getIconHeight());
        panel.add(ss);
    }

    void start() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ss.setAlignmentX(e.getX());
                ss.setAlignmentY(e.getY());
                panel.add(ss);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //TODO
                // add this method
                //Fire();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void exitForm(WindowEvent e) {

    }
}
