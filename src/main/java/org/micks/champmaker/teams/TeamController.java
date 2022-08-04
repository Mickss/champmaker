package org.micks.champmaker.teams;

import org.micks.champmaker.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{teamId}")
    public TeamDTO getTeam(@PathVariable long teamId) throws EntityNotFoundException {
        return teamService.getTeam(teamId);
    }

    @GetMapping
    public List<TeamDTO> getTeams() {
        return teamService.getTeams();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTeams(@RequestBody TeamDTO teamDto) {
        teamService.createTeam(teamDto);
    }
}
