package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "landen")
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany
    @JoinColumn(name = "landid")
    @OrderBy("naam")
    private Set<Soort> soorten;
    @Version
    private long versie;

    protected Land() {
    }
    public Land(String naam) {
        this.naam = naam;
        this.soorten = new LinkedHashSet<>();
    }

    public boolean addSoort(Soort soort) {
        return soorten.add(soort);
    }
    public boolean removeSoort(Soort soort) {
        return soorten.remove(soort);
    }

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
