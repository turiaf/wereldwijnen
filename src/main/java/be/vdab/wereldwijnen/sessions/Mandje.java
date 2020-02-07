package be.vdab.wereldwijnen.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<Long, Integer> wijnen = new HashMap<>();
    private BigDecimal totaal = BigDecimal.ZERO;

    public void addWijn(long id, int aantal) {
        if(wijnen.containsKey(id)) {
            wijnen.put(id, wijnen.get(id) + aantal);
        } else {
            wijnen.put(id, aantal);
        }
    }

    public Map<Long, Integer> getWijnen() {
        return wijnen;
    }

    public boolean isGevuld() {
        return !wijnen.isEmpty();
    }

    public void verhoogTotaal(BigDecimal prijs) {
        totaal = totaal.add(prijs);
    }

    public BigDecimal getTotaal() {
        return totaal;
    }

    public void delete() {
        wijnen.clear();
        totaal = BigDecimal.ZERO;
    }
}
