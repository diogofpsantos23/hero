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
    public Hero hero = new Hero(39, 19);

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
        hero.drawHero(screen_);
        this.screen_.refresh();
    }

    public void run() throws IOException {
        draw();
        while (true) {
            KeyStroke key = this.screen_.readInput();
            processKey(key);

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {this.screen_.close();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {hero.moveLeft();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') {hero.moveUp();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {hero.moveDown();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {hero.moveRight();}
            if (key.getKeyType() == KeyType.ArrowUp) {hero.moveUp();}
            if (key.getKeyType() == KeyType.ArrowDown) {hero.moveDown();}
            if (key.getKeyType() == KeyType.ArrowLeft) {hero.moveLeft();}
            if (key.getKeyType() == KeyType.ArrowRight) {hero.moveRight();}
            if (key.getKeyType() == KeyType.EOF) {break;}

            draw();
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }

    private int x__ = hero.get_x();
    private int y__ = hero.get_y();
}