package org.micks.champmaker.games;

import org.micks.champmaker.util.DateCommons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void createGame(GameDTO gameDTO) {
        String gameTimeString = gameDTO.getGameTime();
        LocalDate gameTime = DateCommons.parse(gameTimeString);
        GameEntity gameEntity = new GameEntity(gameDTO.getTeam1(), gameDTO.getTeam2(), gameDTO.getFinalScore(), gameTime);
        gameRepository.save(gameEntity);
    }

    public List<GameDTO> getGames() {
        List<GameEntity> gameList = gameRepository.findAll();
        return gameList.stream()
                .map(gameEntity -> {
                    LocalDate gameTime = gameEntity.getGameTime();
                    String gameTimeString = DateCommons.format(gameTime);
                    return new GameDTO(gameEntity.getTeam1(), gameEntity.getTeam2(), gameEntity.getFinalScore(), gameTimeString);
                })
                .collect(Collectors.toList());
    }
}
