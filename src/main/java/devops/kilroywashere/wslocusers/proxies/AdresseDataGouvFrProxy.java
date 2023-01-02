package devops.kilroywashere.wslocusers.proxies;

import devops.kilroywashere.wslocusers.models.ApiAdresseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "adresseDataGouvFr", url = "https://api-adresse.data.gouv.fr")
public interface AdresseDataGouvFrProxy {
    @GetMapping("/search/?q={ville}&postcode={codepostal}&type=municipality&limit=1")
    /*@RequestMapping(
            value = "/search/?q={ville}&postcode={codepostal}&type=municipality&limit=1",
            method = RequestMethod.GET
    )*/
    ApiAdresseData getVille(@PathVariable String ville, @PathVariable String codepostal);
}
