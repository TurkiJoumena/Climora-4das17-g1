package com.climora.previsions.repository;

import com.climora.previsions.domain.entity.Prevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrevisionRepository extends JpaRepository<Prevision, Long> {

    Optional<Prevision> findByVilleId(Long villeId);

    boolean existsByVilleId(Long villeId);
}
