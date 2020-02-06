package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaSoortRepository.class)
@Sql("/insertLand.sql")
@Sql("/insertSoort.sql")
class JpaSoortRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaSoortRepository repository;
    private static final String SOORTEN = "soorten";

    public JpaSoortRepositoryTest(JpaSoortRepository repository) {
        this.repository = repository;
    }

    //    private Land land1;
//    private Soort soort1;
//
//    @BeforeEach
//    void beforeEach() {
//        land1 = new Land("test1");
//        soort1 = new Soort("test1");
//    }
    private long idVanTestSoort() {
        return super.jdbcTemplate.queryForObject(
                "select id from soorten where naam = 'test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestSoort()).get().getNaam()).isEqualTo("test");
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(SOORTEN))
                .extracting(soort -> soort.getNaam())
                .isSortedAccordingTo((n1, n2) -> n1.compareToIgnoreCase(n2));
    }
}