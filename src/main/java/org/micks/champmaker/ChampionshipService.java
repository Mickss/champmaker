package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Transactional
    public List<ChampionshipDTO> getChampionships() {
        List<ChampionshipEntity> championshipList = championshipRepository.findAll();
        return championshipList.stream()
                .map(championshipEntity -> new ChampionshipDTO(championshipEntity.getNameChamp()))
                .collect(Collectors.toList());
    }

    public void createNewChampionship(ChampionshipDTO championshipDTO) {
        ChampionshipEntity championshipEntity = new ChampionshipEntity(championshipDTO.getName());
        championshipRepository.save(championshipEntity);
    }
}
