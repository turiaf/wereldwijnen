package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
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
    @OneToMany
    @JoinColumn(name = "soortid")
    @OrderBy("jaar")
    private Set<Wijn> wijnen;
    @Version
    private long versie;

    protected Soort() {
    }

    public Soort(String naam) {
        this.naam = naam;
        this.wijnen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public boolean addWijn(Wijn wijn) {
        return wijnen.add(wijn);
    }

    public boolean removeWijn(Wijn wijn) {
        return wijnen.remove(wijn);
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
