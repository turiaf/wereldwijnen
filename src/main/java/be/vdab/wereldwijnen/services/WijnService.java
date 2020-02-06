package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Optional;

public interface WijnService {
    List<Wijn> findAll();
    Optional<Wijn> findById(long id);
}
