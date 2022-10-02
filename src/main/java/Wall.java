public class Wall {
    private int width__;
    private int height__;
    private Position position__;

    public Wall(int width, int height, Position position) {
        this.width__ = width;
        this.height__ = height;
        this.position__ = new Position(width__, height__);
    }

    public int get_width() {
        return width__;
    }
    public int get_height() {
        return height__;
    }

    public Position get_position() {
        return position__;
    }
}