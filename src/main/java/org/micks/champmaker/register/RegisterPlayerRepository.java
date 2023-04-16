package org.micks.champmaker.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterPlayerRepository extends JpaRepository<RegisterPlayerEntity, Long> {

    List<RegisterPlayerEntity> findByChampId(long champId);

}
