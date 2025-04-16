package org.BowlingAlley.services;

import org.BowlingAlley.models.Game;
import org.BowlingAlley.models.Player;
import org.BowlingAlley.utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;

public class GameService {
    private static final Scanner scanner = new Scanner(System.in);

    public static Game createGame() {
        System.out.println("Enter number of players : ");
        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        while(numberOfPlayers < 1 || numberOfPlayers > 10) {
            System.out.println("Please enter a valid number of players (1-10): ");
            numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        Game game = new Game(numberOfPlayers, Constants.MAX_ROUNDS);
        int playerIndex = 1;
        while(playerIndex <= numberOfPlayers){
            System.out.println("Enter name of player " + playerIndex + " : ");
            String name = scanner.nextLine();
            Player player = new Player(playerIndex, name, 0, 0);
            game.getPlayerIdtoPlayerMap().put(playerIndex, player);
            playerIndex++;
        }

        return game;
    }

    public static Integer generateRandomNumber(){
        Random random = new Random();
        return random.nextInt();
    }

    private static int getPlayerScore(int currentPlayerIndex){
        int playerScore = 0;
        int maxPlayerScoreInCurrentRound = 10;
        for(int round = 1; round <= 2; round++){
            System.out.println("Enter score for Player "
                    + currentPlayerIndex
                    + " in round"
                    + round +" : ");

            int currentPlayerScore = scanner.nextInt();
            while(!(currentPlayerScore > 0 && currentPlayerScore <= maxPlayerScoreInCurrentRound)){
                System.out.println("Enter valid score for Player "
                        + currentPlayerIndex
                        + " : ");
                currentPlayerScore = scanner.nextInt();
            }

            maxPlayerScoreInCurrentRound = maxPlayerScoreInCurrentRound - currentPlayerScore;
            if(maxPlayerScoreInCurrentRound == 0){
                if(round == 1) {
                    currentPlayerScore += 10;
                    playerScore += currentPlayerScore;
                    break;
                } else {
                    currentPlayerScore += 5;
                }
            }
            playerScore += currentPlayerScore;
        }

        return playerScore;
    }

    public static void playGame(Game game){
        int totalPlayers = game.getNumberOfPlayers();
        int maxRounds = game.getMaxRounds();
        while(game.getRound() <= maxRounds){
            int currentPlayerIndex = 1;
            while(currentPlayerIndex <= totalPlayers){
                int playerScore = getPlayerScore(currentPlayerIndex);

                Player player = game.getPlayerIdtoPlayerMap().get(currentPlayerIndex);
                player.setTotalScore(player.getTotalScore() + playerScore);

                currentPlayerIndex++;
            }
            game.setRound(game.getRound() + 1);
        }
    }

    public static void displayResults(Game game){
        List<Map.Entry<Integer, Player>> entries = new ArrayList<>(game.getPlayerIdtoPlayerMap().entrySet());
        entries.sort(Comparator.comparingInt(e -> e.getValue().getTotalScore()));
        int ranking = 1;
        for (Map.Entry<Integer, Player> entry : entries) {
            System.out.println("Rank : " + ranking
                    + ", Name : " + entry.getValue().getName()
                    + ", Score : " + entry.getValue().getTotalScore());
            ranking++;
        }
    }
}
