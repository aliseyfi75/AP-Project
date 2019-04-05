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

    private BufferedImage cursorImg;
    private Cursor blankCursor;

    private Thread worker;
    private Thread rest;

    private boolean mouseFlag;

    private JLabel temperatureLabel;


    final private int width = 1280;
    final private int height = 720;

    GamePlay(String username) {
        init(username);
        addItems(new GameState(width, height, ss));
        cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
    }

    GamePlay(String username, GameState gs) {
        init(username);
        addItems(gs);
        cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
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
        temperatureLabel = new JLabel("temperature = " + ss.getTemperature());
        temperatureLabel.setBounds(100,100,200,200);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(temperatureLabel);
        setVisible(true);
    }

    private void addItems(GameState gs) {
        ss.setBounds(gs.getSpaceshipX(), gs.getSpaceshipY(), ss.getIcon().getIconWidth(), ss.getIcon().getIconHeight());
        panel.add(ss);
    }
    class Worker extends Thread {
        public void run() {
            while(true) {
                if(mouseFlag && ss.getTemperature()<100) {
                    ss.fireShot(panel, temperatureLabel);
                    try {
                        Worker.sleep(200);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if(ss.getTemperature() >= 100){
                    temperatureLabel.setText("wait");
                    ss.setTemperature(0);
                    System.out.println(ss.getTemperature());
                    try {
                        Worker.sleep(4000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if (isInterrupted())
                    break;
            }
        }
    }
    class Rest extends Thread{
        public void run(){
            while (true){
                ss.rest(temperatureLabel);
                try {
                    Worker.sleep(25);
                } catch (InterruptedException e) {
                    break;
                }
                if (isInterrupted())
                    break;
            }
        }
    }
    void start() {
        rest = new Rest();
        rest.start();
        panel.setCursor(blankCursor);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                ss.moveToPoint(e.getX()-45,e.getY()-85);
                panel.repaint();
                if (rest != null) {
                    rest.interrupt();
                    rest = null;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ss.moveToPoint(e.getX()-45,e.getY()-85);
                panel.repaint();
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (rest != null) {
                    rest.interrupt();
                    rest = null;
                }
                if(e.getButton() == MouseEvent.BUTTON1)
                    mouseFlag = true;
                else if(e.getButton() == MouseEvent.BUTTON3) {
                    ss.fireBomb(panel);
                    mouseFlag = false;
                }
                worker = new Worker();
                worker.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (worker != null) {
                    worker.interrupt();
                    worker = null;
                }
                rest = new Rest();
                rest.start();
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
