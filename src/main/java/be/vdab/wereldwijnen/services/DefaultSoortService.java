package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Soort;
import be.vdab.wereldwijnen.repositories.SoortRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultSoortService implements SoortService {
    private final SoortRepository repository;

    public DefaultSoortService(SoortRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Soort> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Soort> findById(long id) {
        return repository.findById(id);
    }
}
