package org.micks.champmaker.players;

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
@RequestMapping("/players")
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}")
    public PlayerDTO getPlayer(@PathVariable long playerId) throws EntityNotFoundException {
        return playerService.getPlayer(playerId);
    }

    @GetMapping
    public List<PlayerDTO> getPlayers() {
        return playerService.getPlayers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
    }
}
