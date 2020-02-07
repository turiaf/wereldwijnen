package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "landen")
@NamedEntityGraph(name = Land.MET_SOORTEN,
        attributeNodes = @NamedAttributeNode("soorten"))
//@NamedEntityGraph(name = "Land.metSoortenEnWijn",
//        attributeNodes = @NamedAttributeNode(value = "soorten", subgraph = "metWijn"),
//        subgraphs = @NamedSubgraph(name = "metWijn",
//                attributeNodes = @NamedAttributeNode(value = "wijnen") ))
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "land")
    @OrderBy("naam")
    private Set<Soort> soorten = new LinkedHashSet<>();
    @Version
    private long versie;
    public static final String MET_SOORTEN = "Land.metSoorten";

    protected Land() {
    }

    public Land(String naam) {
        this.naam = naam;
//        this.soorten = new LinkedHashSet<>();
    }

    public boolean addSoort(Soort soort) {
        boolean toevoegen = soorten.add(soort);
        Land oudeLand = soort.getLand();
        if(oudeLand != null && oudeLand != this) {
            oudeLand.soorten.remove(soort);
        }
        if(oudeLand != this) {
            soort.setLand(this);
        }
        return soorten.add(soort);
    }

//    public boolean removeSoort(Soort soort) {
//        return soorten.remove(soort);
//    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Soort> getSoorten() {
        return Collections.unmodifiableSet(soorten);
    }

}
