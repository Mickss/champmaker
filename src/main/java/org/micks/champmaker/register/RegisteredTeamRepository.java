package org.micks.champmaker.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredTeamRepository extends JpaRepository<RegisteredTeamEntity, Long> {

    List<RegisteredTeamEntity> findByChampId(long champId);
}
