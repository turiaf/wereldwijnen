package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Soort;

import java.util.List;
import java.util.Optional;

public interface SoortRepository {
    Optional<Soort> findById(long id);
}
