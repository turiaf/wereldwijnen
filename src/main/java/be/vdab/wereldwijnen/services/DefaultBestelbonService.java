package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Bestelbon;
import be.vdab.wereldwijnen.repositories.BestelbonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBestelbonService implements BestelbonService {
    private final BestelbonRepository repository;

    public DefaultBestelbonService(BestelbonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Bestelbon bestelbon) {
        repository.create(bestelbon);
    }
}
