package devops.kilroywashere.wslocusers.controllers;


import devops.kilroywashere.wslocusers.models.Utilisateur;
import devops.kilroywashere.wslocusers.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UtilisateursController {

    // DI par Spring Boot avec le contructeur
    private final UtilisateurService service;

    /**
     * Logging
     */
    private final Logger logger = LoggerFactory.getLogger(UtilisateursController.class);

    public UtilisateursController(UtilisateurService service) {
        this.service = service;
    }

    /** Retourne tous les utilisateurs
     *
     * @return Une liste d'objet Utilisateur
     */
    @GetMapping("/users")
    public Iterable<Utilisateur> getUtilisateurs() {
        logger.info("UtilisateursController.getUtilisateurs");
        return service.findAll();
    }

    /**
     * Retourne tous les utilisateurs d'une ville
     *
     * @param id Id de la ville
     * @return Une liste d'objet Utilisateur
     */
    @GetMapping("/users/ville/{id}")
    public Iterable<Utilisateur> getHabitationsByVilleId(@PathVariable int id) {
        return service.findAllByVilleId(id);
    }

    /** Retourne l'utilisateur identifié par id
     *
     * @return Un objet Utilisateur
     */
    @GetMapping("/users/{id}")
    public Utilisateur getUtilisateur(@PathVariable int id) {
        logger.info("UtilisateursController.getUtilisateur");
        return service.findById(id);
    }

    /** Ajoute un utilisateur
     * @implNote Les items et les options ne sont pas enregistrés par cette API
     *
     * @param utilisateur Objet Utilisateur contenant les informations à ajouter
     * @return Le status HTTP 201: Created avec l'url de la ressource créée si tout est ok
     *        Le status HTTP BAD_REQUEST sinon
     * @see Utilisateur
     */
    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@Validated @RequestBody Utilisateur utilisateur) {
        try {
            // Ajout de l'utilisateur
            Utilisateur utilisateurCreated = service.add(utilisateur);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(utilisateurCreated.getId())
                    .toUri();
            return ResponseEntity.created(location).build();

        } catch (Exception exception) {
            logger.error("UtilisateursController", exception);

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Utilisateur: %s", utilisateur.toString())
            );
        }
    }
}
