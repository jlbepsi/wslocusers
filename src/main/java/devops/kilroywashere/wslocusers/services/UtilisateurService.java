package devops.kilroywashere.wslocusers.services;

import jakarta.validation.constraints.NotNull;

import devops.kilroywashere.wslocusers.models.Utilisateur;


public interface UtilisateurService {
    Iterable<Utilisateur> findAll();
    Iterable<Utilisateur> findAllByVilleId(int id);
    Utilisateur findById(Integer id);

    Utilisateur add(Utilisateur utilisateur) throws Exception;
}
