package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public void createTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = new TeamEntity(teamDTO.getNameTeam());
        teamRepository.save(teamEntity);
    }
}
