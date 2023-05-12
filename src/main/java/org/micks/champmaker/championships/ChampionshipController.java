package org.micks.champmaker.championships;

import lombok.extern.slf4j.Slf4j;
import org.micks.champmaker.EntityNotFoundException;
import org.micks.champmaker.meals.MealDTO;
import org.micks.champmaker.meals.MealService;
import org.micks.champmaker.register.RegisterDTO;
import org.micks.champmaker.register.RegisterPlayerDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championships")
@CrossOrigin
@Slf4j
public class ChampionshipController {

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private MealService mealService;

    @GetMapping("/{champId}")
    public ChampionshipDTO getChampionship(@PathVariable long champId) throws EntityNotFoundException {
        return championshipService.getChampionship(champId);
    }

    @PutMapping(value = "{champId}/register-team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerTeamToChampionship(@PathVariable long champId, @RequestBody RegisterDTO registerDTO) {
        registerService.registerTeamToChampionship(champId, registerDTO);
    }

    @PutMapping(value = "{champId}/register-player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPlayerToChampionship(@PathVariable long champId, @RequestBody RegisterPlayerDTO registerPlayerDTO) {
        registerService.registerPlayer(champId, registerPlayerDTO);
    }

    @GetMapping
    public List<ChampionshipDTO> getChampionships(@RequestParam(required = false) String city) {
        log.info("Fetching championships by city: {}", city);
        return championshipService.getChampionships(city);
    }

    @GetMapping("/{champId}/registered-players")
    public List<Long> getRegisteredPlayers(@PathVariable long champId, @RequestParam long teamId) {
        return registerService.getRegisteredPlayers(champId, teamId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createChampionship(@RequestBody ChampionshipDTO championship) {
        championshipService.createChampionship(championship);
    }

    @PutMapping(value = "/{champId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editingChampionship(@PathVariable long champId, @RequestBody ChampionshipDTO championshipDTO) throws EntityNotFoundException {
        championshipService.editingChampionship(champId, championshipDTO);
    }

    @PutMapping(value = "/{champId}/shuffle-teams")
    public void shuffleTeams(@PathVariable long champId) {
        championshipService.shuffleTeams(champId);
    }

    @PostMapping(value = "/{champId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMeal(@PathVariable long champId, @RequestBody MealDTO mealDTO) {
        mealService.createMeal(champId, mealDTO);
    }

    @PutMapping(value = "/{champId}/meals")
    public void editMeals(@PathVariable long champId, @RequestBody MealDTO mealDTO) {
        mealService.editMeals(champId, mealDTO);
    }

    @GetMapping("/{champId}/meals")
    public List<MealDTO> getMeals(@PathVariable long champId) {
       return mealService.getMeals(champId);
    }
}
