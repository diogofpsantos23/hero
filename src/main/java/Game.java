import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        this.screen.refresh();
    }

    private void setScreen() {
        try {
            TerminalSize terminalSize = new TerminalSize(80, 40);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen(); // screens must be started
            this.screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        setScreen();
        draw();
        while (true) {
            KeyStroke key = this.screen.readInput();
            processKey(key);

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {screen.close();}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {x--;}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') {y--;}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {y++;}
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {x++;}
            if (key.getKeyType() == KeyType.ArrowUp) {y--;}
            if (key.getKeyType() == KeyType.ArrowDown) {y++;}
            if (key.getKeyType() == KeyType.ArrowLeft) {x--;}
            if (key.getKeyType() == KeyType.ArrowRight) {x++;}
            if (key.getKeyType() == KeyType.EOF) {break;}

            draw();
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }

    private int x = 10;
    private int y = 10;
}