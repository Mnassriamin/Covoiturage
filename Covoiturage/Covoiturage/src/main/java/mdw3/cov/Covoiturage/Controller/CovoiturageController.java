package mdw3.cov.Covoiturage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mdw3.cov.Covoiturage.Entity.*;
import mdw3.cov.Covoiturage.Repository.*;

import java.util.List;

@RestController
@RequestMapping("/api/covoiturage")
public class CovoiturageController {

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Create a new trip (Trajet)
    @PostMapping("/trajet")
    public ResponseEntity<?> proposerTrajet(@RequestBody Trajet trajet) {
        return new ResponseEntity<>(trajetRepository.save(trajet), HttpStatus.CREATED);
    }


    @GetMapping("/trajets")
    public ResponseEntity<List<Trajet>> rechercherTrajet(
            @RequestParam String lieuDepart,
            @RequestParam String lieuArrivee) {
        return new ResponseEntity<>(trajetRepository.findByLieuDepartAndLieuArrivee(lieuDepart, lieuArrivee), HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> reserverTrajet(@RequestBody Reservation reservation) {
        if (reservationRepository.existsById(reservation.getId())) {
            return new ResponseEntity<>("La réservation existe déjà", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservationRepository.save(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/reservation/{id}/confirmer")
    public ResponseEntity<Reservation> confirmerReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        reservation.setConfirme(true);
        return new ResponseEntity<>(reservationRepository.save(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> annulerReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
