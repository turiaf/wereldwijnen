package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.repositories.WijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultWijnService implements WijnService {
    private final WijnRepository repository;

    public DefaultWijnService(WijnRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Wijn> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return repository.findById(id);
    }
}
