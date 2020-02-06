package be.vdab.wereldwijnen.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WijnTest {
    private Wijn wijn1;

    @BeforeEach
    void beforeEach() {
        wijn1 = new Wijn(2020, 1, BigDecimal.TEN, 1);
    }

    @Test
    void verhoogBestelling() {
        wijn1.verhoogBestelling(10);
        assertThat(wijn1.getInBestelling()).isPositive();
        assertThat(wijn1.getInBestelling()).isEqualTo(11);
    }

    @Test
    void verhoogBestellingMetZeroMisliukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> wijn1.verhoogBestelling(0));
    }

    @Test
    void verhoogBestellingMetNegatiefAantalMisliukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> wijn1.verhoogBestelling(-1));
    }
}