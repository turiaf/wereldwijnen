package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
class JpaWijnRepository implements WijnRepository {
    private final EntityManager manager;

    public JpaWijnRepository(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public Optional<Wijn> findById(long id) {
        return Optional.ofNullable(manager.find(Wijn.class, id));
    }

    @Override
    public List<Wijn> findAllInList(List<Long> idList) {
        return manager.createNamedQuery("Wijn.findAllInList", Wijn.class)
                .setParameter("idList", idList)
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph(Wijn.MET_SOORTENLAND))
                .getResultList();
    }

    @Override
    public List<Wijn> findAllInListZonderSoort(List<Long> idList) {
        return manager.createNamedQuery("Wijn.findAllInList", Wijn.class)
                .setParameter("idList", idList)
                .getResultList();
    }
}
