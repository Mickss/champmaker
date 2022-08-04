package org.micks.champmaker;

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
@RequestMapping("/championships")
@CrossOrigin
public class ChampionshipController {

    @Autowired
    private ChampionshipService championshipService;

    @GetMapping("/{champId}")
    public ChampionshipDTO getChampionship(@PathVariable long champId) throws EntityNotFoundException {
        return championshipService.getChampionship(champId);
    }

    @GetMapping
    public List<ChampionshipDTO> getChampionships() {
        return championshipService.getChampionships();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createChampionship(@RequestBody ChampionshipDTO championship) {
        championshipService.createChampionship(championship);
    }
}
