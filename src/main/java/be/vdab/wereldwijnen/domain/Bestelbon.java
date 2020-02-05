package be.vdab.wereldwijnen.domain;

import javax.persistence.*;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private String straat;
    private String huisNr;
    private String postCode;
    private String gemeente;
//    enum
    private Bestelwijze bestelwijze;
    @Version
    private long versie;

    protected Bestelbon() {
    }

    public Bestelbon(String naam, String straat, String huisNr, String postCode, String gemeente, Bestelwijze bestelwijze) {
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postCode = postCode;
        this.gemeente = gemeente;
        this.bestelwijze = bestelwijze;
    }

    public long getId() {
        return id;
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
}
