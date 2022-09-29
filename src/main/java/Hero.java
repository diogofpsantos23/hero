import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position_;

    public Hero(Position position) {
        this.position_ = new Position(position.get_x(), position.get_y());
    }

    public int get_x() {return this.position_.get_x();}
    public int get_y() {return this.position_.get_y();}

    public Position moveUp() {
        return new Position(position_.get_x(), position_.get_y() - 1);
    }
    public Position moveDown() {
        return new Position(position_.get_x(), position_.get_y() + 1);
    }
    public Position moveLeft() {
        return new Position(position_.get_x() - 1, position_.get_y());
    }
    public Position moveRight() {
        return new Position(position_.get_x() + 1, position_.get_y());
    }

    public Position setPosition(Position position) {
        return new Position(position.get_x(), position.get_y());
    }
}
