package org.BowlingAlley.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Player {
    private Integer playerId;
    private String name;
    private int score;
    private int totalScore;
}
