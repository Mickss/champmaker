package org.micks.champmaker.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "registered_player")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisterPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long champId;
    private Long playerId;
    private Long mealId;

    public RegisterPlayerEntity(Long champId, Long playerId, Long mealId) {
        this.champId = champId;
        this.playerId = playerId;
        this.mealId = mealId;
    }
}
