package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
class JpaLandRepository implements LandRepository {
    private final EntityManager manager;

    public JpaLandRepository(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public List<Long> findAllLandsId() {
        return manager.createNamedQuery("Land.findAllLandsId", Long.class)
                .getResultList();
    }

    @Override
    public Optional<Land> findById(long id) {
        return Optional.ofNullable(manager.find(Land.class, id));
    }
}
