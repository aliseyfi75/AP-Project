import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Spaceship extends JLabel {

    private int x;
    private int y;

    private long temperature;
    private long timeFree;

    private boolean flag = false;


    Spaceship() {
        setIcon(new ImageIcon("resources/spaceship-resized.png"));
        temperature = 0;
        timeFree = 0;
    }

    void moveToPoint(int x, int y){
        this.x = x;
        this.y = y;
        setLocation(x,y);
    }

    void fireShot(gamePanel panel, JLabel temperatureLabel) {
        timeFree = System.currentTimeMillis();
        shot s = new shot();
        s.setBounds(x + 40, y, 10, 10);
        panel.add(s);
        Timer timer = new Timer(5, e -> {
            s.moveToPoint(s.getX(), s.getY() - 1);
            if (s.getY() < 50)
                ((Timer) e.getSource()).stop();
            panel.repaint();
        });
        timer.setRepeats(true);
        timer.start();
        temperature += 5;
        temperatureLabel.setText("temperature = " + temperature);
    }
    void fireBomb(gamePanel panel) {
        bomb b = new bomb();
        b.setBounds(x+40,y,40,40);
        panel.add(b);
        Timer timer = new Timer(5, e -> {
            b.moveToPoint(b.getX() + (panel.getWidth()/2 - b.getX())/40,b.getY() + (panel.getHeight()/2 - b.getY())/40);
            if(Math.abs(b.getX() - panel.getWidth()/2) < 5 && Math.abs(b.getY() - panel.getHeight()/2) < 5 )
                ((Timer)e.getSource()).stop();
            panel.repaint();
        });
        timer.setRepeats(true);
        timer.start();
    }

    long getTemperature(){
        return temperature;
    }

    void rest(JLabel temperatureLabel){
        if(temperature<100) {
            temperature--;
            if (temperature < 0)
                temperature = 0;
            temperatureLabel.setText("temperature = " + temperature);
        }
    }

    void setTemperature(long temperature){
        this.temperature = temperature;
    }
}
