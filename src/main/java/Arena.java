import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width__;
    private int height__;
    private static Hero hero__;
    private static List<Wall> walls__;

    public Arena (int width, int height, Hero hero, List<Wall> walls) {
        this.width__ = width;
        this.height__ = height;
        hero__ = hero;
        walls__ = createWalls();
    }

    public int get_width() {return this.width__;}
    public int get_height() {return this.height__;}

    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width__; c++) {
            walls.add(new Wall(c, 0, new Position(width__, height__)));
            walls.add(new Wall(c, height__ - 2, new Position(width__, height__)));
        }
        for (int r = 1; r < height__ - 1; r++) {
            walls.add(new Wall(0, r, new Position(width__, height__)));
            walls.add(new Wall(width__ - 2, r, new Position(width__, height__)));
        }
        return walls;
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
    }
}
