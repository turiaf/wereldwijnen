package be.vdab.wereldwijnen.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaWijnRepository.class)
@Sql("/insertLand.sql")
@Sql("/insertSoort.sql")
@Sql("/insertWijn.sql")
class JpaWijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaWijnRepository repository;
    private static final String WIJNEN = "wijnen";

    public JpaWijnRepositoryTest(JpaWijnRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(WIJNEN))
                .extracting(wijn -> wijn.getJaar())
                .isSorted();
    }

    private long idVanTestWijn() {
        return super.jdbcTemplate.queryForObject(
                "select id from wijnen where jaar = 2020", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestWijn()).get().getJaar()).isEqualTo(2020);
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}