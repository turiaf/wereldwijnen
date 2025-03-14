package be.vdab.wereldwijnen.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaLandRepository.class)
@Sql("/insertLand.sql")
class JpaLandRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
        private final JpaLandRepository repository;
        private static final String LANDEN = "landen";

    public JpaLandRepositoryTest(JpaLandRepository repository) {
        this.repository = repository;
    }

//    @Test
//    void findAll() {
//        assertThat(repository.findAll()).hasSize(super.countRowsInTable(LANDEN))
//                .extracting(land1 -> land1.getNaam())
//                .isSortedAccordingTo((n1, n2) -> n1.compareToIgnoreCase(n2));
//    }

    @Test
    void findAllLandsId() {
        assertThat(repository.findAllLandsId()).hasSize(super.countRowsInTable(LANDEN));
    }

    private long idVanTestLand() {
        return super.jdbcTemplate.queryForObject(
                "select id from landen where naam = 'test'", Long.class);
    }

    @Test
    void findById() {
        Land land = repository.findById(idVanTestLand()).get();
        assertThat(land.getNaam()).isEqualTo("test");
    }

    @Test
    void soortenLazyLoaded() {
        Land land = repository.findById(idVanTestLand()).get();
        assertThat(land.getSoorten())
                .extracting(soort -> soort.getNaam());
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}