package devops.kilroywashere.wslocusers.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mail", nullable = false, length = 100)
    private String mail;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    /*@ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,
            optional = true
    )
    @JoinColumn(name = "ville_id")*/
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Ville ville;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", ville=" + (ville==null ? "aucune" : ville.toString()) +
                '}';
    }
}