package org.micks.champmaker.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.micks.champmaker.championships.ChampionshipStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Table(name = "registered_team")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisteredTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long champId;
    private Long teamId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    private String champGroup;

    public RegisteredTeamEntity(Long champId, Long teamId, Date registrationDate) {
        this.champId = champId;
        this.teamId = teamId;
        this.registrationDate = registrationDate;
    }
}
