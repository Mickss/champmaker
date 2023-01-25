package org.micks.champmaker.hat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hats")
@CrossOrigin
public class HatController {

    @Autowired
    private HatService hatService;

    @PutMapping(value = "/{champId}/shuffle-hat-players")
    public void shuffleHatPlayers(@PathVariable long champId) {
        hatService.shuffleHatPlayers(champId);
    }
}
