package org.micks.champmaker.championships;

import lombok.extern.slf4j.Slf4j;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.register.RegisterService;
import org.micks.champmaker.register.RegisterTeamDTO;
import org.micks.champmaker.register.RegisteredTeamEntity;
import org.micks.champmaker.register.RegisteredTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisteredTeamRepository registeredTeamRepository;

    @Autowired
    private WebClient webClient;

    public ChampionshipDTO getChampionship(long champId) throws EntityNotFoundException {
        Optional<ChampionshipEntity> optionalChampionship = championshipRepository.findById(champId);
        if (optionalChampionship.isPresent()) {
            ChampionshipEntity championshipEntity = optionalChampionship.get();
            List<RegisterTeamDTO> registeredTeams = registerService.getRegisteredTeams(champId);
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
                    List<RegisterTeamDTO> registeredTeams = registerService.getRegisteredTeams(championshipEntity.getId());
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
        log.info("Shuffling teams for championship: {}", champId);
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

    public void schedule(long champId) {
        ArrayList<Object> testList = new ArrayList<>();
        testList.add("Group A");
        testList.add("Group B");
        ScheduleChampionshipRequest payload = new ScheduleChampionshipRequest(champId, testList);
        webClient.post()
                .uri("/schedule", champId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(payload), ScheduleChampionshipRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError((error) -> {
                    throw new IllegalStateException("Failed to send request to matches service", error);
                })
                .block();
    }
}
