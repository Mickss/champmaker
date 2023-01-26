package org.micks.champmaker.hat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatTeamRepository extends JpaRepository<HatTeamEntity, Long> {
}
