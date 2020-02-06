package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Bestelbon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
class JpaBestelbonRepository implements BestelbonRepository {
    private final EntityManager manager;

    public JpaBestelbonRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Bestelbon bestelbon) {
        manager.persist(bestelbon);
    }
}
