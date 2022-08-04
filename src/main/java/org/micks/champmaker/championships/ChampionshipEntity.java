package org.micks.champmaker.championships;

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
    private Long id;

    private String nameChamp;
    private String city;
    private String date;

    public ChampionshipEntity(String nameChamp, String city, String date) {
        this.nameChamp = nameChamp;
        this.city = city;
        this.date = date;
    }
}
