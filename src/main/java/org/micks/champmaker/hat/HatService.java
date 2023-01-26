package org.micks.champmaker.hat;

import org.micks.champmaker.players.PlayerEntity;
import org.micks.champmaker.players.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HatService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private HatTeamRepository hatTeamRepository;

    /**
     * shuffle all players into hat teams
     *
     * @param champId id of hat championship
     */
    public void shuffleHatPlayers(long champId) {
        List<PlayerEntity> playersList = playerRepository.findAll();
        List<HatTeamEntity> hatTeamsList = hatTeamRepository.findAll();

        for (HatTeamEntity hatTeamEntity : hatTeamsList) {

            int numberOfPlayers = playersList.size();
            int numberOfHatTeams = hatTeamsList.size();

            Random random = new Random();
            int numberOfPlayersInTeams = numberOfPlayers / numberOfHatTeams;

            ArrayList<Integer> randomPlayersIds = new ArrayList<>();
            for (int i = 0; i < numberOfPlayersInTeams; i++) {
                int randomPlayerId = random.nextInt(numberOfPlayers);
                randomPlayersIds.add(randomPlayerId);
            }
            String hatTeamPlayersIds = randomPlayersIds
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            hatTeamEntity.setPlayerIds(hatTeamPlayersIds);
            hatTeamRepository.save(hatTeamEntity);
        }
    }
}
