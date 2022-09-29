import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width__;
    private int height__;
    private static Hero hero_;

    public Arena (int width, int height, Hero hero) {
        this.width__ = width;
        this.height__ = height;
        hero_ = hero;
    }

    public int get_width() {return this.width__;}
    public int get_height() {return this.height__;}

    public static void drawHero(Screen screen) {
        screen.setCharacter(hero_.get_x(), hero_.get_y(), TextCharacter.fromCharacter('X')[0]);
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        System.out.println("x=" + hero_.get_x() + ", y=" + hero_.get_y());
    }
}
