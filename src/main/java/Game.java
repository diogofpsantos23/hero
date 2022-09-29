import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen_;
    private Position position_ = new Position(39, 19);
    private Hero hero_ = new Hero(position_);
    private Arena arena_ = new Arena(80, 40, hero_);

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
        Arena.drawHero(screen_);
        this.screen_.refresh();
    }

    public boolean canHeroMove(Position position) {
        return position.get_x()+1 <= arena_.get_width() && position.get_x()-1 > -2 &&
                position.get_y()-1 > -2 && position.get_y()+1 <= arena_.get_height();
    }

    public void run() throws IOException {
        draw();
        while (true) {
            KeyStroke key = this.screen_.readInput();
            arena_.processKey(key);

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {screen_.close();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {if (canHeroMove(position_)) {hero_.moveLeft();} else {hero_.moveRight();}}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') {if (canHeroMove(position_)) {hero_.moveUp();} else {hero_.moveDown();}}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {if (canHeroMove(position_)) {hero_.moveDown();} else {hero_.moveUp();}}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {if (canHeroMove(position_)) {hero_.moveRight();} else {hero_.moveLeft();}}
            if (key.getKeyType() == KeyType.ArrowUp) {if (canHeroMove(position_)) {hero_.moveUp();} else {hero_.moveDown();}}
            if (key.getKeyType() == KeyType.ArrowDown) {if (canHeroMove(position_)) {hero_.moveDown();} else {hero_.moveUp();}}
            if (key.getKeyType() == KeyType.ArrowLeft) {if (canHeroMove(position_)) {hero_.moveLeft();} else {hero_.moveRight();}}
            if (key.getKeyType() == KeyType.ArrowRight) {if (canHeroMove(position_)) {hero_.moveRight();} else {hero_.moveLeft();}}
            if (key.getKeyType() == KeyType.EOF) {break;}

            draw();
        }
    }
}