import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Position {
    private static int x_;
    private static int y_;

    public Position(int x, int y) {
        this.x_ = x;
        this.y_ = y;
    }

    public  int get_x() {return this.x_;}
    public int get_y() {return this.y_;}

    public static void drawHero(Screen screen) {
        screen.setCharacter(x_, y_, TextCharacter.fromCharacter('X')[0]);
    }
}
