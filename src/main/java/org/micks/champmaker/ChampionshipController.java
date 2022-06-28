package org.micks.champmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ChampionshipController {

    @Autowired
    private ChampionshipService championshipService;

    @GetMapping
    public List<ChampionshipDTO> getChampionships() {
        return championshipService.getChampionships();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createChampionship(@RequestBody ChampionshipDTO championship) {
        championshipService.createNewChampionship(championship);
    }
}
