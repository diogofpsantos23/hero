public class Hero {
    private final Position position__;

    public Hero(Position position) {
        this.position__ = new Position(position.get_x(), position.get_y());
    }

    public int get_x() {return position__.get_x();}
    public int get_y() {return position__.get_y();}

    public Position moveUp() {
        if (position__.get_y() < 0) {return new Position(position__.get_x(), position__.get_y() + 1);}
        if (position__.get_y() >= 0) {return new Position(position__.get_x(), position__.get_y() - 1);}
    return null;}
    public Position moveDown() {
        if (position__.get_y() < 40) {return new Position(position__.get_x(), position__.get_y() + 1);}
        if (position__.get_y() >= 40) {return new Position(position__.get_x(), position__.get_y() - 1);}
        return null;
    }
    public Position moveLeft() {
        if (position__.get_x() < 0) {return new Position(position__.get_x() + 1, position__.get_y());}
        if (position__.get_x() >= 0) {return new Position(position__.get_x() - 1, position__.get_y());}
        return null;}
    public Position moveRight() {
        if (position__.get_y() < 40) {return new Position(position__.get_x() + 1, position__.get_y());}
        if (position__.get_y() >= 40) {return new Position(position__.get_x() - 1, position__.get_y());}
        return null;
    }

    public Position setPosition(Position position) {
        return new Position(position.get_x(), position.get_y());
    }
}
