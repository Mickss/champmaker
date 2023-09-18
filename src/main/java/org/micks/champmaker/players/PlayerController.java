package org.micks.champmaker.players;

import lombok.extern.slf4j.Slf4j;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin
@Slf4j
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}")
    public PlayerDTO getPlayer(@PathVariable long playerId) throws EntityNotFoundException {
        return playerService.getPlayer(playerId);
    }

    @GetMapping
    public List<PlayerDTO> getPlayers(@RequestParam(required = false) Long teamId, @RequestParam(required = false) String name) {
        return playerService.getPlayers(teamId, name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPlayer(@Valid @RequestBody PlayerDTO playerDTO) throws EntityNotFoundException {
        playerService.createPlayer(playerDTO);
    }
}
