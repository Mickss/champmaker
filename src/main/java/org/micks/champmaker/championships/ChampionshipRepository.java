package org.micks.champmaker.championships;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipRepository extends JpaRepository<ChampionshipEntity,Long> {
}
