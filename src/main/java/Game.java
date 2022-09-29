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
    private Position position = new Position(39, 19);
    private Hero hero = new Hero(position);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(80, 40);
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
        Position.drawHero(screen_);
        this.screen_.refresh();
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    public void run() throws IOException {
        draw();
        while (true) {
            KeyStroke key = this.screen_.readInput();
            processKey(key);

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {this.screen_.close();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {moveHero(hero.moveLeft());}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') {moveHero(hero.moveUp());}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {moveHero(hero.moveDown());}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {moveHero(hero.moveRight());}
            if (key.getKeyType() == KeyType.ArrowUp) {moveHero(hero.moveUp());}
            if (key.getKeyType() == KeyType.ArrowDown) {moveHero(hero.moveDown());}
            if (key.getKeyType() == KeyType.ArrowLeft) {moveHero(hero.moveLeft());}
            if (key.getKeyType() == KeyType.ArrowRight) {moveHero(hero.moveRight());}
            if (key.getKeyType() == KeyType.EOF) {break;}

            draw();
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }
}