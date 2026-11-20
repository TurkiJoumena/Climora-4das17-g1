package tn.climora.stationssources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.climora.stationssources.domain.VerificationSource;

import java.util.List;

public interface IVerificationSourceRepository extends JpaRepository<VerificationSource, Long> {

    List<VerificationSource> findTop50BySourceIdOrderByDateVerificationDesc(Long sourceId);
}
