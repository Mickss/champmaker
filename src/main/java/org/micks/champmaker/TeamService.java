package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamDTO> getNameTeam() {
        List<TeamEntity> teamList = teamRepository.findAll();
        return teamList.stream()
                .map(teamEntity -> new TeamDTO(teamEntity.getNameTeam()))
                .collect(Collectors.toList());
    }

    public void createTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = new TeamEntity(teamDTO.getNameTeam());
        teamRepository.save(teamEntity);
    }
}
