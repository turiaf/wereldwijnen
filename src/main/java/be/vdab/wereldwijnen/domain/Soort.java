package be.vdab.wereldwijnen.domain;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "soorten")
public class Soort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "soort")
    @OrderBy("jaar")
    private Set<Wijn> wijnen;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "landid")
    private Land land;
    @Version
    private long versie;

    protected Soort() {
    }

    public Soort(String naam, Land land) {
        this.naam = naam;
        this.wijnen = new LinkedHashSet<>();
        setLand(land);
    }


    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public boolean addWijn(Wijn wijn) {
        boolean toegevoegd = wijnen.add(wijn);
        Soort oudeSoort = wijn.getSoort();
        if(oudeSoort != null && oudeSoort != this) {
            oudeSoort.wijnen.remove(wijn);
        }
        if(oudeSoort != this) {
            wijn.setSoort(this);
        }
        return toegevoegd;
    }

    public Set<Wijn> getWijnen() {
        return Collections.unmodifiableSet(wijnen);
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        if (!land.getSoorten().contains(this)) {
            land.addSoort(this);
        }
        this.land = land;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Soort)) return false;
        Soort soort = (Soort) o;
        return Objects.equals(naam.toLowerCase(), soort.naam.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam.toLowerCase());
    }
}
