package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Land;

import java.util.List;
import java.util.Optional;

public interface LandService {
    List<Land> findAll();
    Optional<Land> findById(long id);
}
