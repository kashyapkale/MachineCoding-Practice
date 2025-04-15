package org.BowlingAlley.models;

import lombok.Getter;
import lombok.Setter;
import org.BowlingAlley.services.GameService;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Game {
    private Integer id;
    private Integer round;
    private Integer numberOfPlayers;
    private Map<Integer, Player> playerIdtoPlayerMap;
    private Integer maxRounds;

    public Game(Integer numberOfPlayers, Integer maxRounds) {
        this.playerIdtoPlayerMap = new HashMap<Integer, Player>();
        this.round = 1;
        this.id = GameService.generateRandomNumber();
        this.numberOfPlayers = numberOfPlayers;
        this.maxRounds = maxRounds;
    }
}
