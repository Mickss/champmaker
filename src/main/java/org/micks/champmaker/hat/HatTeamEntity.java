package org.micks.champmaker.hat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hat_team")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HatTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String playerIds;

    private String nameHatTeam;

    public HatTeamEntity(String nameHatTeam) {
        this.nameHatTeam = nameHatTeam;
    }
}
