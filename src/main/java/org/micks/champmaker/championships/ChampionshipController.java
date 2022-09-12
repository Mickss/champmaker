package org.micks.champmaker.championships;

import org.micks.champmaker.EntityNotFoundException;
import org.micks.champmaker.register.RegisterDTO;
import org.micks.champmaker.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private RegisterService registerService;

    @GetMapping("/{champId}")
    public ChampionshipDTO getChampionship(@PathVariable long champId) throws EntityNotFoundException {
        return championshipService.getChampionship(champId);
    }

    @PutMapping(value = "/{champId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerTeamToChampionship(@PathVariable long champId, @RequestBody RegisterDTO registerDTO) {
        registerService.registerTeamToChampionship(champId, registerDTO);
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
