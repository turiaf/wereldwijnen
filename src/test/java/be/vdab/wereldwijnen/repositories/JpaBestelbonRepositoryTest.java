package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Bestelbon;
import be.vdab.wereldwijnen.domain.Bestelwijze;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaBestelbonRepository.class)
@Sql("/insertBestelbon.sql")
//@Sql("/insertBestelbonBestelbonlijn.sql")
class JpaBestelbonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaBestelbonRepository repository;
    private static String BESTELBONNEN = "bestelbonnen";
    private Bestelbon bestelbon1;

    public JpaBestelbonRepositoryTest(JpaBestelbonRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    void beforeEach() {
        bestelbon1 = new Bestelbon(LocalDateTime.now(), "test1", "test1", "test1", "test1",
                "test1", Bestelwijze.AFHALEN);
    }

    @Test
    void create() {
        repository.create(bestelbon1);
        assertThat(bestelbon1.getId()).isPositive();
        assertThat(super.countRowsInTableWhere(BESTELBONNEN, "id= "+ bestelbon1.getId())).isOne();
    }
}