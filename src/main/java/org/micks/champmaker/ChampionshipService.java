package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;

    public List<ChampionshipDTO> getChampionships() {
        List<ChampionshipEntity> championshipList = championshipRepository.findAll();
        return championshipList.stream()
                .map(championshipEntity -> new ChampionshipDTO(championshipEntity.getNameChamp(), championshipEntity.getCity(), championshipEntity.getDate()))
                .collect(Collectors.toList());
    }

    public void createChampionship(ChampionshipDTO championshipDTO) {
        ChampionshipEntity championshipEntity = new ChampionshipEntity(championshipDTO.getName(), championshipDTO.getCity(), championshipDTO.getDate());
        championshipRepository.save(championshipEntity);
    }
}
