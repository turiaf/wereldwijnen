package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime besteld;
    private String naam;
    private String straat;
    private String huisNr;
    private String postCode;
    private String gemeente;
//    enum
    private Bestelwijze bestelwijze;
//    @OneToMany
//    @JoinColumn(name = "bonid")
    @ElementCollection
    @CollectionTable(name = "bestelbonlijnen",
            joinColumns = @JoinColumn(name = "bonid"))
    private Set<Bestelbonlijn> bestelbonlijnen;
    @Version
    private long versie;

    protected Bestelbon() {
    }

    public Bestelbon(LocalDateTime besteld ,String naam, String straat, String huisNr, String postCode, String gemeente, Bestelwijze bestelwijze) {
        this.besteld = besteld;
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postCode = postCode;
        this.gemeente = gemeente;
        this.bestelwijze = bestelwijze;
        this.bestelbonlijnen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getBesteld() {
        return besteld;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public Bestelwijze getBestelwijze() {
        return bestelwijze;
    }

    public boolean addBonLijn(Bestelbonlijn bestelbonlijn) {
        return bestelbonlijnen.add(bestelbonlijn);
    }

    public boolean removeBonLijn(Bestelbonlijn bestelbonlijn) {
        return bestelbonlijnen.remove(bestelbonlijn);
    }

    public Set<Bestelbonlijn> getBestelbonlijnen() {
        return Collections.unmodifiableSet(bestelbonlijnen);
    }
}
