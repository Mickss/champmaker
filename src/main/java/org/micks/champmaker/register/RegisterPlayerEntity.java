package org.micks.champmaker.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "register_player")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisterPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mealId;
    private Long champId;
    private Long playerId;

    public RegisterPlayerEntity(Long mealId, Long champId, Long playerId) {
        this.mealId = mealId;
        this.champId = champId;
        this.playerId = playerId;
    }
}
