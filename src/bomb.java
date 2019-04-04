import javax.swing.*;

class bomb extends JLabel {

    private int x;
    private int y;

    bomb() {
        setIcon(new ImageIcon("resources/bomb.png"));
    }

    void moveToPoint(int x, int y){
        this.x = x;
        this.y = y;
        setLocation(x,y);
    }


}
