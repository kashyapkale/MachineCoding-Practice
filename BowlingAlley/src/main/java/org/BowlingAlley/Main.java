package org.BowlingAlley;

import org.BowlingAlley.models.Game;
import org.BowlingAlley.services.GameService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bowling Alley!");
        Game game = GameService.createGame();
        GameService.playGame(game);
        GameService.displayResults(game);
    }
}