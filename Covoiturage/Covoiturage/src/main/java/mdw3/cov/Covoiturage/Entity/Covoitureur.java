package mdw3.cov.Covoiturage.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Covoitureur extends Utilisateur {

    @OneToMany(mappedBy = "covoitureur")
    private List<Reservation> reservations;

    // Getters and Setters
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
