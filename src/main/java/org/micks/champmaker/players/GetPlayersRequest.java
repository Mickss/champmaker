package org.micks.champmaker.players;

import lombok.Data;

@Data
public class GetPlayersRequest {

    private Long teamId;
    private String name;
}
