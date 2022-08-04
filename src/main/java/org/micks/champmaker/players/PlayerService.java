package org.micks.champmaker.players;

import org.micks.champmaker.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerDTO getPlayer(long playerId) throws EntityNotFoundException {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            PlayerEntity playerEntity = optionalPlayer.get();
            return new PlayerDTO(playerEntity.getPlayerName(), playerEntity.getPlayerNumber(), playerEntity.getPlayerTeam());
        } else {
            throw new EntityNotFoundException("Cannot find player with Id: " + playerId);
        }
    }

    public void createPlayer(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity(playerDTO.getPlayerName(), playerDTO.getPlayerNumber(), playerDTO.getPlayerTeam());
        playerRepository.save(playerEntity);
    }

    public List<PlayerDTO> getPlayers() {
        List<PlayerEntity> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(playerEntity -> new PlayerDTO(playerEntity.getPlayerName(), playerEntity.getPlayerNumber(), playerEntity.getPlayerTeam()))
                .collect(Collectors.toList());
    }
}
