package org.micks.champmaker.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {

    List<RegisterEntity> findByChampId(long champId);
}
