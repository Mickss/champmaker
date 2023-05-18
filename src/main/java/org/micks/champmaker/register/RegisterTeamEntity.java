package org.micks.champmaker.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Register")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisterTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long champId;
    private Long teamId;
    private String registrationDate;
    private String champGroup;

    public RegisterTeamEntity(Long champId, Long teamId, String registrationDate) {
        this.champId = champId;
        this.teamId = teamId;
        this.registrationDate = registrationDate;
    }
}
