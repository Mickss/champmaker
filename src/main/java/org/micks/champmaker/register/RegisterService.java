package org.micks.champmaker.register;

import org.micks.champmaker.championships.ChampionshipEntity;
import org.micks.champmaker.championships.ChampionshipRepository;
import org.micks.champmaker.championships.ChampionshipStatus;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.exceptions.PlayerAgeNotValidException;
import org.micks.champmaker.players.PlayerDTO;
import org.micks.champmaker.players.PlayerEntity;
import org.micks.champmaker.players.PlayerService;
import org.micks.champmaker.teams.TeamDTO;
import org.micks.champmaker.teams.TeamService;
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
    private RegisteredTeamRepository registeredTeamRepository;

    @Autowired
    private RegisterPlayerRepository registerPlayerRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private TeamService teamService;

    public void registerTeamToChampionship(Long champId, RegisterTeamDTO registerTeamDTO) throws IllegalStateException {
        RegisteredTeamEntity registeredTeamEntity = new RegisteredTeamEntity(champId, registerTeamDTO.getTeamId(), registerTeamDTO.getRegistrationDate());
        ChampionshipEntity championshipEntity = championshipRepository.getById(champId);
        if (!championshipEntity.getStatus().equals(ChampionshipStatus.REGISTRATION)) {
            throw new IllegalStateException("Wrong status of the tournament " + champId);
        }
        registeredTeamRepository.save(registeredTeamEntity);
    }

    public List<RegisterPlayerDTO> getRegisteredPlayers(long champId, long teamId) {
        List<RegisterPlayerEntity> registeredPlayers = registerPlayerRepository.findByChampId(champId);
        List<Long> registeredPlayersIds = registeredPlayers.stream()
                .map(RegisterPlayerEntity::getPlayerId)
                .toList();
        List<PlayerEntity> registeredPlayersIdsForTeam = playerService.getPlayersForTeam(teamId).stream()
                .filter(pe -> registeredPlayersIds.contains(pe.getId()))
                .toList();
        return registeredPlayersIdsForTeam.stream().map(playerEntity -> {
            RegisterPlayerEntity registerPlayer = registeredPlayers.stream()
                    .filter(registerPlayerEntity -> registerPlayerEntity.getPlayerId().equals(playerEntity.getId()))
                    .findFirst()
                    .orElseThrow();
            PlayerDTO playerInfo = new PlayerDTO(playerEntity.getId(), playerEntity.getTeam().getId(), playerEntity.getName(), playerEntity.getShirtNumber(), playerEntity.getBirthDate());
            return new RegisterPlayerDTO(playerEntity.getId(), registerPlayer.getMealId(), playerInfo);
        }).collect(toList());
    }

    public List<RegisterTeamDTO> getRegisteredTeams(long champId) {
        List<RegisteredTeamEntity> registerEntities = registeredTeamRepository.findByChampId(champId);
        return registerEntities.stream()
                .map(registeredTeamEntity -> {
                    TeamDTO teamDTO;
                    try {
                        teamDTO = teamService.getTeam(registeredTeamEntity.getTeamId());
                    } catch (EntityNotFoundException e) {
                        throw new IllegalStateException("Team not found exception for team ID: " + registeredTeamEntity.getTeamId(), e);
                    }
                    return new RegisterTeamDTO(registeredTeamEntity.getTeamId(), teamDTO.getName(), registeredTeamEntity.getRegistrationDate(), registeredTeamEntity.getChampGroup());
                }).collect(toList());
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
