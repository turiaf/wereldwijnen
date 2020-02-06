package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Optional;

public interface WijnRepository {
    List<Wijn> findAll();
    Optional<Wijn> findById(long id);
}
