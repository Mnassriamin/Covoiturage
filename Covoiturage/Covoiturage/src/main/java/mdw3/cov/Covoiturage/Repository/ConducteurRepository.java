package mdw3.cov.Covoiturage.Repository;

import mdw3.cov.Covoiturage.Entity.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
}
