package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Soort;

import java.util.List;
import java.util.Optional;

public interface SoortService {
    List<Soort> findAll();
    Optional<Soort> findById(long id);
}
