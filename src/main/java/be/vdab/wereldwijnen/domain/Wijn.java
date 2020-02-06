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

    public long getId() {
        return id;
    }

    public int getJaar() {
        return jaar;
    }

    public int getBeoordeling() {
        return beoordeling;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getInBestelling() {
        return inBestelling;
    }

    public void verhoogBestelling(int aantal) {
        if(aantal <= 0) {
            throw new IllegalArgumentException();
        }
        inBestelling += aantal;
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
