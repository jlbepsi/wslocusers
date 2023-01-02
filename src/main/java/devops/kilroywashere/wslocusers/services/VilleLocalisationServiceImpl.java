package devops.kilroywashere.wslocusers.services;

import devops.kilroywashere.wslocusers.models.ApiAdresseData;
import devops.kilroywashere.wslocusers.models.Ville;
import devops.kilroywashere.wslocusers.proxies.AdresseDataGouvFrProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleLocalisationServiceImpl implements VilleLocalisationService {
    private final AdresseDataGouvFrProxy adresseDataGouvFrProxy;

    VilleLocalisationServiceImpl(AdresseDataGouvFrProxy adresseDataGouvFrProxy) {
        this.adresseDataGouvFrProxy = adresseDataGouvFrProxy;
    }
    @Override
    public Ville findById(String nom, String codepostal) {
        ApiAdresseData apiAdresseData;
        try {
            apiAdresseData = adresseDataGouvFrProxy.getVille(nom, codepostal);
        } catch (Exception exception) {
            return  null;
        }

        Ville ville = new Ville();
        ApiAdresseData.Feature feature = apiAdresseData.getFeatures().get(0);
        ApiAdresseData.Properties properties = feature.getProperties();
        ApiAdresseData.Geometry geometry = feature.getGeometry();
        List<Float> points = geometry.getCoordinates();

        ville.setId(properties.getId());
        ville.setNom(properties.getName());
        ville.setCodepostal(codepostal);
        ville.setLongitude(points.get(0));
        ville.setLatitude(points.get(1));
        return ville;
    }
}
