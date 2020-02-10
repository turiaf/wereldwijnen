package be.vdab.wereldwijnen.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.wereldwijnen.domain.Wijn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaWijnRepository.class)
@Sql("/insertLand.sql")
@Sql("/insertSoort.sql")
@Sql("/insertWijn.sql")
class JpaWijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaWijnRepository repository;
    private static final String WIJNEN = "wijnen";
    private final EntityManager manager;

    public JpaWijnRepositoryTest(JpaWijnRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

//    @Test
//    void findAll() {
//        assertThat(repository.findAll()).hasSize(super.countRowsInTable(WIJNEN))
//                .extracting(wijn -> wijn.getJaar())
//                .isSorted();
//    }

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

    @Test
    void soortLazyLoaded() {
        Wijn wijn = repository.findById(idVanTestWijn()).get();
        assertThat(wijn.getSoort().getNaam());
    }

    @Test
    void landLazyLoaded() {
        Wijn wijn = repository.findById(idVanTestWijn()).get();
        assertThat(wijn.getSoort().getLand().getNaam());
    }

    private long idVanTestWijn2() {
        return super.jdbcTemplate.queryForObject(
                "select id from wijnen where jaar = 2022", Long.class);
    }

    @Test
    void findAllInList() {
        List<Long> idList = new ArrayList<>();
        assertThat(idList.add(idVanTestWijn())).isTrue();
        assertThat(idList.add(idVanTestWijn2())).isTrue();
        List<Wijn> wijns = repository.findAllInList(idList);
        manager.clear();
        assertThat(wijns).hasSize(2)
        .extracting(wijn -> wijn.getSoort().getLand().getNaam());
    }
}