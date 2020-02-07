package be.vdab.wereldwijnen.forms;

import be.vdab.wereldwijnen.domain.Wijn;

import java.math.BigDecimal;

public class BestelLijn {
    private final Wijn wijn;
    private final int aantal;
    private final BigDecimal teBetalen;

    public BestelLijn(Wijn wijn, int aantal, BigDecimal teBetalen) {
        this.wijn = wijn;
        this.aantal = aantal;
        this.teBetalen = teBetalen;
    }

    public Wijn getWijn() {
        return wijn;
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getTeBetalen() {
        return teBetalen;
    }
}
