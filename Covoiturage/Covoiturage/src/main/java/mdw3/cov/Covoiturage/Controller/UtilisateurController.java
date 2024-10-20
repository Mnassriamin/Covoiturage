package mdw3.cov.Covoiturage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mdw3.cov.Covoiturage.Repository.UtilisateurRepository;
import mdw3.cov.Covoiturage.Entity.Utilisateur;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<?> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            return new ResponseEntity<>("L'utilisateur avec cet email existe déjà", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(utilisateurRepository.save(utilisateur), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return new ResponseEntity<>(utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé")), HttpStatus.OK);
    }
}
