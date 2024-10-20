package mdw3.cov.Covoiturage.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Conducteur extends Utilisateur {

    @OneToMany(mappedBy = "conducteur")
    private List<Trajet> trajets;

    // Getters and Setters
    public List<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }
}
