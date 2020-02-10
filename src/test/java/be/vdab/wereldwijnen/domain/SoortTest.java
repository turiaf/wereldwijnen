package be.vdab.wereldwijnen.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoortTest {
    private Land land1, land2;
    private Soort soort1, soort2;

    @BeforeEach
    void beforeEach() {
        land1 = new Land("test");
        soort1 = new Soort("test", land1);
        soort2 = new Soort("test", land1);
        land2 = new Land("test");
    }

    @Test
    void meerdereSoortenKunnenTotDezelfdeLandBehoren() {
        assertThat(land1.getSoorten()).containsOnly(soort1, soort2);
    }

    @Test
    void soort1KomtVoorInLand1() {
        assertThat(soort1.getLand()).isEqualTo(land1);
        assertThat(land1.getSoorten()).contains(soort1);
    }

    @Test
    void soort1VerhuisNaarLand2() {
        soort1.setLand(land2);
        assertThat(soort1.getLand()).isEqualTo(land2);
        assertThat(land1.getSoorten()).isEmpty();
        assertThat(land2.getSoorten()).containsOnly(soort1);
    }

    @Test
    void eenNullLandInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> soort1.setLand(null));
    }
}