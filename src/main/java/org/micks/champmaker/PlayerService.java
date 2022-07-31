package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void createPlayer(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity(playerDTO.getPlayerName(), playerDTO.getPlayerNumber(), playerDTO.getPlayerTeam());
        playerRepository.save(playerEntity);
    }
}
