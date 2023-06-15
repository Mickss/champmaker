package org.micks.champmaker.register;

import org.micks.champmaker.championships.ChampionshipEntity;
import org.micks.champmaker.championships.ChampionshipRepository;
import org.micks.champmaker.championships.ChampionshipStatus;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.exceptions.PlayerAgeNotValidException;
import org.micks.champmaker.players.PlayerDTO;
import org.micks.champmaker.players.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private RegisterPlayerRepository registerPlayerRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChampionshipRepository championshipRepository;

    public void registerTeamToChampionship(Long champId, RegisterTeamDTO registerTeamDTO) throws IllegalStateException {
        RegisterTeamEntity registerTeamEntity = new RegisterTeamEntity(champId, registerTeamDTO.getTeamId(), registerTeamDTO.getRegistrationDate());
        ChampionshipEntity championshipRepositoryById = championshipRepository.getById(champId);
        if (!championshipRepositoryById.getStatus().equals(ChampionshipStatus.REGISTRATION)) {
            throw new IllegalStateException("Wrong status of the tournament " + champId);
        }
        registerTeamEntity.setStatus(ChampionshipStatus.IN_PROGRESS);
        registerRepository.save(registerTeamEntity);
    }

    public List<Long> getRegisteredPlayers(long champId, long teamId) {
        List<RegisterPlayerEntity> registeredPlayers = registerPlayerRepository.findByChampId(champId);
        List<Long> registeredPlayersIds = registeredPlayers.stream()
                .map(RegisterPlayerEntity::getPlayerId)
                .collect(toList());
        return playerService.getPlayersIds(teamId).stream()
                .filter(registeredPlayersIds::contains)
                .collect(toList());
    }

    public List<Long> getRegisteredTeams(long champId) {
        List<RegisterTeamEntity> registerEntities = registerRepository.findByChampId(champId);
        return registerEntities.stream()
                .map(RegisterTeamEntity::getTeamId).collect(toList());
    }

    public void registerPlayer(long champId, RegisterPlayerDTO registerPlayerDTO) throws EntityNotFoundException {
        boolean validPlayerAge = validatePlayerAge(champId, registerPlayerDTO);
        if (!validPlayerAge) {
            throw new PlayerAgeNotValidException("Age must be over 18 when attending tournament " + registerPlayerDTO.getPlayerId());
        }
        RegisterPlayerEntity registerPlayerEntity = new RegisterPlayerEntity(champId, registerPlayerDTO.getPlayerId(), registerPlayerDTO.getMealId());
        registerPlayerRepository.save(registerPlayerEntity);
    }

    private boolean validatePlayerAge(long champId, RegisterPlayerDTO registerPlayerDTO) throws EntityNotFoundException {
        Long playerId = registerPlayerDTO.getPlayerId();
        PlayerDTO player = playerService.getPlayer(playerId);
        Date birthDate = new Date(player.getBirthDate().getTime());
        LocalDate playerBirthDateLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        ChampionshipEntity championshipEntity = championshipRepository.findById(champId).orElseThrow();
        Date champDate = new Date(championshipEntity.getDate().getTime());
        LocalDate champDateLocal = champDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return !playerBirthDateLocal.plusYears(18).isAfter(champDateLocal) || registerPlayerDTO.isParentalApproval();
    }
}
