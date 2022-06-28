package org.micks.champmaker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "championship")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChampionshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String nameChamp;

    public ChampionshipEntity(String nameChamp) {
        this.nameChamp = nameChamp;
    }
}
