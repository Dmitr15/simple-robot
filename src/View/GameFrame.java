package View;

import Model.gamefield.GameField;
import Model.seeders.SimpleSeeder;

import javax.swing.*;

// Окно игры
public class GameFrame extends JFrame {

    public GameFrame() {

        GameField field = new GameField(3, 4, new SimpleSeeder() );
        GameFieldView mainBox = new GameFieldView( field );

        setContentPane(mainBox);
        pack();
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
