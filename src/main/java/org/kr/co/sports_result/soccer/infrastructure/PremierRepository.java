package org.kr.co.sports_result.soccer.infrastructure;

import org.kr.co.sports_result.soccer.infrastructure.entity.PremierResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremierRepository extends JpaRepository<PremierResultEntity, Long> {

}
