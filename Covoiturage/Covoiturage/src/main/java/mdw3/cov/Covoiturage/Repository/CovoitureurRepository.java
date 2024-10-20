package mdw3.cov.Covoiturage.Repository;

import mdw3.cov.Covoiturage.Entity.Covoitureur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovoitureurRepository extends JpaRepository<Covoitureur, Long> {
}
