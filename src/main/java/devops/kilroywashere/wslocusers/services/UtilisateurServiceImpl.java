package devops.kilroywashere.wslocusers.services;

import devops.kilroywashere.wslocusers.models.Utilisateur;
import devops.kilroywashere.wslocusers.models.Ville;
import devops.kilroywashere.wslocusers.repositories.UtilisateursRepository;
import devops.kilroywashere.wslocusers.repositories.VilleRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateursRepository repository;
    private final VilleRepository villeRepository;
    private final VilleLocalisationService villeLocalisationService;

    public UtilisateurServiceImpl(
            UtilisateursRepository repository,
            VilleRepository villeRepository,
            VilleLocalisationService villeLocalisationService
    ) {
        this.repository = repository;
        this.villeRepository = villeRepository;
        this.villeLocalisationService = villeLocalisationService;
    }

    @Override
    public Iterable<Utilisateur> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Utilisateur> findAllByVilleId(@NotNull int id) {
        return repository.findUtilisateurByVilleId(id);
    }

    @Override
    public Utilisateur findById(@NotNull Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur add(Utilisateur utilisateur) throws Exception {
        // Vérification de la ville
        Ville ville = utilisateur.getVille();
        if (ville == null) {
            throw new Exception("La ville est vide");
        }
        Ville villeGPSFound = villeLocalisationService.findById(ville.getNom(), ville.getCodepostal());
        if (villeGPSFound == null) {
            throw new Exception("La ville n'existe pas");
        }
        if (! villeGPSFound.getNom().equals(ville.getNom())){
            throw new Exception("Le nom de la ville est incorrect");
        }
        // Copie des points GPS (pour être sûr)
        ville.setLatitude(villeGPSFound.getLatitude());
        ville.setLongitude(villeGPSFound.getLongitude());

        // Recherche de la ville
        Ville villeData = villeRepository.findById(ville.getId()).orElse(null);
        if (villeData == null) {
            // Ajout de la ville
            villeRepository.save(ville);
        }

        // Ajout de l'utilisateur
        return repository.save(utilisateur);
    }
}
