import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x_;
    private int y_;

    public Hero(int x, int y) {
        this.x_ = x;
        this.y_ = y;
    }

    public static void main(String[] args) {}

    public int get_x() {return this.x_;}
    public int get_y() {return this.y_;}

    public void moveUp() {y_--;}
    public void moveDown() {y_++;}
    public void moveLeft() {x_--;}
    public void moveRight() {x_++;}

    public void drawHero(Screen screen) {
        screen.setCharacter(x_, y_, TextCharacter.fromCharacter('X')[0]);
    }

}
