package devops.kilroywashere.wslocusers.services;

import devops.kilroywashere.wslocusers.models.Ville;

public interface VilleLocalisationService {
    Ville findById(String nom, String codepostal);
}
