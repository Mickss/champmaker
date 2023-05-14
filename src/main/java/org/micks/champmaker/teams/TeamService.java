package org.micks.champmaker.teams;

import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamDTO getTeam(long teamId) throws EntityNotFoundException {
        Optional<TeamEntity> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            TeamEntity teamEntity = optionalTeam.get();
            return new TeamDTO(teamEntity.getName());
        } else {
            throw new EntityNotFoundException("Cannot find team with Id: " + teamId);
        }
    }

    public List<TeamDTO> getTeams() {
        List<TeamEntity> teamList = teamRepository.findAll();
        return teamList.stream()
                .map(teamEntity -> new TeamDTO(teamEntity.getName()))
                .collect(Collectors.toList());
    }

    public void createTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = new TeamEntity(teamDTO.getNameTeam());
        teamRepository.save(teamEntity);
    }
}
