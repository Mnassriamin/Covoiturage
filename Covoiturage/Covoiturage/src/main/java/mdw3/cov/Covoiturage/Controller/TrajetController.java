package mdw3.cov.Covoiturage.Controller;

import mdw3.cov.Covoiturage.Entity.Trajet;
import mdw3.cov.Covoiturage.Repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {

    @Autowired
    private TrajetRepository trajetRepository;

    @PostMapping
    public ResponseEntity<Trajet> createTrajet(@RequestBody Trajet trajet) {
        Trajet newTrajet = trajetRepository.save(trajet);
        return new ResponseEntity<>(newTrajet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long id) {
        Optional<Trajet> trajet = trajetRepository.findById(id);
        return trajet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Trajet>> getAllTrajets() {
        List<Trajet> trajets = trajetRepository.findAll();
        return new ResponseEntity<>(trajets, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody Trajet updatedTrajet) {
        Optional<Trajet> existingTrajet = trajetRepository.findById(id);
        if (existingTrajet.isPresent()) {
            Trajet trajet = existingTrajet.get();
            trajet.setLieuDepart(updatedTrajet.getLieuDepart());
            trajet.setLieuArrivee(updatedTrajet.getLieuArrivee());
            trajet.setDateHeure(updatedTrajet.getDateHeure());
            trajet.setPrix(updatedTrajet.getPrix());
            Trajet savedTrajet = trajetRepository.save(trajet);
            return new ResponseEntity<>(savedTrajet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        if (trajetRepository.existsById(id)) {
            trajetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
