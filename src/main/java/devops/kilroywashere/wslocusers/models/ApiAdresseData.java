package devops.kilroywashere.wslocusers.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiAdresseData {
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Getter
    @Setter
    public static class Feature {
        private Properties properties;
        private Geometry geometry;
    }
    @Getter
    @Setter
    public static class Geometry {
        private List<Float> coordinates;
    }

    @Getter
    @Setter
    public static class Properties {
        private String label;
        private String id;
        private String name;
        private String postcode;
    }
}
