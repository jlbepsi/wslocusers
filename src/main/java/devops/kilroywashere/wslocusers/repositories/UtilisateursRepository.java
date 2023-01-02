package devops.kilroywashere.wslocusers.repositories;

import devops.kilroywashere.wslocusers.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateursRepository extends JpaRepository<Utilisateur, Integer> {
    public List<Utilisateur> findUtilisateurByVilleId(int id);
}