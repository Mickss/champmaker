package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<TeamDTO> getTeams() {
        return teamService.getNameTeam();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTeams(@RequestBody TeamDTO teamDto) {
        teamService.createTeam(teamDto);
    }
}
