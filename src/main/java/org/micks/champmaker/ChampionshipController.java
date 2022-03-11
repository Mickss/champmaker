package org.micks.champmaker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ChampionshipController {

    @GetMapping
    public List<String> getChampionships(){
        return List.of("Turniej Puffa", "Uwaga Turniej", "Czarna kompania turniej");
    }
}
