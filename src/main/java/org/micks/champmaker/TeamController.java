package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTeams(@RequestBody TeamDTO teamDto) {
        teamService.createTeam(teamDto);
    }
}
