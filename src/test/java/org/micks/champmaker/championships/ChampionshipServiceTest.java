package org.micks.champmaker.championships;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.micks.champmaker.EntityNotFoundException;
import org.micks.champmaker.register.RegisterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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

        // given
        Optional<ChampionshipEntity> expectedChampionship = Optional.of(new ChampionshipEntity("Pomeranian", "Szczecin", "15 August"));
        when(championshipRepository.findById(322L)).thenReturn(expectedChampionship);

        // when
        ChampionshipDTO championship = championshipService.getChampionship(322);

        // then
        assertThat(championship.getCity()).isEqualTo("Szczecin");
        assertThat(championship.getName()).isEqualTo("Pomeranian");
        assertThat(championship.getDate()).isEqualTo("15 August");
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
}
