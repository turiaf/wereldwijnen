package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "wijnen")
public class Wijn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int jaar;
    private int beoordeling;
    private BigDecimal prijs;
    private long inBestelling;
    private long versie;

    protected Wijn() {
    }

    public Wijn(int jaar, int beoordeling, BigDecimal prijs, long inBestelling) {
        this.jaar = jaar;
        this.beoordeling = beoordeling;
        this.prijs = prijs;
        this.inBestelling = inBestelling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wijn)) return false;
        Wijn wijn = (Wijn) o;
        return jaar == wijn.jaar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jaar);
    }
}
