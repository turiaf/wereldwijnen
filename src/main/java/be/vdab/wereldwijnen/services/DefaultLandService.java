package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.repositories.LandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultLandService implements LandService {
    private final LandRepository repository;

    public DefaultLandService(LandRepository repository) {
        this.repository = repository;
    }

//    @Override
//    public List<Land> findAll() {
//        return repository.findAll();
//    }

    @Override
    public List<Long> findAllLandsId() {
        return repository.findAllLandsId();
    }

    @Override
    public Optional<Land> findById(long id) {
        return repository.findById(id);
    }
}
