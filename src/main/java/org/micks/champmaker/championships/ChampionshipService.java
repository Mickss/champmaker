package org.micks.champmaker.championships;

import org.micks.champmaker.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;

    public ChampionshipDTO getChampionship(long champId) throws EntityNotFoundException {
        Optional<ChampionshipEntity> optionalChampionship = championshipRepository.findById(champId);
        if (optionalChampionship.isPresent()) {
            ChampionshipEntity championshipEntity = optionalChampionship.get();
            return new ChampionshipDTO(championshipEntity.getNameChamp(), championshipEntity.getCity(), championshipEntity.getDate());
        } else {
            throw new EntityNotFoundException("Can not find Championship with Id: " + champId);
        }
    }

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
