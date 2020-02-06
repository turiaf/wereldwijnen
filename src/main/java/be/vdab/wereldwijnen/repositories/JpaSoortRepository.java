package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Soort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
class JpaSoortRepository implements SoortRepository {
    private final EntityManager manager;

    public JpaSoortRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Soort> findAll() {
        return manager.createNamedQuery("Soort.findAll", Soort.class)
//                check setHint
                .getResultList();
    }

    @Override
    public Optional<Soort> findById(long id) {
        return Optional.ofNullable(manager.find(Soort.class, id));
    }
}
