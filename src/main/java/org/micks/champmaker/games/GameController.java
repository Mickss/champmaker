package org.micks.champmaker.games;

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
@RequestMapping("/games")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@RequestBody GameDTO gameDTO) {
        gameService.createGame(gameDTO);
    }

    @GetMapping
    public List<GameDTO> getGame() {
        return gameService.getGames();
    }
}
