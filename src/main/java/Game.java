import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Game {
    private Screen screen_;
    private Position position_ = new Position(0,0);
    private Hero hero_ = new Hero(position_);
    private List<Wall> walls_;
    private Arena arena_ = new Arena(150, 50, hero_, walls_);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(arena_.get_width(), arena_.get_height());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen_ = new TerminalScreen(terminal);
            this.screen_.setCursorPosition(null); // we don't need a cursor
            this.screen_.startScreen(); // screens must be started
            this.screen_.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    private void draw() throws IOException {
        this.screen_.clear();
        //fundo
        TextGraphics graphics = this.screen_.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#75e1ff"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena_.get_width(), arena_.get_height()), ' ');
        //paredes
        for (Wall wall : walls_) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffcd45"));
            graphics.fillRectangle(new TerminalPosition(wall.get_width(), wall.get_height()), new TerminalSize(2,2) , ' ');
        }
        //herói
        graphics.setBackgroundColor(TextColor.Factory.fromString("#75e1ff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position_.get_x() * 2, position_.get_y() * 2), "\\/");
        graphics.putString(new TerminalPosition(position_.get_x() * 2, position_.get_y() * 2 + 1), "/\\");
        this.screen_.refresh();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return position_.get_x() == p.get_x() && position_.get_y() == p.get_y();
    }

    public void run() throws IOException {
        this.walls_ = arena_.createWalls();
        this.position_ = new Position(37, 11);
        draw();
        while (true) {
            KeyStroke key = this.screen_.readInput();
            arena_.processKey(key);

            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')) {screen_.close();}
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'a' || key.getCharacter() == 'A')) {
                if (position_.get_x() > 1) {hero_.moveLeft();}}
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'w' || key.getCharacter() == 'W')) {
                if (position_.get_y() > 1) {hero_.moveUp();}}
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S')) {
                if (position_.get_y() < arena_.get_height()/2-2) {hero_.moveDown();}}
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'd' || key.getCharacter() == 'D')) {
                if (position_.get_x() < arena_.get_width()/2-2) {hero_.moveRight();}}
            if (key.getKeyType() == KeyType.ArrowUp) {
                if (position_.get_y() > 1) {hero_.moveUp();}}
            if (key.getKeyType() == KeyType.ArrowDown) {
                if (position_.get_y() < arena_.get_height()/2-2) {hero_.moveDown();}}
            if (key.getKeyType() == KeyType.ArrowLeft) {
                if (position_.get_x() > 1) {hero_.moveLeft();}}
            if (key.getKeyType() == KeyType.ArrowRight) {
                if (position_.get_x() < arena_.get_width()/2-2) {hero_.moveRight();}}
            if (key.getKeyType() == KeyType.EOF) {break;}

            draw();
            System.out.println("x=" + hero_.get_x()*2 + ", y=" + hero_.get_y()*2);
        }
    }
}
