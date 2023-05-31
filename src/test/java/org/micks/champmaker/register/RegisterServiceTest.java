package org.micks.champmaker.register;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.micks.champmaker.championships.ChampionshipEntity;
import org.micks.champmaker.championships.ChampionshipRepository;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.exceptions.PlayerAgeNotValidException;
import org.micks.champmaker.players.PlayerDTO;
import org.micks.champmaker.players.PlayerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private PlayerService playerService;

    @Mock
    private ChampionshipRepository championshipRepository;

    @Mock
    private RegisterPlayerRepository registerPlayerRepository;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void checkAgeOfMajorityLessThanEighteen() throws EntityNotFoundException, ParseException {

        //given
        long champId = 301;

        PlayerDTO mockedPlayerDTO = new PlayerDTO(401L, 801L, "Kazio", 99L, DATE_FORMAT.parse("2005-12-16"));
        when(playerService.getPlayer(401)).thenReturn(mockedPlayerDTO);
        Optional<ChampionshipEntity> mockedChampionshipId = Optional.of(new ChampionshipEntity("PUFF", "Poznań", DATE_FORMAT.parse("2023-12-15")));
        when(championshipRepository.findById(301L)).thenReturn(mockedChampionshipId);
        RegisterPlayerDTO registerPlayerDTO = new RegisterPlayerDTO(401, 501);

        //when
        Throwable throwable = catchThrowable(() -> registerService.registerPlayer(champId, registerPlayerDTO));

        //then
        assertThat(throwable).isInstanceOf(PlayerAgeNotValidException.class);
    }

    @Test
    void checkAgeOfMajorityGreaterThanEighteen() throws EntityNotFoundException, ParseException {

        //given
        long champId = 302;

        PlayerDTO mockedPlayerDTO = new PlayerDTO(402L, 802L, "Mieszko", 98L, DATE_FORMAT.parse("1990-03-05"));
        when(playerService.getPlayer(402)).thenReturn(mockedPlayerDTO);
        Optional<ChampionshipEntity> mockedChampionshipId = Optional.of(new ChampionshipEntity("PUFF", "Poznań", DATE_FORMAT.parse("2023-12-15")));
        when(championshipRepository.findById(302L)).thenReturn(mockedChampionshipId);
        RegisterPlayerDTO registerPlayerDTO = new RegisterPlayerDTO(402, 502);

        //when
        registerService.registerPlayer(champId, registerPlayerDTO);

        //then
        // no exception occurs
    }

    @Test
    void checkAgeOfMajorityEqualThanEighteen() throws EntityNotFoundException, ParseException {

        //given
        long champId = 303;

        PlayerDTO mockedPlayerDTO = new PlayerDTO(403L, 803L, "Waldemar", 98L, DATE_FORMAT.parse("2005-10-10"));
        when(playerService.getPlayer(403)).thenReturn(mockedPlayerDTO);
        Optional<ChampionshipEntity> mockedChampionshipId = Optional.of(new ChampionshipEntity("PUFF", "Poznań", DATE_FORMAT.parse("2023-10-10")));
        when(championshipRepository.findById(303L)).thenReturn(mockedChampionshipId);
        RegisterPlayerDTO registerPlayerDTO = new RegisterPlayerDTO(403, 503);

        //when
        Throwable throwable = catchThrowable(() -> registerService.registerPlayer(champId, registerPlayerDTO));

        //then
        assertThat(throwable).isInstanceOf(PlayerAgeNotValidException.class);
    }
}
