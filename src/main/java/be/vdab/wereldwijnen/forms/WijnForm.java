package be.vdab.wereldwijnen.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class WijnForm {
    @NotNull
    @Positive
    private final Long id;
    @NotNull
    @Positive
    private final Integer aantal;

    public WijnForm(Long id, Integer aantal) {
        this.id = id;
        this.aantal = aantal;
    }

    public Long getId() {
        return id;
    }

    public Integer getAantal() {
        return aantal;
    }
}
