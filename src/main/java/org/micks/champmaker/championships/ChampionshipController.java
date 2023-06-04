package org.micks.champmaker.championships;

import lombok.extern.slf4j.Slf4j;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.meals.MealDTO;
import org.micks.champmaker.meals.MealService;
import org.micks.champmaker.register.RegisterPlayerDTO;
import org.micks.champmaker.register.RegisterService;
import org.micks.champmaker.register.RegisterTeamDTO;
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

import javax.validation.Valid;
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
    public void registerTeamToChampionship(@PathVariable long champId, @RequestBody RegisterTeamDTO registerTeamDTO) {
        registerService.registerTeamToChampionship(champId, registerTeamDTO);
    }

    @PutMapping(value = "{champId}/register-player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPlayerToChampionship(@PathVariable long champId, @RequestBody RegisterPlayerDTO registerPlayerDTO) throws EntityNotFoundException {
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
    public void createChampionship(@Valid @RequestBody ChampionshipDTO championship) {
        championshipService.createChampionship(championship);
    }

    @PutMapping(value = "/{champId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editChampionship(@PathVariable long champId, @Valid @RequestBody ChampionshipDTO championshipDTO) throws EntityNotFoundException {
        championshipService.editChampionship(champId, championshipDTO);
    }

    @PutMapping(value = "/{champId}/start-registration")
    public void startRegistration(@PathVariable long champId) {
        championshipService.startRegistration(champId);
    }

    @PutMapping(value = "/{champId}/shuffle-teams")
    public void shuffleTeams(@PathVariable long champId) {
        championshipService.shuffleTeams(champId);
    }

    @PostMapping(value = "/{champId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMeal(@PathVariable long champId, @RequestBody MealDTO mealDTO) {
        mealService.createMeal(champId, mealDTO);
    }

    @PutMapping(value = "/{champId}/meals/{mealId}")
    public void editMeal(@PathVariable long champId, @PathVariable long mealId, @RequestBody MealDTO mealDTO) {
        mealService.editMeal(champId, mealId, mealDTO);
    }

    @GetMapping("/{champId}/meals")
    public List<MealDTO> getMeals(@PathVariable long champId) {
        return mealService.getMeals(champId);
    }

    @GetMapping("/{champId}/meals/{mealId}")
    public MealDTO getMeal(@PathVariable long champId, @PathVariable long mealId) {
        return mealService.getMeal(champId, mealId);
    }
}
