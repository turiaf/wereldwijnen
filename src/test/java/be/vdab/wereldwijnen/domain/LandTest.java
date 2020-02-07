package be.vdab.wereldwijnen.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {
    private Land land1;
    private Soort soort1;

    @BeforeEach
    void beforeEach() {
        land1 = new Land("test");
        soort1 = new Soort("test", land1);
    }
    @Test
    void eenNieuweLandHeeftGeenSoorten() {
        assertThat(land1.getSoorten()).isEmpty();
    }
    @Test
    void soortToevoegen() {
        assertThat(land1.addSoort(soort1)).isTrue();
        assertThat(land1.getSoorten()).containsOnly(soort1);
    }
    @Test
    void tweeKeerDezelfdeSoortMislukt() {
        land1.addSoort(soort1);
        assertThat(land1.addSoort(soort1)).isFalse();
        assertThat(land1.getSoorten()).containsOnly(soort1);
    }
//    @Test
//    void nullAlsSoortMislukt() {
//        assertThatNullPointerException().isThrownBy(() -> land1.addSoort(null));
//    }
//    @Test
//    void soortVerwijderen() {
//        land1.addSoort(soort1);
//        assertThat(land1.removeSoort(soort1)).isTrue();
//        assertThat(land1.getSoorten()).isEmpty();
//    }
//    @Test
//    void eenSoortVerwijderenDieJeNietToevoegdeMislukt() {
//        land1.addSoort(soort1);
//        Soort soort2 = new Soort("test2");
//        assertThat(land1.removeSoort(soort2)).isFalse();
//        assertThat(land1.getSoorten()).containsOnly(soort1);
//    }
}