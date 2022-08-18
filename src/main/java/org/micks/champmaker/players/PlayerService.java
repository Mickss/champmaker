package org.micks.champmaker.players;

import org.micks.champmaker.EntityNotFoundException;
import org.micks.champmaker.teams.TeamEntity;
import org.micks.champmaker.teams.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public PlayerDTO getPlayer(long playerId) throws EntityNotFoundException {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            PlayerEntity playerEntity = optionalPlayer.get();
            return new PlayerDTO(playerEntity.getPlayerName(), playerEntity.getPlayerNumber(), playerEntity.getTeam().getId());
        } else {
            throw new EntityNotFoundException("Cannot find player with Id: " + playerId);
        }
    }

    public void createPlayer(PlayerDTO playerDTO) throws EntityNotFoundException {
        Optional<TeamEntity> team = teamRepository.findById(playerDTO.getTeamId());
        if (team.isEmpty()) {
            throw new EntityNotFoundException("Cannot find team by Id: " + playerDTO.getTeamId());
        }
        PlayerEntity playerEntity = new PlayerEntity(playerDTO.getPlayerName(), playerDTO.getPlayerNumber(), team.get());
        playerRepository.save(playerEntity);
    }

    public List<PlayerDTO> getPlayers() {
        List<PlayerEntity> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(playerEntity -> new PlayerDTO(playerEntity.getPlayerName(), playerEntity.getPlayerNumber(), playerEntity.getTeam().getId()))
                .collect(Collectors.toList());
    }
}