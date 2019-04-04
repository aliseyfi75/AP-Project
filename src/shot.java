import javax.swing.*;

class shot extends JLabel {

    private int x;
    private int y;

    shot() {
        setIcon(new ImageIcon("resources/shot.png"));
    }

    void moveToPoint(int x, int y){
        this.x = x;
        this.y = y;
        setLocation(x,y);
    }


}
