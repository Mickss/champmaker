package org.micks.champmaker.championships;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.micks.champmaker.exceptions.EntityNotFoundException;
import org.micks.champmaker.register.RegisterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChampionshipServiceTest {

    @InjectMocks
    private ChampionshipService championshipService;

    @Mock
    private ChampionshipRepository championshipRepository;

    @Mock
    private RegisterService registerService;

    @Test
    void shouldReturnChampionship() throws EntityNotFoundException {

        Date mockedDate = new Date();

        // given
        Optional<ChampionshipEntity> expectedChampionship = Optional.of(new ChampionshipEntity("Pomeranian", "Szczecin", mockedDate));
        when(championshipRepository.findById(322L)).thenReturn(expectedChampionship);

        // when
        ChampionshipDTO championship = championshipService.getChampionship(322);

        // then
        assertThat(championship.getCity()).isEqualTo("Szczecin");
        assertThat(championship.getName()).isEqualTo("Pomeranian");
        assertThat(championship.getDate()).isEqualTo(mockedDate);
    }

    @Test
    void shouldThrowExceptionWhenNoChampionshipIsFound() {

        // given
        when(championshipRepository.findById(322L)).thenReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> championshipService.getChampionship(322));

        // then
        assertThat(throwable)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Can not find Championship with Id: 322");
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void shouldFilterChampionshipsByCity() {

        // given
        List<ChampionshipEntity> mockedChampionships = List.of(
                mockChampionship(301L, "Pomernian", "Szczecin", "1999-01-11"),
                mockChampionship(302L, "WLU2022", "Warszawa", "1999-01-10"),
                mockChampionship(303L, "WLU2023", "Warszawa", "1999-01-14"),
                mockChampionship(304L, "Snejk", "Warszawa Bemowo", "1999-01-12"),
                mockChampionship(305L, "Puffiaki", "Poznań", "1999-01-18")
        );
        when(championshipRepository.findAll()).thenReturn(mockedChampionships);

        // when
        List<ChampionshipDTO> championshipsInWarsaw = championshipService.getChampionships("Warszawa");

        // then
        assertThat(championshipsInWarsaw).hasSize(3);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void shouldGetChampionshipsWithoutFiltering() {

        // given
        List<ChampionshipEntity> mockedChampionships = List.of(
                mockChampionship(301L, "Pomernian", "Szczecin", "1999-01-11"),
                mockChampionship(302L, "WLU2022", "Warszawa", "1999-01-10"),
                mockChampionship(303L, "WLU2023", "Warszawa", "1999-01-14"),
                mockChampionship(304L, "Snejk", "Warszawa Bemowo", "1999-01-12"),
                mockChampionship(305L, "Puffiaki", "Poznań", "1999-01-18")
        );
        when(championshipRepository.findAll()).thenReturn(mockedChampionships);

        // when
        List<ChampionshipDTO> championshipsInWarsaw = championshipService.getChampionships(null);

        // then
        assertThat(championshipsInWarsaw).hasSize(5);
    }

    private ChampionshipEntity mockChampionship(long id, String name, String city, String date) {
        ChampionshipEntity mockedChampionship = mock(ChampionshipEntity.class);
        when(mockedChampionship.getId()).thenReturn(id);
        when(mockedChampionship.getName()).thenReturn(name);
        when(mockedChampionship.getCity()).thenReturn(city);
        when(mockedChampionship.getDate()).thenReturn(date);
        return mockedChampionship;
    }
}
