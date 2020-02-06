package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
class JpaWijnRepository implements WijnRepository {
    private final EntityManager manager;

    public JpaWijnRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Wijn> findAll() {
        return manager.createNamedQuery("Wijn.findAll", Wijn.class)
//                check setHint
                .getResultList();
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return Optional.ofNullable(manager.find(Wijn.class, id));
    }
}
