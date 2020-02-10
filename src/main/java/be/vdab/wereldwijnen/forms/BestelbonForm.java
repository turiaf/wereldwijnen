package be.vdab.wereldwijnen.forms;

import be.vdab.wereldwijnen.domain.Bestelwijze;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BestelbonForm {
    @NotBlank
    private String naam;
    @NotBlank
    private String straat;
    @NotBlank
    private String huisNr;
    @NotBlank
    private String postCode;
    @NotBlank
    private String gemeente;
    @NotNull
    @Range(min = 0, max = 1)
    private Integer bestelwijze;

    public BestelbonForm(String naam, String straat, String huisNr, String postCode, String gemeente, Integer bestelwijze) {
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postCode = postCode;
        this.gemeente = gemeente;
        this.bestelwijze = bestelwijze;
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

    public Integer getBestelwijze() {
        return bestelwijze;
    }
}
