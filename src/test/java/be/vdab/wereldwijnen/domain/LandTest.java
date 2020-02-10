package be.vdab.wereldwijnen.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {
    private Land land1, land2;
    private Soort soort1;

    @BeforeEach
    void beforeEach() {
        land1 = new Land("test");
        soort1 = new Soort("test", land1);
        land2 = new Land("test2");
    }
    @Test
    void land11IsDeLandVanSoort1() {
        assertThat(soort1.getLand()).isEqualTo(land1);
        assertThat(land1.getSoorten()).containsOnly(soort1);
    }
    @Test
    void soort1VerhuistNaarLand2() {
        assertThat(land2.addSoort(soort1)).isTrue();
        assertThat(land1.getSoorten()).isEmpty();
        assertThat(land2.getSoorten()).containsOnly(soort1);
        assertThat(soort1.getLand()).isEqualTo(land2);
    }
    @Test
    void tweeKeerDezelfdeSoortMislukt() {
        land1.addSoort(soort1);
        assertThat(land1.addSoort(soort1)).isFalse();
        assertThat(land1.getSoorten()).containsOnly(soort1);
    }
    @Test
    void eenNullSoortToevoegenMislukt() {
        assertThatNullPointerException().isThrownBy(() -> land1.addSoort(null));
    }
}