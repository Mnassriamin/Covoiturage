package mdw3.cov.Covoiturage.Repository;

import mdw3.cov.Covoiturage.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
