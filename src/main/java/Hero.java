public class Hero {
    private Position position__;

    public Hero(Position position) {
        this.position__ = new Position(position.get_x(), position.get_y());
    }

    public int get_x() {return position__.get_x();}
    public int get_y() {return position__.get_y();}

    public Position moveUp() {
        return new Position(position__.get_x(), position__.get_y() - 1);}

    public Position moveDown() {
        return new Position(position__.get_x(), position__.get_y() + 1);}

    public Position moveLeft() {
        return new Position(position__.get_x() - 1, position__.get_y());}

    public Position moveRight() {
        return new Position(position__.get_x() + 1, position__.get_y());}
}
