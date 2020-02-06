package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@Access(AccessType.FIELD)
public class Bestelbonlijn {
    private int aantal;
    private BigDecimal prijs;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "wijnid")
    private Wijn wijn;

    protected Bestelbonlijn() {
    }
    public Bestelbonlijn(int aantal) {}

    public Bestelbonlijn(int aantal, BigDecimal prijs, Wijn wijn) {
        this.aantal = aantal;
        this.prijs = prijs;
        setWijn(wijn);
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Wijn getWijn() {
        return wijn;
    }

    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
    }
}
