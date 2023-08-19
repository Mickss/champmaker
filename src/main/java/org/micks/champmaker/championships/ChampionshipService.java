package org.micks.champmaker.championships;

import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.register.RegisteredTeamRepository;
import org.micks.champmaker.register.RegisterService;
import org.micks.champmaker.register.RegisteredTeamEntity;
import org.micks.champmaker.teams.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisteredTeamRepository registeredTeamRepository;

    public ChampionshipDTO getChampionship(long champId) throws EntityNotFoundException {
        Optional<ChampionshipEntity> optionalChampionship = championshipRepository.findById(champId);
        if (optionalChampionship.isPresent()) {
            ChampionshipEntity championshipEntity = optionalChampionship.get();
            List<TeamDTO> registeredTeams = registerService.getRegisteredTeams(champId);
            return new ChampionshipDTO(championshipEntity.getId(), championshipEntity.getName(), championshipEntity.getCity(), championshipEntity.getDate(), registeredTeams);
        } else {
            throw new EntityNotFoundException("Can not find Championship with Id: " + champId);
        }
    }

    public List<ChampionshipDTO> getChampionships(String city) {
        List<ChampionshipEntity> championshipList = championshipRepository.findAll();
        return championshipList.stream()
                .filter(championshipEntity -> city == null || championshipEntity.getCity().contains(city))
                .map(championshipEntity -> {
                    List<TeamDTO> registeredTeams = registerService.getRegisteredTeams(championshipEntity.getId());
                    return new ChampionshipDTO(championshipEntity.getId(), championshipEntity.getName(), championshipEntity.getCity(), championshipEntity.getDate(), registeredTeams);
                })
                .collect(Collectors.toList());
    }

    public void createChampionship(ChampionshipDTO championshipDTO) {
        ChampionshipEntity championshipEntity = new ChampionshipEntity(championshipDTO.getName(), championshipDTO.getCity(), championshipDTO.getDate(), ChampionshipStatus.DRAFT);
        championshipRepository.save(championshipEntity);
    }

    public void editChampionship(long champId, ChampionshipDTO championshipDTO) throws EntityNotFoundException {
        Optional<ChampionshipEntity> optionalChampionship = championshipRepository.findById(champId);
        if (optionalChampionship.isPresent()) {
            ChampionshipEntity championshipEntity = optionalChampionship.get();
            championshipEntity.setName(championshipDTO.getName());
            championshipEntity.setCity(championshipDTO.getCity());
            championshipEntity.setDate(championshipDTO.getDate());
            championshipRepository.save(championshipEntity);
        } else {
            throw new EntityNotFoundException("Can not find Championship with Id: " + champId);
        }
    }

    public void shuffleTeams(long champId) {
        List<RegisteredTeamEntity> registeredTeamList = registeredTeamRepository.findByChampId(champId);
        Random random = new Random();
        int counterA = 0;
        int counterB = 0;

        for (RegisteredTeamEntity registeredTeamsGroup : registeredTeamList) {
            double randomNumber = random.nextInt(100);
            String group;
            int countedTeams = registeredTeamList.size();
            int maxTeamsInGroup = countedTeams / 2;
            if (counterB >= maxTeamsInGroup || randomNumber >= 50 && counterA < maxTeamsInGroup) {
                group = "A";
                counterA++;
            } else {
                group = "B";
                counterB++;
            }
            registeredTeamsGroup.setChampGroup(group);
            registeredTeamRepository.save(registeredTeamsGroup);
        }
    }

    public void startRegistration(long champId) throws EntityNotFoundException {
        Optional<ChampionshipEntity> optionalChampionship = championshipRepository.findById(champId);
        if (optionalChampionship.isEmpty()) {
            throw new EntityNotFoundException("Cannot find champ by Id: " + champId);
        }
        ChampionshipEntity championshipEntity = optionalChampionship.get();
        if (!championshipEntity.getStatus().equals(ChampionshipStatus.DRAFT)) {
            throw new IllegalArgumentException("Wrong status of the tournament " + champId);
        }
        championshipEntity.setStatus(ChampionshipStatus.REGISTRATION);
        championshipRepository.save(championshipEntity);
    }
}
