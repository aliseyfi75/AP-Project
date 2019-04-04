import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class PlayerMenu extends JFrame {

    private myPanel panel;
    private JLabel welcomeLabel;
    private JButton resumeButton;
    private JButton newGameButton;
    private JButton rankingButton;
    private JButton exitButton;
    private JButton settingButton;
    private JButton aboutUsButton;

    private String username;
    private Player player;

    final private int width = 1280;
    final private int height = 720;

    final private int horizontalMargin = 80;
    final private int verticalMargin = 20;
    final private int buttonHeight = 40;
    final private int smallButtonWidth = 80;
    final private int bigButtonWidth = 3 * smallButtonWidth / 2;

    PlayerMenu(String username) {
        this.username = username;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
        setTitle("Chicken Invaders: " + username);

        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File("resources/chicken-invaders-hd-wallpaper-1280*720.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel = new myPanel(myImage);
        setContentPane(panel);
        setResizable(false);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        loadPlayerData();
        initializeButtons();
    }

    private void loadPlayerData() {
        JSONObject playerData = null;
        try {
            playerData = (JSONObject) (new JSONParser().parse(new FileReader("data/" + username + ".data")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        player = new Player(username, playerData);
    }

    private void initializeButtons() {
        exitButton = new JButton("خروج");
        exitButton.setBounds(horizontalMargin, height - 2 * verticalMargin - buttonHeight, smallButtonWidth, buttonHeight);
        add(exitButton);
        settingButton = new JButton("تنظیمات");
        settingButton.setBounds((width - smallButtonWidth) / 2, exitButton.getY(), smallButtonWidth, buttonHeight);
        add(settingButton);
        aboutUsButton = new JButton("درباره ما");
        aboutUsButton.setBounds(width - horizontalMargin - smallButtonWidth, exitButton.getY(), smallButtonWidth, buttonHeight);
        add(aboutUsButton);
        rankingButton = new JButton("رتبه بندی");
        rankingButton.setBounds((width - bigButtonWidth) / 2, settingButton.getY() - verticalMargin - buttonHeight, bigButtonWidth, buttonHeight);
        add(rankingButton);
        newGameButton = new JButton("شروع بازی جدید");
        newGameButton.setBounds((width - bigButtonWidth) / 2, rankingButton.getY() - verticalMargin - buttonHeight, bigButtonWidth, buttonHeight);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GamePlay(username);
            }
        });
        add(newGameButton);
        resumeButton = new JButton("ادامه بازی");
        resumeButton.setBounds((width - bigButtonWidth) / 2, newGameButton.getY() - verticalMargin - buttonHeight, bigButtonWidth, buttonHeight);
        resumeButton.setEnabled(player.hasResume());
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GamePlay(username, new GameState(player));
            }
        });
        add(resumeButton);
        welcomeLabel = new JLabel("Hello, " + username + "!");
        welcomeLabel.setBounds((width - bigButtonWidth) / 2, resumeButton.getY() - verticalMargin - buttonHeight, bigButtonWidth, buttonHeight);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLabel);
        setVisible(true);
    }

    private void exitForm(WindowEvent e) {
        //TODO
    }
}

class myPanel extends JPanel {
    private Image image;

    public myPanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}