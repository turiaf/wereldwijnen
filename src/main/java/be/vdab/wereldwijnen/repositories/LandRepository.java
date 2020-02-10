package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;

import java.util.List;
import java.util.Optional;

public interface LandRepository {
    List<Long> findAllLandsId();
    Optional<Land> findById(long id);
}
